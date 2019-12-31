/*
 *   连接状态
 * 
 */
package linkcrawler.datatypes;

import java.util.Date;

/**
 * LinkStatus - 连接状态
 * 
 */
public class LinkStatus {
    
	private String id ;
    private String href;   //完整url地址
    private String httpCode;  //http响应状态码如：http/1.1 200 ok ,BAD URL,含Exception的异常字符串，Self Anchor Email Address  Https Address
    private String contentType;// 响应头给出的内容类型
    private URLObject urlLocation;  //一般是该链接所在主站地址 如：http://www.njqxq.gov.cn
    private boolean image = false; // 是否是图像
    
    private boolean timeOut = false;  // 是否超时
    private long linkTime = 0 ;  //ms http链接消耗的时间
 
	private String linkText = "";  //链接文本
    private int customLinkType = -1 ;   //0栏目、1频道、2重点页面、3其它；-1是未定制的url
	private Date checkTime = new Date();   //链接抓取时间
    
    public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public LinkStatus(String href, URLObject urlLocation, String httpCode)
    {
        this(href, urlLocation);        
        this.httpCode = httpCode;        
    }
    
    public LinkStatus(String href, URLObject urlLocation, boolean image)
    {
        this(href, urlLocation); 
        this.image = image;
    }
    
    
    public LinkStatus(String href, URLObject urlLocation)
    {
        this.href = href;
        this.urlLocation = urlLocation;        
    }
    
    public boolean isImage()
    {
        return image;
    }    
    
    /**
     * isUp() - Returns if the HTTP Code indicated that the page can be displayed on a browser.
     * @return boolean
     */
    public boolean isUp()
    {
        if(this.getHttpCode().matches(".*200.*|.*206.*|.*301.*|.*302.*|.*303.*|.*304.*"))
        {
            return true;
        }
        return false;
    }
    
    public boolean isEmailAddress()
    {
        return this.getHttpCode().equals("Email Address");
    }
    
    public boolean isSelfAnchor()
    {
        return this.getHttpCode().equals("Self Anchor");
    }
    public boolean isHttpsAddrress(){
    	return this.getHttpCode().equals("Https Address");
    }

    public String getHrefUnformatted() {
        if (this.href.endsWith("/"))
        {
            return this.href.substring(0, this.href.length() - 1);
            
        }
        return this.href;
    }
    
    //获取全路径url
    public URLObject getHref() throws Exception {
        String fixedHref = this.href;
        
        if (fixedHref.endsWith("/"))
        {
            fixedHref = fixedHref.substring(0, fixedHref.length() - 1);
        }
        
        
        if (fixedHref.startsWith("/"))
        {
            if(urlLocation.toString().endsWith("/"))
            {                
               return new URLObject(this.urlLocation.toString().substring(0, this.urlLocation.toString().length() - 1) + fixedHref);             
            }
            return new URLObject(this.urlLocation + fixedHref);
        }
        
        if(!fixedHref.startsWith("mailto:") && !fixedHref.startsWith("http") && !fixedHref.startsWith("javascript:"))
        {
            if(urlLocation.toString().endsWith("/"))
            {                 
                return new URLObject(this.urlLocation.toString().substring(0, this.urlLocation.toString().length() - 1) + "/" + fixedHref);
            }
            return new URLObject(this.urlLocation + "/" + fixedHref);
        }
        
        return new URLObject(fixedHref);
    }
    
    public String getHref2(){
    	return this.href;
    }
    //判断是否是站内链接，子站链接是站内链接
    public boolean isInternalLink() throws Exception
    {
        boolean isInternal = this.getHref().toString().startsWith(this.urlLocation.toString());
        //if false we should also consider the fact that could be a subdomain link that is also an internal link
        if(!isInternal && this.getHref().isSitePartOfSameDomain(urlLocation)) //子站连接，二级域名
            isInternal = true;
        return isInternal;
    }
    //根据响应类型判断\协议类型是否适合下载该链接
    public boolean isValidForCrawling() throws Exception
    {
        return this.getContentType().contains("text/html") && this.getHref().getProtocol().equalsIgnoreCase("http") ;
    }

    @Override
    public String toString() {
        return "Link Detail {" + "href=" + href + ", httpCode=" + getHttpCode() + '}';
    }
    
    public String toJSON() {
        return "{\"url\": \"" + href + "\", \"status\": \""+ getHttpCode() +"\", \"contentType\": \""+ getContentType() +"\"}";
    }
    
    public String toJSON(String linkType) {
        return "{\"url\": \"" + href + "\", \"status\": \""+ getHttpCode() +"\", \"contentType\": \""+ getContentType() +"\", \"type\": \""+ linkType +"\"}";
    }

    /**
     * @return the httpCode
     */
    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }
    //设置文本类型，如果类型为空""，则为"not specified"
    public void setContentType(String contentType)
    {
    	if(contentType.indexOf(";") > -1)
    	{
    		this.contentType = contentType.substring(0, contentType.indexOf(";"));
    	}
    	else
    	{
    		this.contentType = contentType;
    	}
    	if(this.contentType.trim().equals(""))
    	{
    		this.contentType = "[Not Specified]";
    	}
    }
    
    public String getContentType()
    {
    	return this.contentType;
    }
    
    public String[] getStatusInfoInArray()
    {
        return new String[]{href, getHttpCode()};
    }
   //判断是否是子站，如：njqxq.gov.cn 主要看njqxq是否相等
    public boolean isIsSubdomain() {
    	
        try {
			return getHref().getDomainName().equals(urlLocation.getDomainName());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
   //判断该链接是否是满足路径深度要求的子站链接，子路径数大于指定值。
  // 深度0对应www.xx.njqx.gov.cn 这样的网址，  注意最后不能有“/”
  //da.njqxq  mx.dk.njqxq  www.kz.njqxq 可能返回true  
   public boolean isSubDomainDepth(int depth) {
    	boolean res = false;
        try {
        	URLObject uobject = getHref();
			if( uobject.getDomainName().equals(urlLocation.getDomainName())){//同域
				
				if(uobject.getPrefixes().contains(".") ||!uobject.getPrefixes().equals("www")){          //是子站  www.dc.njqx
				  if(uobject.getDepthLevel() == 0 ){  //是子站主页链接
					  return false;
				  }
				  if(uobject.getDepthLevel()> depth){    // 深度超过要求
                      res = true;						
				}
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return res;
		}
        return res;
    }
    
   
   
	public boolean isTimeOut() {
		return timeOut;
	}

	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	public long getLinkTime() {
		return linkTime;
	}

	public void setLinkTime(long linkTime) {
		this.linkTime = linkTime;
	}
	   public String getLinkText() {
			return linkText;
		}

		public void setLinkText(String linkText) {
			this.linkText = linkText;
		}

		public int getCustomLinkType() {
			return customLinkType;
		}

		public void setCustomLinkType(int customLinkType) {
			this.customLinkType = customLinkType;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}    
}
