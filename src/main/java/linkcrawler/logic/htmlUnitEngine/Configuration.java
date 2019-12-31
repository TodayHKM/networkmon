/*
 *    
 * 
 */
package linkcrawler.logic.htmlUnitEngine;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.util.ArrayList;

import linkcrawler.datatypes.URLObject;

/**
 * Configuration - Object that contain all settings needed for the crawling engine
 * 
 */
public class Configuration {
    private String name  ;  //监控任务名称
    
    private URLObject domain;   //网站地址
    private ArrayList<String> exclusionListArray ;
    private String browserName = "IE 10";
    private int allowedDepthLevel = 1;  // 监控深度
    private boolean imageCheck = true;  //是否检查图片
    private boolean checkSubdomains = true; //默认是只检查子站首页，如果为false，子站和主站一样检查
    private String httpUserName = "";  //https 需要的用户名
    private String httpPassword = "";
    
    private int timeOut = 5000;  //超时 单位 ms
    private int jobTime = 60 ;  // 任务执行周期，单位： 分钟
    
    private boolean bEmailWarn = false;   //是否邮件报警，默认false
    private boolean bTelephoneWarn = false; //是否电话短信报警
    private String telephone = "";   //报警电话
    private String email = "" ;   //报警目的邮箱    
    private String sendEmail;     //发送邮件的邮箱
    private String emailPassword;  //发送邮件邮箱的密码
	private String emailHost ; //发送邮箱的主机如 15850675602@139.com
    
    public Configuration()
    {
        
    }

    /**
     * @return the domain 如 njqxq
     */
    public URLObject getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(URLObject domain) {
        this.domain = domain;
    }

    /**
     * @return the exclusionListArray
     */
    public ArrayList<String> getExclusionListArray() {
        return exclusionListArray;
    }

    /**
     * @param exclusionListArray the exclusionListArray to set
     */
    public void setExclusionListArray(ArrayList<String> exclusionListArray) {
        this.exclusionListArray = exclusionListArray;
    }

    /**
     * @return the browserName
     */
    @SuppressWarnings("deprecation")
	public BrowserVersion getBrowserEnum() {
        if (browserName.equals("Mozilla Firefox 3.6")) {
            return BrowserVersion.FIREFOX_3_6;
        }
        else if (browserName.equals("Mozilla Firefox 10")) {
            return BrowserVersion.FIREFOX_10;
        }
        else if (browserName.equals("Mozilla Firefox 17")) {
            return BrowserVersion.FIREFOX_17;
        }
        else if (browserName.equals("IE 6")) {
            return BrowserVersion.INTERNET_EXPLORER_6;
        } else if (browserName.equals("IE 7")) {
            return BrowserVersion.INTERNET_EXPLORER_7;
        } else if (browserName.equals("IE 8")) {
            return BrowserVersion.INTERNET_EXPLORER_8;
        }
        else if (browserName.equals("IE 9")) {
            return BrowserVersion.INTERNET_EXPLORER_9;
        }
        else if (browserName.equals("IE 10")) {
            return BrowserVersion.INTERNET_EXPLORER_10;
        }
        
        return BrowserVersion.getDefault();
    }

    /**
     * @param browserName the browserName to set
     */
    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    /**
     * @return the allowedDepthLevel
     */
    public int getAllowedDepthLevel() {
        if (allowedDepthLevel == 0)
            return 1000000000;
        return allowedDepthLevel;
    }

    /**
     * @param allowedDepthLevel the allowedDepthLevel to set
     */
    public void setAllowedDepthLevel(int allowedDepthLevel) {
        this.allowedDepthLevel = allowedDepthLevel;
    }

    /**
     * @return the imageCheck
     */
    public boolean isImageCheck() {
        return imageCheck;
    }

    /**
     * @param imageCheck the imageCheck to set
     */
    public void setImageCheck(boolean imageCheck) {
        this.imageCheck = imageCheck;
    }

    /**
     * @return the checkSubdomains
     */
    public boolean isCheckSubdomains() {
        return checkSubdomains;
    }

    /**
     * @param checkSubdomains the checkSubdomains to set
     */
    public void setCheckSubdomains(boolean checkSubdomains) {
        this.checkSubdomains = checkSubdomains;
    }

    /**
     * @return the httpUserName
     */
    public String getHttpUserName() {
        return httpUserName;
    }

    /**
     * @param httpUserName the httpUserName to set
     */
    public void setHttpUserName(String httpUserName) {
        this.httpUserName = httpUserName;
    }

    /**
     * @return the httpPassword
     */
    public String getHttpPassword() {
        return httpPassword;
    }

    /**
     * @param httpPassword the httpPassword to set
     */
    public void setHttpPassword(String httpPassword) {
        this.httpPassword = httpPassword;
    }
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getJobTime() {
		return jobTime;
	}

	public void setJobTime(int jobTime) {
		this.jobTime = jobTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	
	public boolean isbEmailWarn() {
		return bEmailWarn;
	}

	public void setbEmailWarn(boolean bEmailWarn) {
		this.bEmailWarn = bEmailWarn;
	}

	public boolean isbTelephoneWarn() {
		return bTelephoneWarn;
	}

	public void setbTelephoneWarn(boolean bTelephoneWarn) {
		this.bTelephoneWarn = bTelephoneWarn;
	}

	@Override
    public String toString()
    {
        String configToDisplay = "******************************\r\n";
        configToDisplay += "Domain to check: " + this.getDomain().toString() +"\r\n";
        configToDisplay += "Amount of excluded URL: " + this.getExclusionListArray().size() +"\r\n";
        configToDisplay += "BrowserName to Use: " + this.getBrowserEnum().getUserAgent() +"\r\n";
        configToDisplay += "Depth Level: " + this.getAllowedDepthLevel() +"\r\n";
        configToDisplay += "Check for Broken Images: " + this.isImageCheck() +"\r\n";
        configToDisplay += "Check for Subdomains: " + this.isCheckSubdomains() +"\r\n";
        configToDisplay += "******************************\r\n";
        configToDisplay += "HTTP AUTH\r\n";
        configToDisplay += "******************************\r\n";
        configToDisplay += "HTTP Username: " + this.getHttpUserName() +"\r\n";
        configToDisplay += "HTTP Password: " + this.getHttpPassword() +"\r\n";
        configToDisplay += "******************************";
        return configToDisplay;
    }
    
    
}
