/*
 *  
 * 
 */
package linkcrawler.datatypes;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import linkcrawler.connectors.HTTPConnector;

/**
 *��װ��url
 * 
 */
public class URLObject {
    
    
    private String protocol = "";    //Э������ http
    private String prefixes = "";    //������ǰ׺����www
    private String domainName = "";  // ������ njqxq
    private String domainSuffix = ""; //���������׺��  gov.cn
    private String[] domainSuffixesArray = {"AC", "AD", "AE", "AERO", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ", "AR", "ARPA", "AS", "ASIA", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BIZ", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA", "CAT", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "COM", "COOP", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EDU", "EE", "EG", "EH", "ER", "ES", "ET", "EU", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GOV", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "INFO", "INT", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JOBS", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MIL", "MK", "ML", "MM", "MN", "MO", "MOBI", "MP", "MQ", "MR", "MS", "MT", "MU", "MUSEUM", "MV", "MW", "MX", "MY", "MZ", "NA", "NAME", "NC", "NE", "NET", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "ORG", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PRO", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST", "SU", "SV", "SX", "SY", "SZ", "TC", "TD", "TEL", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TP", "TR", "TRAVEL", "TT", "TV", "TW", "TZ", "UA", "UG", "UK", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "XXX", "YE", "YT", "ZA", "ZM", "ZW"};
    private ArrayList<String> resource;  //��url����·���б���/��/index.html,���1��/jnjs/xx��/jnjs/kk.htm  ���2 ������"/"����Ⱦ��Ǽ���
    boolean mainSiteOnly = false;  //��url�����Ƿ�����վ��
    private boolean offlinesite = false;  //�Ƿ�������վ��
    
    private int type ;  //1 ��վ��ҳ  2 һ������  3Ƶ�� 4�ص�ҳ�� 5 ����
    private String port = "" ;
    
    public URLObject(String url) throws Exception
    {
        this(url, false);
    }
    
    
    public URLObject(String url, boolean mainSiteOnly) throws Exception
    {
    	String old = url;
        url = url.trim();
        resource = new ArrayList<String>();
        if(url.indexOf("://") != -1)
        {
            //Extract Protocol
            url = this.extractProtocol(url);          //���ص��ǲ�����Э��Ĳ���  
            //Get and remove resource only if a / is found after domain and suffix
            if(url.indexOf("/") != -1) //����·����../jsjn
            {
                extractResourceSection(url.substring(url.indexOf("/") + 1, url.length()));
                url = url.substring(0, url.indexOf("/"));//����������
            }
            // ��ȡ�˿�
            url = extractPort(url);
            
            //Extract Suffix
            url = extractSuffixesAndDomainName(url);
            
            if(!url.equals(""))
                this.prefixes = url;            
        }
        else
        {
            throw new Exception("URL invalid: No protocol detected. ->" + old);
        }
        this.mainSiteOnly = mainSiteOnly;
    }
    // ����url������Э��Ĳ���
    private String extractProtocol(String url)
    {
        this.protocol = url.split("://")[0];            
        url = url.replaceAll(this.protocol+"://", "");
        return url;
    }
    private String extractPort(String url){
    	String[] strs = url.split(":");
    	if(strs.length>1){
    		port = strs[1];    		
    	}	    	
    	return strs[0];
    	
    }
    //������������������������ǰ�沿�֣��磺www.njqxq.gov.cn ,����www���������������ж��Ƿ�������վ��
    private String extractSuffixesAndDomainName(String urlFragment)
    {
        String[] sections = urlFragment.split("\\.");
        for(int x = sections.length - 1; x >= 0; x--)
        {
            if(this.isDomainSuffix(sections[x]))
            {
                //removing this from urlFragment
                urlFragment = urlFragment.replaceAll("\\."+sections[x]+"$", "");
                if(!this.domainSuffix.equals(""))
                    this.domainSuffix = sections[x] + "." + this.getDomainSuffix();
                else
                    this.domainSuffix = sections[x];
            }
            else if(this.getDomainName().equals(""))
            {
                this.domainName = sections[x];
                //Removing section from fragment
                urlFragment = urlFragment.replaceAll(sections[x]+"$", "");
                if(urlFragment.endsWith("."))
                {
                    urlFragment = urlFragment.replaceAll("\\.$", "");
                }
                break ;  //������������ֱ���˳�ѭ��   by fh
            }
        }
        if(this.getDomainSuffix().equals(""))
            offlinesite = true;
        return urlFragment;
    }
    
    public int getDepthLevel()
    {
        return this.resource.size();
    }
    
    public boolean isValidSitemapLocation()
    {
        URL siteURL = null;
        try {
            siteURL = new URL(this.getSitemapLocation());
                try {
                    URLConnection conn = siteURL.openConnection();
                    conn.connect();
                } catch (IOException ex) {
                    return false;
                }
        } catch (MalformedURLException ex) {
            return false;
        }
        return true;
    }
    
    public String getSitemapLocation()
    {   
        String URLFragment = this.toString();
        if(this.resource.isEmpty())
        {
            if(URLFragment.endsWith("/"))
            {
                URLFragment += "sitemap.xml";
            }
            else
            {
                URLFragment += "/sitemap.xml";
            }

        }                  
            
        return URLFragment;
        
    }
    
    private boolean isDomainSuffix(String possibleSuffix)
    {
        return Arrays.asList(domainSuffixesArray).contains(possibleSuffix.toUpperCase());
    }
    //��url��·��../xx/yy/..  �ֳ�xx,yy�󣬼���resource��
    private void extractResourceSection(String URLResourceSection)
    {
        if(URLResourceSection.indexOf("/") != -1)
        {
            this.resource.addAll(Arrays.asList(URLResourceSection.split("/")));
        }
        else
        {
            this.resource.add(URLResourceSection);
        }
    }

    /**
     * 
     * @return String - Absolute URL including resource path
     */
    @Override
    public String toString() {
        String urlFormatted = this.protocol + "://";
        if(!prefixes.equals(""))
            urlFormatted += prefixes + ".";       
        if(isOfflinesite() == true)
            urlFormatted += getDomainName();
        else
            urlFormatted += getDomainName() + "." + this.getDomainSuffix();
        
        if(!port.equals("")){               //���϶˿ں�
           urlFormatted += ":" + port;
        }
        if(!mainSiteOnly)
        {
            if(!resource.isEmpty())
            {
                for(String resourceSection : resource)
                {
                    urlFormatted += "/" + resourceSection;
                }
            }        
        }
        if (urlFormatted.endsWith("/"))
        {
            return urlFormatted.substring(0, urlFormatted.length() - 1);
            
        }
        return urlFormatted;
    }    
    //
    public boolean isURLReachable(){
    	HTTPConnector httpconn = null;
    	HttpURLConnection conn = null;
    	String siteURL = this.toString();   	
    	try {
    		httpconn = new HTTPConnector(siteURL);
			conn = httpconn.getConnection();
			if(conn == null){  //����ʧ��
				return false;
			}
		} catch (Exception e) {
		    return false;
		}finally{
			if(conn != null)
				conn.disconnect();
		}
        return true;  
    }
    
    // �����վ���Ƿ���Ч��url��ʽ��http�����쳣������
    public URLValidationCodes isValidMainSiteUrl()
    {
    	// 1 �Ȳ��Ը���url������http://www.njqxq.gov.cn
    	// 2 ��1ʧ�ܣ�����Ը���urlȫ·����http://www.njqxq.gov.cn/index.html
        String siteURL = this.toString();
        HttpURLConnection conn = null;
        HttpURLConnection conn1 = null;
                try {
                	conn = new HTTPConnector(siteURL).getConnection0();
                	conn.connect();                	
                } 
                catch (MalformedURLException ex) {
                    return URLValidationCodes.MALFORMED;
                }
                catch (IOException ex) {
                    System.out.println("Main site looks Unreachable");
                    if(!resource.isEmpty())  //��վurl����·��
                    {                       
                        mainSiteOnly = false;
                        siteURL = this.toString();  //��ȡ������·����url
                        mainSiteOnly = true;    
                        conn1 = null;
                        try
                        {                           
                            
                            conn1 = new HTTPConnector(siteURL).getConnection();
                        	conn1.connect();
                        }
                        catch(Exception e)
                        {
                            return URLValidationCodes.UNREACHABLE;
                        }finally{
                        	if(conn1!=null)
                        		conn1.disconnect();
                        }
                    }
                    else
                    {
                        return URLValidationCodes.UNREACHABLE;
                    }
                }finally{
                	if(conn!=null)
                		conn.disconnect();
                }
        
        return URLValidationCodes.VALID;
    }
    
    public String getMainSiteOnly()
    {
        String urlFormatted = this.protocol + "://";
        if(!prefixes.equals(""))
            urlFormatted += prefixes + ".";             
        if(isOfflinesite() == true)
            urlFormatted += getDomainName();
        else
            urlFormatted += getDomainName() + "." + this.getDomainSuffix();
        return urlFormatted;
    }
     
    public boolean isMainSiteOnly(){
    	return this.mainSiteOnly;
    }

    public boolean isOfflinesite() {
        return offlinesite;
    }
    //�ж�url�Ƿ�����ͬһ����ͬdomain(njqxq)\(suffix)(gov.cn)����, hh.njqxq.gov.cn����  ,
    public boolean isSitePartOfSameDomain(URLObject uo)
    {
        if(uo.isOfflinesite() && this.isOfflinesite())
        {
            return this.getDomainName().equals(uo.getDomainName());
        }
        else
        {
            return this.getDomainName().equals(uo.getDomainName()) && this.getDomainSuffix().equals(uo.getDomainSuffix());
        }
    }

    public String getDomainName() {
        return domainName;
    }

    public String getDomainSuffix() {
        return domainSuffix;
    }


	public String getPrefixes() {
		return prefixes;
	}


	public String getProtocol() {
		return protocol;
	}
    
    
}
