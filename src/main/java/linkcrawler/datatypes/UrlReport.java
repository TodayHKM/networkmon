/*
 *    一个被加载网页检查的报告
 */
package linkcrawler.datatypes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 *
 */
public class UrlReport {
    private String id ;
    private ArrayList<LinkStatus> internalLinks;  //站内链接，含子站
    private ArrayList<LinkStatus> externalLinks;    // 站外链接
    private ArrayList<LinkStatus> specialLinks;  //特殊链接
    private ArrayList<LinkStatus> imagesSrc;  // 图片链接
    private int goodLinks = 0;
    private int badLinks = 0;
    private String currentPage;    //上述链接所在当前页url
    HashMap<String, Integer> contentTypeStats;   //链接对应的类型
    
    private String title =""  ;   //页标题  
    private boolean pageStatus = false;   //页加载状态，true 加载且成功
    
    public UrlReport(String currentPage)
    {
        this.currentPage = currentPage;
        this.internalLinks = new ArrayList<LinkStatus>();
        this.externalLinks = new ArrayList<LinkStatus>();
        this.specialLinks = new ArrayList<LinkStatus>();
        this.imagesSrc = new ArrayList<LinkStatus>();
        contentTypeStats = new HashMap<String, Integer>();
    }
       
    //分外部链接、特殊链(email # https)、图片连接、内部链接加入不同列表
    public void addLink(LinkStatus checkedLink) throws Exception
    {    	
        if(checkedLink.isInternalLink() && !checkedLink.isSelfAnchor() && !checkedLink.isImage() && !checkedLink.isHttpsAddrress())
        {
            this.getInternalLinks().add(checkedLink);
        }
        else if(checkedLink.isEmailAddress() && !checkedLink.isImage() )
        {
            this.getSpecialLinks().add(checkedLink);
        }
        else if(checkedLink.isSelfAnchor() && !checkedLink.isImage())
        {
            this.getSpecialLinks().add(checkedLink);
        }
        else if(checkedLink.isHttpsAddrress() && !checkedLink.isImage()){
        	this.getSpecialLinks().add(checkedLink);
        }
        else if(checkedLink.isImage())
        {
            getImagesSrc().add(checkedLink);
        } 
        else
        {
            this.getExternalLinks().add(checkedLink);
        }
        
        if(checkedLink.isUp())
    	{
    		goodLinks++;
    	}
    	else if(!checkedLink.isSelfAnchor() && !checkedLink.isEmailAddress() && !checkedLink.isHttpsAddrress() && !checkedLink.isUp())
    	{
    		badLinks++;
    	}
        
        if(!contentTypeStats.containsKey(checkedLink.getContentType()))
		{
			contentTypeStats.put(checkedLink.getContentType().trim().toLowerCase(), 1);
		}
		else
		{
			contentTypeStats.put(checkedLink.getContentType().trim().toLowerCase(), contentTypeStats.get(checkedLink.getContentType().trim().toLowerCase()) + 1);
		}
    }

    /**
     * 
     * @return Array of int values that represent the amount of each link found in current page
     * First value represent internal links
     * Second value represent external links
     * Third value represent special links
     */
    public int[] getShortDetails()
    {
        return new int[]{this.getInternalLinks().size(), this.getExternalLinks().size(), this.getSpecialLinks().size()};
    }
    
    public boolean haveImages()
    {
        return !this.imagesSrc.isEmpty();
    }
    
    
    public boolean haveInternalLinks()
    {
        return !this.internalLinks.isEmpty();
    }
    
    public boolean haveExternalLinks()
    {
        return !this.externalLinks.isEmpty();
    }
    
    public boolean haveSpecialLinks()
    {
        return !this.specialLinks.isEmpty();
    }
    
    public String getPageUrl()
    {
        return this.currentPage;
    }
    
    @Override
    public String toString() {
        String reportInTxt = "URL = " + this.currentPage + "\n\r"
                + "\n\r -- Internal Links --\n\r ";
        
        if(this.getInternalLinks().isEmpty())
        {
             reportInTxt += "No internal links detected";
        }
        else
        {
            for(LinkStatus link : this.getInternalLinks())
            {
                 reportInTxt += link.toString() + "\n\r";
            }
        }
        
        reportInTxt += "\n\r -- External Links --\n\r ";
        
        if(this.getExternalLinks().isEmpty())
        {
             reportInTxt += "No external links detected";
        }
        else
        {
            for(LinkStatus link : this.getExternalLinks())
            {
                 reportInTxt += link.toString() + "\n\r";
            }
        }
        return reportInTxt;
    }
    
    public int getGoodLinks()
    {
    	return goodLinks;
    }
    
    public int getBadLinks()
    {
    	return badLinks;
    }
    
    public HashMap<String, Integer> getContentTypeStatistics()
    {
    	return contentTypeStats;
    }
    
    public String toJSON() {
        String reportInTxt = "{"
        		+ "\"id\": \"" + this.currentPage +"\","
        				+ "\"Links\": [ ";
        
        
        for(LinkStatus link : this.getInternalLinks())
        {
             reportInTxt += link.toJSON("Internal Links")+", ";
        }
        for(LinkStatus link : this.getExternalLinks())
        {
        	  reportInTxt += link.toJSON("External Links")+", ";
        }
        for(LinkStatus link : this.getImagesSrc())
        {
        	  reportInTxt += link.toJSON("Images")+", ";
        }
        for(LinkStatus link : this.getSpecialLinks())
        {
        	  reportInTxt += link.toJSON("Special Links")+", ";
        }
        
        
        reportInTxt = reportInTxt.substring(0, reportInTxt.length() - 2);
       
        reportInTxt += "]}";
        
        return reportInTxt;
    }

    public ArrayList<LinkStatus> getInternalLinks() {
        return internalLinks;
    }

    public ArrayList<LinkStatus> getExternalLinks() {
        return externalLinks;
    }

    public ArrayList<LinkStatus> getSpecialLinks() {
        return specialLinks;
    }

    public ArrayList<LinkStatus> getImagesSrc() {
        return imagesSrc;
    }

    public void setImagesSrc(ArrayList<LinkStatus> imagesSrc) {
        this.imagesSrc = imagesSrc;
    }


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public boolean isPageStatus() {
		return pageStatus;
	}


	public void setPageStatus(boolean pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    
    
}
