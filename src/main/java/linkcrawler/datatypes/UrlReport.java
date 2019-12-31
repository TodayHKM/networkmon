/*
 *    һ����������ҳ���ı���
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
    private ArrayList<LinkStatus> internalLinks;  //վ�����ӣ�����վ
    private ArrayList<LinkStatus> externalLinks;    // վ������
    private ArrayList<LinkStatus> specialLinks;  //��������
    private ArrayList<LinkStatus> imagesSrc;  // ͼƬ����
    private int goodLinks = 0;
    private int badLinks = 0;
    private String currentPage;    //�����������ڵ�ǰҳurl
    HashMap<String, Integer> contentTypeStats;   //���Ӷ�Ӧ������
    
    private String title =""  ;   //ҳ����  
    private boolean pageStatus = false;   //ҳ����״̬��true �����ҳɹ�
    
    public UrlReport(String currentPage)
    {
        this.currentPage = currentPage;
        this.internalLinks = new ArrayList<LinkStatus>();
        this.externalLinks = new ArrayList<LinkStatus>();
        this.specialLinks = new ArrayList<LinkStatus>();
        this.imagesSrc = new ArrayList<LinkStatus>();
        contentTypeStats = new HashMap<String, Integer>();
    }
       
    //���ⲿ���ӡ�������(email # https)��ͼƬ���ӡ��ڲ����Ӽ��벻ͬ�б�
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
