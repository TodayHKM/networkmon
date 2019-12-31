/*
 *   ����״̬
 * 
 */
package linkcrawler.datatypes;

import java.util.Date;

/**
 * LinkStatus - ����״̬
 * 
 */
public class LinkStatus {
    
	private String id ;
    private String href;   //����url��ַ
    private String httpCode;  //http��Ӧ״̬���磺http/1.1 200 ok ,BAD URL,��Exception���쳣�ַ�����Self Anchor Email Address  Https Address
    private String contentType;// ��Ӧͷ��������������
    private URLObject urlLocation;  //һ���Ǹ�����������վ��ַ �磺http://www.njqxq.gov.cn
    private boolean image = false; // �Ƿ���ͼ��
    
    private boolean timeOut = false;  // �Ƿ�ʱ
    private long linkTime = 0 ;  //ms http�������ĵ�ʱ��
 
	private String linkText = "";  //�����ı�
    private int customLinkType = -1 ;   //0��Ŀ��1Ƶ����2�ص�ҳ�桢3������-1��δ���Ƶ�url
	private Date checkTime = new Date();   //����ץȡʱ��
    
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
    
    //��ȡȫ·��url
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
    //�ж��Ƿ���վ�����ӣ���վ������վ������
    public boolean isInternalLink() throws Exception
    {
        boolean isInternal = this.getHref().toString().startsWith(this.urlLocation.toString());
        //if false we should also consider the fact that could be a subdomain link that is also an internal link
        if(!isInternal && this.getHref().isSitePartOfSameDomain(urlLocation)) //��վ���ӣ���������
            isInternal = true;
        return isInternal;
    }
    //������Ӧ�����ж�\Э�������Ƿ��ʺ����ظ�����
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
    //�����ı����ͣ��������Ϊ��""����Ϊ"not specified"
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
   //�ж��Ƿ�����վ���磺njqxq.gov.cn ��Ҫ��njqxq�Ƿ����
    public boolean isIsSubdomain() {
    	
        try {
			return getHref().getDomainName().equals(urlLocation.getDomainName());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
   //�жϸ������Ƿ�������·�����Ҫ�����վ���ӣ���·��������ָ��ֵ��
  // ���0��Ӧwww.xx.njqx.gov.cn ��������ַ��  ע��������С�/��
  //da.njqxq  mx.dk.njqxq  www.kz.njqxq ���ܷ���true  
   public boolean isSubDomainDepth(int depth) {
    	boolean res = false;
        try {
        	URLObject uobject = getHref();
			if( uobject.getDomainName().equals(urlLocation.getDomainName())){//ͬ��
				
				if(uobject.getPrefixes().contains(".") ||!uobject.getPrefixes().equals("www")){          //����վ  www.dc.njqx
				  if(uobject.getDepthLevel() == 0 ){  //����վ��ҳ����
					  return false;
				  }
				  if(uobject.getDepthLevel()> depth){    // ��ȳ���Ҫ��
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
