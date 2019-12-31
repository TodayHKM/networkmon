/*
 *   ʹ��htmlunit������վ����������㷨�������url����·����
 * 
 */
package linkcrawler.logic.htmlUnitEngine;

import com.ds.util.Uuid;
import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import linkcrawler.connectors.HTTPConnector;
import linkcrawler.dao.CheckTaskDao;
import linkcrawler.dao.LinkCheckDao;
import linkcrawler.dao.PageCheckDao;
import linkcrawler.dao.TaskDao;
import linkcrawler.dao.WarnsDao;
import linkcrawler.datatypes.LinkModel;
import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.SpecialUrl;
import linkcrawler.datatypes.Task;
import linkcrawler.datatypes.URLObject;
import linkcrawler.datatypes.UrlReport;
import linkcrawler.datatypes.Warn;
import linkcrawler.log.LogController;
import linkcrawler.report.ReportController;
import linkcrawler.report.ReportType;
import linkcrawler.warn.EmailWarner;
import linkcrawler.warn.WarnMonitor;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

/**
 * HtmlUnitCrawler is capable to "Crawl" a website and gather href information
 *
 */
public class HtmlUnitCrawler extends Thread {

    private URLObject domain;  //ץȡ����վ
    private ArrayList<String> alreadyCrawled;  //htmlunit�Ѽ���ҳ��url�б�
    private HashMap<String, String[]> previousCheckUrl; //httpconnection���Ӽ�����urls
    private ArrayList<UrlReport> totalReport;
    private byte runByte = 1;  // ����ץȡ�߳��Ƿ��˳�
    private Cache cacheHandler = new Cache();
    private String currentStatus = "Crawler Starting......";
    private int goodLinks = 0;
    private int badLinks = 0;
    private final CredentialsProvider credentials = new BasicCredentialsProvider();  // ��վ��֤
    private LogController log;
    private Configuration cfg; //���ò�������
    private TableView<LinkModel> linkTable = null;
    private String exclusionRegex = "";   // �������򣬷��ϸ�����Ĳ���ץȡ
    
    private int rdepth=0 ;  //�ݹ����crawlURL�����
    private HashMap<String,LinkStatus> errorLinks ;
    private WarnMonitor warner ;
    
    private Task task ;  //��Ӧ������
    private TaskDao  taskdao;  //������ȡ    
    private PageCheckDao pck ;  //ҳ��ȡ
    private CheckTaskDao ctd ; //�����¼��ȡ
    private WarnsDao wd ;   //������¼��ȡ
    
    private Date startTime ;//һ�μ������ʼ���ʱ��
    private Date endTime ;////һ�μ������������ʱ��
    
    public HtmlUnitCrawler(String threadName, LogController log) {
        super(threadName); // Initialize thread.
        this.log = log;
            
        alreadyCrawled = new ArrayList<String>();
        totalReport = new ArrayList<UrlReport>();
        previousCheckUrl = new HashMap<String, String[]>();
        
        errorLinks = new HashMap<String,LinkStatus>();
        taskdao = new TaskDao();
        pck = new PageCheckDao();
        ctd = new CheckTaskDao();
        wd = new WarnsDao();
    }
       
    
    public HtmlUnitCrawler(Configuration cfg, LogController log, TableView<LinkModel> linkTable) {
        this("HtmlUnitCrawler", log);
        this.cfg = cfg;       
        this.domain = cfg.getDomain();
        this.linkTable = linkTable;      
        warner = new WarnMonitor(this.cfg,errorLinks);  // ���뱨����
        
    }
    public HtmlUnitCrawler(Configuration cfg, LogController log) {
        this("HtmlUnitCrawler", log); 
        this.cfg = cfg;       
        this.domain = cfg.getDomain();
        warner = new WarnMonitor(this.cfg,errorLinks);  // ���뱨����
      
    }
    
    public HtmlUnitCrawler(Configuration cfg, LogController log,Task task) {
        this("HtmlUnitCrawler", log); 
        this.cfg = cfg;       
        this.domain = cfg.getDomain();
        warner = new WarnMonitor(this.cfg,errorLinks);  // ���뱨����
        this.task = task;
        
    }
    
    
    @Override
    public void run() {      	
     
      try {
		  long st = System.currentTimeMillis();
		  this.startTime = new Date();
		  System.out.println(task.getName()+"  crawl start ");
		  log.addOutputLine(this.cfg.getName(), "info");
		 
		  if(this.isHostReach()){ //��վ�ɴ�
		    crawlURL(this.domain); // ������Ƚ���ͨ�ü��
		   
		    crawlCustomURLs();    //����ָ����url���м��
		   
		    this.endTime = new Date();
		    ctd.doInsert(this);   //�����������¼
		    
//		    this.warn();  //һ�μ����󱨾�
		    
		    System.out.println("crawled links:"+previousCheckUrl.size());
		    System.out.println("downloaded page:"+alreadyCrawled.size());       
		   
		  }
		  printErrors();
		  System.out.println("toal time:"+(System.currentTimeMillis()-st));
		  setCurrentStatus("Crawler Stopped,Crawler time:"+(System.currentTimeMillis()-st)/1000+"s");
		  //���execl���
		  saveAsExcel();
		  //�������洢���ָ���������
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
	  dofinalize();	  
	}
	 System.out.println(task.getName()+" crawl over");
    }    
    //������վ���ɴ1��������ѯ�����Բ��ɷ��ʣ��򷵻�false
    private boolean isHostReach(){
      boolean bres = false;	
      int cnt = 0;
      while (cnt<6){
       cnt++;      
       if(this.domain.isURLReachable()){
          return true;  	
       }	
       try {
		    Thread.sleep(1000*10);  //��ʱ10��
	    } catch (InterruptedException e) {		
	    }
       cnt++;
      }
      //���ɴ�
      LinkStatus ls = new LinkStatus(this.domain.toString(),this.domain); 
	  ls.setHttpCode("��վ�����쳣");
	  ls.setLinkText("��ҳ");
	  putErrorLinks(ls, domain.toString(),false);
	  pck.saveHostPage(null, ls, task.getId());  //�������Ӽ�¼��Ϣ
	  this.endTime = new Date();
	  ctd.doInsert(this);   //�����������¼
	  this.warn();;    //���ͱ�����Ϣ��
	  log.addOutputLine("��վ�쳣", "info");
      
      return bres;	
    }
    
//     ����:���ͱ��汨����Ϣ
    public void warn(){
    	this.warner.sendMessage();
    	Warn warn = warner.getWarn();
    	warn.setTask_id(this.task.getId());
    	wd.doInsert(warn);
    }
    
    //��execl�ļ���ʽ������
    private void saveAsExcel(){
        ReportController rc = new ReportController(domain.toString(), this.getReport(), ReportType.EXCEL);
  	  rc.start();
  	  try {
  		Thread.sleep(10000);
  	  } catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	  }  //for    execel save
        //�������洢���ָ���������   	
    }
    //�߳̽����������߳̿�ִ�б�־�������Ӧ�б���󣬱������ڵ���ִ�С���
    private void dofinalize(){
    	this.runByte = 1;   //�����´�ִ��
    	goodLinks = 0;
        badLinks = 0;
        cacheHandler.clear();
    	alreadyCrawled.clear();
        totalReport.clear();
        previousCheckUrl.clear(); 
        errorLinks.clear(); 
        
        rdepth = 0 ;
        log.endLog();
        this.log = new LogController(true); //�����µ���־�ļ�
    	
    }
    //�������Ӽ���ͼ�ν���ı��У��ظ��Ļ����� ������
    private void putErrorLinks(LinkStatus ls, String locatedAt,boolean isPageError){
    	try {
    	    LinkStatus link = null;
    	    if(!isPageError){
    	    	link = errorLinks.get(ls.getHref().toString());    	
    	    }else{
    	    	 errorLinks.put(ls.getHref().toString()+" ", ls);  
    	    }
			if(link == null){	//hash����û�������
			  if(!isPageError){
				  errorLinks.put(ls.getHref().toString(), ls);  
			  }else{
			      errorLinks.put(ls.getHref().toString()+" ", ls);
			  }
			  if(linkTable != null ){
				final LinkStatus linkdata = ls;
				
			    Platform.runLater(new Runnable() {
			      @Override
			      public void run() {
			    	if(linkdata != null)
			        {
			        	try {
							linkTable.getItems().add(new LinkModel(linkdata.getHref().toString(), linkdata.getContentType(), linkdata.getHttpCode()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    }
			    });
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    	
    }
    
    // ��֤url�ĸ�ʽ�Ƿ�Ϸ�
    public boolean verifyURL(String urls){
    	
    	try {
			URL url = new URL(urls);
		} catch (MalformedURLException e) {
			return false;
		}
    	return true;
    }
    //�ݹ����ҳ����������url
    @SuppressWarnings("unchecked")
	private void crawlURL(URLObject url) {
        if (this.runByte == 0) {
            return;
        }        
        //Starting Report
        UrlReport reportThisPage = new UrlReport(url.toString());
        log.addOutputLine("Working on url: " + url, "info");
        //����һ����ҳ
        WebClient webClient;
        if (this.cfg.getBrowserEnum() != null) {
            webClient = new WebClient(this.cfg.getBrowserEnum());  // ����ģ������������
        } else {
            webClient = new WebClient();
        }
        if (!this.cfg.getHttpUserName().equals("") && !this.cfg.getHttpPassword().equals("")) {
            webClient.setCredentialsProvider(credentials); //������վ��¼
        }
        webClient.setCache(cacheHandler);  // ����
        webClient.getOptions().setCssEnabled(false);  //����css   applet activexĬ���ǽ�ֹ
        webClient.getOptions().setJavaScriptEnabled(false);   //��ֹjs�ű�
        webClient.getOptions().setRedirectEnabled(true);  //���ÿͻ����ض���
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //  webClient.getOptions().setUseInsecureSSL(true);  // �������֤�飬�ͻ�������
        //Opening site
        log.addOutputLine("Downloading site...", "info");
        setCurrentStatus("Downloading " + url);
        HtmlPage page;
        //5 secs should be enough;
        webClient.getOptions().setTimeout(this.cfg.getTimeOut());     //socket���Ӻͻ�ȡ���ݵĳ�ʱʱ���Ϊ5s        
        try {
            page = webClient.getPage(url.toString());
        } catch (Exception ex) {   //��ҳ���ش���ֱ�ӷ��أ������ɱ���
        	reportThisPage.setPageStatus(false);
      	    LinkStatus ls = new LinkStatus(url.toString(),this.domain); 
      	    putErrorLinks(ls,url.toString(),true); // �����������
    	    totalReport.ensureCapacity(totalReport.size()); //���뱨���б�
            totalReport.add(reportThisPage);
      	    if(url.isMainSiteOnly()){
      	      ls.setHttpCode("��ҳ�����쳣");
      	      pck.saveHostPage(reportThisPage, ls, task.getId());
      	    }else{
      	      ls.setHttpCode("ҳ�����쳣");	
      	      pck.doInsert(totalReport, task.getId());
      	    }
      	   
      	   // warner.sendMessage();    //���ͱ�����Ϣ��
      	   	log.addOutputLine("Unable to load url: " + url + " " + ex.getMessage(), "ERROR");
            return;
        }
        LinkStatus hostls = null;
        if(url.isMainSiteOnly()){  //��ҳok
        	 hostls = new LinkStatus(url.toString(),this.domain);
             hostls.setHttpCode("200ok");
             hostls.setLinkText("��ҳ");
        }
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle(page.getTitleText());
        log.addOutputLine("Crawling..."+page.getTitleText(), "Info");
        //�����ع�����ҳurl�����ѷ����б�
        this.alreadyCrawled.add(url.toString());  
        
        // ��ȡ��ҳ��������
        List<HtmlAnchor> links = page.getAnchors();
        int linksTotal = links.size();
        
        
        log.addOutputLine("Total links detected: " + linksTotal + " link(s)", "info");
        int linksCount = 1;
        // ѭ�����Ӳ���һҳ�е�url
        for (HtmlAnchor link : links) {
            if (this.runByte == 0) {  //�˳�ץȡ
                return;
            }
            LinkStatus linkToCrawl = null;
            try {    
              String urls = page.getFullyQualifiedUrl(link.getHrefAttribute()).toString();
              if(verifyURL(urls)){ 	//��֤htmlunit��ȡ��url�Ƿ�Ϸ�
                linkToCrawl = new LinkStatus(urls, this.domain);
                linkToCrawl.setLinkText(link.asText());
                setCurrentStatus("Checking link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
                log.addOutputLine("Checking link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
                String[] evaluationResult = this.evaluateLink(linkToCrawl);//ͨ��ʵ�����Ӹ�url��ȡ��Ӧ״̬�������
                if(evaluationResult!=null){ //
                  String statusCode = evaluationResult[0];
                  String contentType = evaluationResult[1];
                  String linkTime = evaluationResult[2];
                  String timeOut =  evaluationResult[3];
                  
                  log.addOutputLine(linkToCrawl.getHref() + " Got Status: "+ statusCode + " Content-Type: " + contentType, "info");
                  linkToCrawl.setHttpCode(statusCode);
                  linkToCrawl.setContentType(contentType);
                  linkToCrawl.setLinkTime(Long.valueOf(linkTime));
                  linkToCrawl.setTimeOut(Boolean.valueOf(timeOut));
                  //Storing site
                  reportThisPage.addLink(linkToCrawl);
                
                  //�����ӹ���url����������hash������Ѵ��ڣ����滻
                  previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                  //Updating counts
                  if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                  {                	
                	if(linkToCrawl.isUp()) //����״̬���ж��ǿ���ʾ��url ��2xx,3xx
                	{
                		addGoodLinks(1);    //��������
                	}
                	else   //�������Ӽ������Ļ����ӱ�
                	{
                		this.putErrorLinks(linkToCrawl, url.toString(),false);
                		addBadLinks(1);
                	}
                  }
               }
              }//if
             } catch (Exception e) {
            	e.printStackTrace();
                log.addOutputLine(e.getMessage(), "Error");
                continue;
            }
            
        }        
        //����ͼƬ����
        List<HtmlImage> images = null;
        if (this.cfg.isImageCheck()) {
            log.addOutputLine("Collecting img tags...", "Info");
            images = (List<HtmlImage>) page.getByXPath("//img");
        }
        if (this.cfg.isImageCheck()) {
            log.addOutputLine("Checking src attribute on IMG tags...", "info");
            int imagesTotal = links.size();
            log.addOutputLine("Total images detected: " + linksTotal + " image(s)", "info");
            int imageCount = 1;

            for (HtmlImage image : images) {
                if (this.runByte == 0) {
                    return;
                }
                LinkStatus linkToCrawl;
                try {                	
                  String urls = page.getFullyQualifiedUrl(image.getSrcAttribute()).toString();
                  if(verifyURL(urls)){ 	//��֤htmlunit��ȡ��url�Ƿ�Ϸ�
                    linkToCrawl = new LinkStatus(urls, this.domain, true);
                    linkToCrawl.setLinkText("ͼƬ");
                    setCurrentStatus("Checking image " + (imageCount++) + " of " + imagesTotal + " : " + linkToCrawl.getHref());
                    String[] evaluationResult = this.evaluateLink(linkToCrawl);//ͨ��ʵ�����Ӹ�url��ȡ��Ӧ״̬�������
                    if(evaluationResult!=null ){
                       String statusCode = evaluationResult[0];
                       String contentType = evaluationResult[1];
                       String linkTime = evaluationResult[2];
                       String timeOut =  evaluationResult[3];
                       
                       linkToCrawl.setHttpCode(statusCode);
                       linkToCrawl.setContentType(contentType);
                       linkToCrawl.setLinkTime(Long.valueOf(linkTime));
                       linkToCrawl.setTimeOut(Boolean.valueOf(timeOut));
                       //Storing site
                       reportThisPage.addLink(linkToCrawl);
                       if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())  //�������ӡ�email��https����
                       {                	
                    	if(linkToCrawl.isUp())  //����״̬���ж��ǿ���ʾ��url ��2xx,3xx
                    	{
                    		addGoodLinks(1);   //��������
                    	}
                    	else
                    	{
                    		this.putErrorLinks(linkToCrawl, url.toString(),false); //����������ʾ�б�
                    		addBadLinks(1);
                    	}
                       }                 
                    //�������ӹ���ͼƬurl���������ӱ�
                    previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});
                  }
                  }
                } catch (Exception e) {
                	e.printStackTrace();
                    log.addOutputLine(e.getMessage(), "Error");
                    continue;
                }
            }
        }
        
        //Recovering resourcers
        page.cleanUp();
        webClient.closeAllWindows();
        page = null;
        webClient = null;

        log.addOutputLine("Saving report in memory\r\n", "info");
        totalReport.ensureCapacity(totalReport.size());
        totalReport.add(reportThisPage);
        //����ҳ����Ϣ
        if(url.isMainSiteOnly()){//��ҳ           
           pck.saveHostPage(reportThisPage, hostls, task.getId());	
        }else{
           pck.doInsert(totalReport, task.getId());
        }
        
        //����ҳ�ڵ�url����htmlunit������
        // ��վҲ����վ������
        if (reportThisPage.haveInternalLinks()) {
            ArrayList<LinkStatus> lsArray = reportThisPage.getInternalLinks();
            //ѭ��ʵ�������ڲ�urlָ�����ҳ
            for (LinkStatus ls : lsArray) {
                try {
                    log.addOutputLine("URL: " + ls.getHref().toString() + ", Depth Level:" + ls.getHref().getDepthLevel() + " - Allowed is " + this.cfg.getAllowedDepthLevel(), "INFO");
                    if (this.cfg.isCheckSubdomains()) {  //��������վ������false��δ��ѡĬ�ϣ�
                        log.addOutputLine("Check subdomains flag is on, checking : " + ls.getHref().toString() + " is inside " + domain.getMainSiteOnly() + " ? ", "INFO");
                        if (ls.isSubDomainDepth(0)) {  //ֻ������վ����ҳ��ַ������
                            log.addOutputLine(ls.getHref().toString() + " is NOT inside " + domain.getMainSiteOnly() + "! ", "INFO");
                            continue;
                        }
                        log.addOutputLine(ls.getHref().toString() + " is clearly inside " + domain.getMainSiteOnly() + "! ", "INFO");
                    }
                    
                    if(!ls.isUp())  //����������Ӧ״̬�룬��Ϊ�������� 2xx 3xx
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL was reported as DOWN in a previous run", "INFO");
                    }
                    else if(alreadyCrawled.contains(ls.getHref().toString())) //�Ѿ�ץȡ��
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: Already crawled in a previous run", "INFO");
                    }
                    else if(!ls.isValidForCrawling()) //����ץȡ���ĵ����ͣ�Ĭ��text/html��\Э��(http),�ж��Ƿ�������أ�
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable Reason: ContentType! "+ls.getContentType() + " " + ls.getHttpCode(), "INFO");
                    }
                    else if(shouldBeExcluded(ls.getHref().toString())) //��url�ų��б���
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL is present in the Exclusion List", "INFO");
                    }
                    else if (ls.getHref().getDepthLevel() > this.cfg.getAllowedDepthLevel()) {  //����ץȡ���
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL is located deeper than the expected", "INFO");
                    }
                    else
                    {
                    	rdepth++;
                    	if(rdepth>15){  //�ݹ�/������������15����ֱ�ӷ���
                    		return ;
                    	}
                    	System.out.println("rdepth:"+rdepth);
                    	crawlURL(ls.getHref());     //�ݹ����ץȡ             
                    	rdepth--;
                    }
                    if (this.runByte == 0) {
                        break;
                    }
                } catch (Exception ex) {
                	ex.printStackTrace();
                    log.addOutputLine(ex.getMessage(), "ERROR");
                    continue;
                }
            }
        }

    }
    
    void crawlCustomURLs(){
    	//ArrayList<String>  urls = new ArrayList<String>();
    	//urls.add("http://www.njqxq.gov.cn/index.html");
    	//urls.add("http://http://www.njqxq.gov.cn/gzfw/wsbsdt/gr/hj");
    	
    	ArrayList<SpecialUrl>  ls = checkURLs(task.getSpecialUrls());
    	if(ls!=null){
        	cralwPages(ls);
    	}
    	
    }
    //����url����������http��ͨ�ԣ�������pingͨ��url object����
    ArrayList<SpecialUrl> checkURLs(ArrayList<SpecialUrl> urls){
      ArrayList<SpecialUrl> ls = null;	
      UrlReport reportThisPage = null;
      URLObject urlToCheck = null;
      LinkStatus linkToCrawl=null;
      int linksTotal = 0;
      int linksCount = 0;
      String specialPageUrl ="";
      if(urls!=null && urls.size()>0){
    	//Starting Report
    	ls = new   ArrayList<SpecialUrl>();
        linksTotal  = urls.size(); 
        linksCount = 1;
        specialPageUrl = this.domain.toString()+"/SpecailUrlsxxx";
        reportThisPage = new UrlReport(specialPageUrl); //���ͻ�����url������һ�������ҳ�С�
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle("�ͻ�����ҳ");
        reportThisPage.setId(Uuid.getUUID());
        log.addOutputLine("Crawling..."+"�ͻ�����ҳ", "Info");
        //2 ��������ҳurl�����ѷ����б�
        this.alreadyCrawled.add(specialPageUrl);  
        log.addOutputLine("start check custom url: " , "info");  
        for(SpecialUrl url:urls){    	  
    	 //1 ����URLObject
          try {
    		urlToCheck = new URLObject(url.getWz());
		  } catch (Exception ex) {
			log.addOutputLine(ex.getMessage(), "ERROR");
			continue;
		  }
    	 //2 ��������
           try {           	
              linkToCrawl = new LinkStatus(url.getWz(), this.domain);
              linkToCrawl.setCustomLinkType(url.getLx()); //������������
              linkToCrawl.setLinkText(url.getMswb());  //�����Զ������ӵ��ı�
            
              setCurrentStatus("Checking custom link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
              log.addOutputLine("Checking custom link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
              String[] evaluationResult = this.evaluateLink(linkToCrawl);//ͨ��ʵ�����Ӹ�url��ȡ��Ӧ״̬�������
              if(evaluationResult!=null){ //
                String statusCode = evaluationResult[0];
                String contentType = evaluationResult[1];
                String linkTime = evaluationResult[2];
                String timeOut =  evaluationResult[3];                
                
                log.addOutputLine(linkToCrawl.getHref() + " Got Status: "+ statusCode + " Content-Type: " + contentType, "info");
                linkToCrawl.setHttpCode(statusCode);
                linkToCrawl.setContentType(contentType);
                linkToCrawl.setLinkTime(Long.valueOf(linkTime));
                linkToCrawl.setTimeOut(Boolean.valueOf(timeOut));
                 //Storing site
                reportThisPage.addLink(linkToCrawl);
              
                //�����ӹ���url����������hash������Ѵ��ڣ����滻
                previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                //Updating counts
                if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                {                	
              	if(linkToCrawl.isUp()) //����״̬���ж��ǿ���ʾ��url ��2xx,3xx
              	{
              		ls.add(url);   //��ͨ�����Ե�specail ���Ӽ���
              		addGoodLinks(1);    //��������
              	}
              	else   //�������Ӽ������Ļ����ӱ�
              	{
              		this.putErrorLinks(linkToCrawl, specialPageUrl,false);
              		addBadLinks(1);
              	}
                }
             }
           } catch (Exception e) {
              log.addOutputLine(e.getMessage(), "Error");
              continue;
          }    	      	 
        }//for	    
      //������
        log.addOutputLine("Saving report in memory\r\n", "info");
        totalReport.ensureCapacity(totalReport.size());
        totalReport.add(reportThisPage);
        //��������ҳ������
        LinkStatus pageLink = new LinkStatus(specialPageUrl,this.domain); 
        pageLink.setId(Uuid.getUUID());
        pageLink.setHttpCode("200ok");
        pageLink.setTimeOut(false);
        pageLink.setCheckTime(new Date());   
        pageLink.setLinkText("����ͻ�����ҳ");
        LinkCheckDao lcd = new LinkCheckDao();       
		lcd.doInsert(pageLink, reportThisPage.getId(), task.getId());//��������ҳ����
       
		pck.doInsert(totalReport, task.getId(),pageLink); //����ҳ
      }//IF 
      
      return ls;
    }
    //���ظ���url����ҳ���������е����ӣ��޵ݹ���������ڶ���url�ĳ���
    void cralwPages(ArrayList<SpecialUrl> urls){
            
     for(SpecialUrl url:urls){ 
    	if (this.runByte == 0) {
             return;
         } 
    	if(alreadyCrawled.contains(url.getWz())){    	//�Ѽ��ع���ҳ	
    		continue ;
    	}
        //Starting Report
        UrlReport reportThisPage = new UrlReport(url.getWz());
        log.addOutputLine("Working on url: " + url.getWz(), "info");
        //1 ����һ����ҳ
        WebClient webClient;
        if (this.cfg.getBrowserEnum() != null) {
            webClient = new WebClient(this.cfg.getBrowserEnum());  // ����ģ������������
        } else {
            webClient = new WebClient();
        }
        if (!this.cfg.getHttpUserName().equals("") && !this.cfg.getHttpPassword().equals("")) {
            webClient.setCredentialsProvider(credentials); //������վ��¼
        }
        webClient.setCache(cacheHandler);  // ����
        webClient.getOptions().setCssEnabled(true);  //����css   applet activexĬ���ǽ�ֹ
        webClient.getOptions().setJavaScriptEnabled(false);   //��ֹjs�ű�
        webClient.getOptions().setRedirectEnabled(true);  //���ÿͻ����ض���
       // webClient.getOptions().setUseInsecureSSL(true);  // �������֤�飬�ͻ�������

        //Opening site
        log.addOutputLine("Downloading site...", "info");
        setCurrentStatus("Downloading " + url.getWz());
        HtmlPage page;
        //5 secs should be enough;
        webClient.getOptions().setTimeout(this.cfg.getTimeOut());     //socket���Ӻͻ�ȡ���ݵĳ�ʱʱ���Ϊ5s        
        try {
            page = webClient.getPage(url.getWz());
        } catch (Exception ex) {  
        	reportThisPage.setPageStatus(false);
      	    LinkStatus ls = new LinkStatus(url.getWz(),this.domain); 
      	    ls.setHttpCode("ҳ�����쳣");
      	    putErrorLinks(ls,url.getWz(),true); // �����������
      	    totalReport.ensureCapacity(totalReport.size()); //���뱨���б�
            totalReport.add(reportThisPage);
             
  	        pck.doInsert(totalReport, task.getId()); //����
      	   // warner.sendMessage();    //���ͱ�����Ϣ��        	
            log.addOutputLine("Unable to crawl url: " + url.getWz() + " " + ex.getMessage(), "ERROR");
            continue;
        }
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle(page.getTitleText());
        log.addOutputLine("Crawling..."+page.getTitleText(), "Info");
        //2 �����ع�����ҳurl�����ѷ����б�
        this.alreadyCrawled.add(url.getWz());  
        
        //3 ��ȡ��ҳ��������
        List<HtmlAnchor> links = page.getAnchors();
        int linksTotal = links.size();        
        
        log.addOutputLine("Total links detected: " + linksTotal + " link(s)", "info");
        int linksCount = 1;
        //4  ѭ�����Ӳ���һҳ�е�url
        for (HtmlAnchor link : links) {
            if (this.runByte == 0) {  //�˳�ץȡ
                return;
            }
            LinkStatus linkToCrawl = null;
            try {
              String ss = page.getFullyQualifiedUrl(link.getHrefAttribute()).toString();
              if(verifyURL(ss)){ 	//��֤htmlunit��ȡ��url�Ƿ�Ϸ�           	
                linkToCrawl = new LinkStatus(ss, this.domain);
                linkToCrawl.setLinkText(link.asText());
                setCurrentStatus("Checking link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
                log.addOutputLine("Checking link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
                String[] evaluationResult = this.evaluateLink(linkToCrawl);//ͨ��ʵ�����Ӹ�url��ȡ��Ӧ״̬�������
                if(evaluationResult!=null){ //
                  String statusCode = evaluationResult[0];
                  String contentType = evaluationResult[1];
                  String linkTime = evaluationResult[2];
                  String timeOut =  evaluationResult[3];
                  
                  log.addOutputLine(linkToCrawl.getHref() + " Got Status: "+ statusCode + " Content-Type: " + contentType, "info");
                  linkToCrawl.setHttpCode(statusCode);
                  linkToCrawl.setContentType(contentType);
                  linkToCrawl.setLinkTime(Long.valueOf(linkTime));
                  linkToCrawl.setTimeOut(Boolean.valueOf(timeOut));
                  //Storing site
                  reportThisPage.addLink(linkToCrawl);
                
                  //�����ӹ���url����������hash������Ѵ��ڣ����滻
                  previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                  //Updating counts
                  if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                  {                	
                	if(linkToCrawl.isUp()) //����״̬���ж��ǿ���ʾ��url ��2xx,3xx
                	{
                		addGoodLinks(1);    //��������
                	}
                	else   //�������Ӽ������Ļ����ӱ�
                	{
                		this.putErrorLinks(linkToCrawl, url.getWz(),false);
                		addBadLinks(1);
                	}
                  }
               }
              }//if
             } catch (Exception e) {
                log.addOutputLine(e.getMessage(), "Error");
                continue;
            }

        }        
        //5 ����ͼƬ����
        List<HtmlImage> images = null;
        if (this.cfg.isImageCheck()) {
            log.addOutputLine("Collecting img tags...", "Info");
            images = (List<HtmlImage>) page.getByXPath("//img");
        }
        if (this.cfg.isImageCheck()) {
            log.addOutputLine("Checking src attribute on IMG tags...", "info");
            int imagesTotal = links.size();
            log.addOutputLine("Total images detected: " + linksTotal + " image(s)", "info");
            int imageCount = 1;

            for (HtmlImage image : images) {
                if (this.runByte == 0) {
                    return;
                }
                LinkStatus linkToCrawl;
                try {
                  String ss = page.getFullyQualifiedUrl(image.getSrcAttribute()).toString();
                  if(verifyURL(ss)){ 	//��֤htmlunit��ȡ��url�Ƿ�Ϸ�
                    linkToCrawl = new LinkStatus(ss, this.domain, true);
                    linkToCrawl.setLinkText("ͼƬ");
                    setCurrentStatus("Checking image " + (imageCount++) + " of " + imagesTotal + " : " + linkToCrawl.getHref());
                    String[] evaluationResult = this.evaluateLink(linkToCrawl);//ͨ��ʵ�����Ӹ�url��ȡ��Ӧ״̬�������
                    if(evaluationResult!=null ){
                       String statusCode = evaluationResult[0];
                       String contentType = evaluationResult[1];
                       String linkTime = evaluationResult[2];
                       String timeOut =  evaluationResult[3];
                       linkToCrawl.setHttpCode(statusCode);
                       linkToCrawl.setContentType(contentType);
                       linkToCrawl.setLinkTime(Long.valueOf(linkTime));
                       linkToCrawl.setTimeOut(Boolean.valueOf(timeOut));
                       //Storing site
                       reportThisPage.addLink(linkToCrawl);
                       if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())  //�������ӡ�email����
                       {                	
                    	if(linkToCrawl.isUp())  //����״̬���ж��ǿ���ʾ��url ��2xx,3xx
                    	{
                    		addGoodLinks(1);   //��������
                    	}
                    	else
                    	{
                    		this.putErrorLinks(linkToCrawl, url.getWz(),false); //����������ʾ�б�
                    		addBadLinks(1);
                    	}
                       }                 
                    //�������ӹ���ͼƬurl���������ӱ�
                       previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});
                     }
                  }//if
                } catch (Exception e) {  //�Ƿ�url�ᴥ���쳣�纬javascript url
                    log.addOutputLine(e.getMessage(), "Error");
                    e.printStackTrace();
                    continue;
                }
            }
        }
      
        //�ͷ���ԴRecovering resourcers
        page.cleanUp();
        webClient.closeAllWindows();
        page = null;
        webClient = null;
        //����
        log.addOutputLine("Saving report in memory\r\n", "info");
        totalReport.ensureCapacity(totalReport.size());
        totalReport.add(reportThisPage);  
       
        pck.doInsert(totalReport, task.getId()); //�������
     } //for
    }
    
    private void generateExclusionRegex()
    {
      int startFlag = 0;
      if(this.cfg.getExclusionListArray()!=null){	   	
    	
    	for (String exclusionString : this.cfg.getExclusionListArray())
    	{
    		if(startFlag == 0)
    			this.exclusionRegex = ".*"+exclusionString.toLowerCase()+".*";
    		else
    			this.exclusionRegex = "|.*"+exclusionString.toLowerCase()+".*";
    		startFlag = 1;
    	}
      }
    }
    private boolean shouldBeExcluded(String href) {
      if(this.cfg.getExclusionListArray()!=null){	       	
    	if(cfg.getExclusionListArray().isEmpty())
    		return false;
    	
    	if (this.exclusionRegex.equals(""))
    	{
    		generateExclusionRegex();
    	}
    	if (href.trim().toLowerCase().matches(exclusionRegex)) {
            return true;
        }
        return false;
      }else{
    	  return false;
      }
    }

    public boolean isReportReady() {
        return !this.totalReport.isEmpty();
    }

    public ArrayList<UrlReport> getReport() {
        return this.totalReport;
    }

    public void stopCrawling() {
        this.runByte = 0;
    }
    //��ǰ���ӹ���url������null�� �Է���Ҫ�������ʵ��ȥHTTPConnector���ӣ�������Ӧ״̬������ͣ��ʼ���# url������,
    private String[] evaluateLink(LinkStatus ls) throws Exception {
        credentials.clear();
        if (ls.isInternalLink()) {  //վ������
            if (!this.cfg.getHttpUserName().equals("") && !this.cfg.getHttpPassword().equals("")) {
                Authenticator.setDefault(new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(cfg.getHttpUserName(), cfg.getHttpPassword().toCharArray());
                    }
                });

                credentials.setCredentials(
                        new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM),
                        new UsernamePasswordCredentials(this.cfg.getHttpUserName(), this.cfg.getHttpPassword()));

            }
        }
        //������ʼ���#url��ַ
        HTTPConnector httpConn = null;
        HttpURLConnection conn = null;
        //�ʼ���ַ�������ӡ�https���Ӳ�������
        if (ls.getHrefUnformatted().startsWith("mailto") == false && ls.getHrefUnformatted().indexOf("#") == -1
        		    && ls.getHref().getProtocol().trim().equalsIgnoreCase("https") == false) {
            try {
                if (previousCheckUrl.containsKey(ls.getHref().toString())) {  //��urlǰ�����ӹ���
                    return previousCheckUrl.get(ls.getHref().toString());
                   // return null;  // ��ǰ���Թ��ģ�ֱ�ӷ���null by fh

                }
                
                httpConn = new HTTPConnector(ls.getHref().toString(),this.cfg.getTimeOut());
                conn = httpConn.getConnection();
                String status = "";
                String contentType = "";
                if(conn!=null){
                  status = conn.getHeaderField(null);  //�磺http/1.1 200 OK ��why  getResponseCode status code              
                  if(status==null){  // ����������״̬�뷵��Ϊnull 
                	  status = "Exception"; // �� http://www.njqxq.gov.cn:9088��ת����ҳ����ʾ���ӱ�����
                  }
                  contentType = conn.getContentType();
                  if(contentType == null){
                	  contentType ="";
                  }
                }else{ //Ϊ�գ�˵�����ӳ����쳣��״̬��Ϊ�쳣�ַ���
                  status = httpConn.getExceptionCode();	
                  log.addOutputLine(httpConn.getExceptionCode(), "Error");
                }
                return new String[] { status, contentType,String.valueOf(httpConn.getLinkTime()),String.valueOf(httpConn.isTimeOut())};
            } catch (Exception ex) {
            	ex.printStackTrace();
            	log.addOutputLine(httpConn.getExceptionCode(), "Error");
                return new String[] {httpConn.getExceptionCode(), "",String.valueOf(httpConn.getLinkTime()),String.valueOf(httpConn.isTimeOut())};
            }finally{
            	if(conn!=null){
            		conn.disconnect();
                    conn = null;
            	}
            }
        } else if (ls.getHref().toString().indexOf("#") >= 0) {  // ҳ�������url
        	 return new String[] { "200 Self Anchor", "","0","false"};       //δ��飬Ĭ������     
        } else if(ls.getHref().getProtocol().trim().equalsIgnoreCase("https")){
        	return new String[] { "200 Https Address", "","0","false"};  
        } else{
        	return new String[] { "200 Email Address", "","0","false"}; 
        }

    }
    
    void printErrors(){
   	 Iterator iter = errorLinks.entrySet().iterator();
	     while(iter.hasNext()){
	    	 Map.Entry entry = (Map.Entry)iter.next();
	    	 String key = (String)entry.getKey();
	    	 LinkStatus  link = (LinkStatus) entry.getValue();
	         System.out.println(key+" "+link.getHttpCode()+" "+link.getLinkText());
	     }  	 	   	
   }
    
    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getGoodLinks() {
        return goodLinks;
    }

    private void addGoodLinks(int goodLink) {
        this.goodLinks += goodLink;
    }

    public int getBadLinks() {
        return badLinks;
    }

    private void addBadLinks(int badLink) {
        this.badLinks += badLink;
    }

	public HashMap<String, LinkStatus> getErrorLinks() {
		return errorLinks;
	}


	public ArrayList<UrlReport> getTotalReport() {
		return totalReport;
	}


	public Date getStartTime() {
		return startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public Task getTask() {
		return task;
	}
}
