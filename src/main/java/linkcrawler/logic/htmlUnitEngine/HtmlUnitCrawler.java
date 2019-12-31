/*
 *   使用htmlunit爬行网站，深度优先算法，深度是url的子路径数
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

    private URLObject domain;  //抓取的主站
    private ArrayList<String> alreadyCrawled;  //htmlunit已加载页的url列表
    private HashMap<String, String[]> previousCheckUrl; //httpconnection连接检查过的urls
    private ArrayList<UrlReport> totalReport;
    private byte runByte = 1;  // 控制抓取线程是否退出
    private Cache cacheHandler = new Cache();
    private String currentStatus = "Crawler Starting......";
    private int goodLinks = 0;
    private int badLinks = 0;
    private final CredentialsProvider credentials = new BasicCredentialsProvider();  // 网站认证
    private LogController log;
    private Configuration cfg; //配置参数对象
    private TableView<LinkModel> linkTable = null;
    private String exclusionRegex = "";   // 过滤正则，符合该正则的不被抓取
    
    private int rdepth=0 ;  //递归调用crawlURL的深度
    private HashMap<String,LinkStatus> errorLinks ;
    private WarnMonitor warner ;
    
    private Task task ;  //对应的任务
    private TaskDao  taskdao;  //任务表存取    
    private PageCheckDao pck ;  //页存取
    private CheckTaskDao ctd ; //任务记录存取
    private WarnsDao wd ;   //报警记录存取
    
    private Date startTime ;//一次检查任务开始检查时间
    private Date endTime ;////一次检查任务结束检查时间
    
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
        warner = new WarnMonitor(this.cfg,errorLinks);  // 加入报警器
        
    }
    public HtmlUnitCrawler(Configuration cfg, LogController log) {
        this("HtmlUnitCrawler", log); 
        this.cfg = cfg;       
        this.domain = cfg.getDomain();
        warner = new WarnMonitor(this.cfg,errorLinks);  // 加入报警器
      
    }
    
    public HtmlUnitCrawler(Configuration cfg, LogController log,Task task) {
        this("HtmlUnitCrawler", log); 
        this.cfg = cfg;       
        this.domain = cfg.getDomain();
        warner = new WarnMonitor(this.cfg,errorLinks);  // 加入报警器
        this.task = task;
        
    }
    
    
    @Override
    public void run() {      	
     
      try {
		  long st = System.currentTimeMillis();
		  this.startTime = new Date();
		  System.out.println(task.getName()+"  crawl start ");
		  log.addOutputLine(this.cfg.getName(), "info");
		 
		  if(this.isHostReach()){ //主站可达
		    crawlURL(this.domain); // 根据深度进行通用检查
		   
		    crawlCustomURLs();    //根据指定的url进行检查
		   
		    this.endTime = new Date();
		    ctd.doInsert(this);   //保存任务检查记录
		    
//		    this.warn();  //一次检查完后报警
		    
		    System.out.println("crawled links:"+previousCheckUrl.size());
		    System.out.println("downloaded page:"+alreadyCrawled.size());       
		   
		  }
		  printErrors();
		  System.out.println("toal time:"+(System.currentTimeMillis()-st));
		  setCurrentStatus("Crawler Stopped,Crawler time:"+(System.currentTimeMillis()-st)/1000+"s");
		  //输出execl结果
		  saveAsExcel();
		  //清除各项存储，恢复各种属性
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
	  dofinalize();	  
	}
	 System.out.println(task.getName()+" crawl over");
    }    
    //处理主站不可达，1分钟内轮询，若仍不可访问，则返回false
    private boolean isHostReach(){
      boolean bres = false;	
      int cnt = 0;
      while (cnt<6){
       cnt++;      
       if(this.domain.isURLReachable()){
          return true;  	
       }	
       try {
		    Thread.sleep(1000*10);  //延时10秒
	    } catch (InterruptedException e) {		
	    }
       cnt++;
      }
      //不可达
      LinkStatus ls = new LinkStatus(this.domain.toString(),this.domain); 
	  ls.setHttpCode("主站链接异常");
	  ls.setLinkText("主页");
	  putErrorLinks(ls, domain.toString(),false);
	  pck.saveHostPage(null, ls, task.getId());  //保存链接记录信息
	  this.endTime = new Date();
	  ctd.doInsert(this);   //保存任务检查记录
	  this.warn();;    //发送报警信息；
	  log.addOutputLine("主站异常", "info");
      
      return bres;	
    }
    
//     报警:发送保存报警信息
    public void warn(){
    	this.warner.sendMessage();
    	Warn warn = warner.getWarn();
    	warn.setTask_id(this.task.getId());
    	wd.doInsert(warn);
    }
    
    //以execl文件方式保存结果
    private void saveAsExcel(){
        ReportController rc = new ReportController(domain.toString(), this.getReport(), ReportType.EXCEL);
  	  rc.start();
  	  try {
  		Thread.sleep(10000);
  	  } catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	  }  //for    execel save
        //清除各项存储，恢复各种属性   	
    }
    //线程结束后，设置线程可执行标志，清除相应列表对象，便于周期调度执行。，
    private void dofinalize(){
    	this.runByte = 1;   //便于下次执行
    	goodLinks = 0;
        badLinks = 0;
        cacheHandler.clear();
    	alreadyCrawled.clear();
        totalReport.clear();
        previousCheckUrl.clear(); 
        errorLinks.clear(); 
        
        rdepth = 0 ;
        log.endLog();
        this.log = new LogController(true); //生成新的日志文件
    	
    }
    //将坏连接加入图形界面的表中，重复的坏链接 被消重
    private void putErrorLinks(LinkStatus ls, String locatedAt,boolean isPageError){
    	try {
    	    LinkStatus link = null;
    	    if(!isPageError){
    	    	link = errorLinks.get(ls.getHref().toString());    	
    	    }else{
    	    	 errorLinks.put(ls.getHref().toString()+" ", ls);  
    	    }
			if(link == null){	//hash表中没有则加入
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
    
    // 验证url的格式是否合法
    public boolean verifyURL(String urls){
    	
    	try {
			URL url = new URL(urls);
		} catch (MalformedURLException e) {
			return false;
		}
    	return true;
    }
    //递归加载页，解析测试url
    @SuppressWarnings("unchecked")
	private void crawlURL(URLObject url) {
        if (this.runByte == 0) {
            return;
        }        
        //Starting Report
        UrlReport reportThisPage = new UrlReport(url.toString());
        log.addOutputLine("Working on url: " + url, "info");
        //加载一个网页
        WebClient webClient;
        if (this.cfg.getBrowserEnum() != null) {
            webClient = new WebClient(this.cfg.getBrowserEnum());  // 设置模拟的浏览器类型
        } else {
            webClient = new WebClient();
        }
        if (!this.cfg.getHttpUserName().equals("") && !this.cfg.getHttpPassword().equals("")) {
            webClient.setCredentialsProvider(credentials); //设置网站登录
        }
        webClient.setCache(cacheHandler);  // 缓存
        webClient.getOptions().setCssEnabled(false);  //加载css   applet activex默认是禁止
        webClient.getOptions().setJavaScriptEnabled(false);   //禁止js脚本
        webClient.getOptions().setRedirectEnabled(true);  //启用客户端重定向
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //  webClient.getOptions().setUseInsecureSSL(true);  // 无需服务方证书，客户均链接
        //Opening site
        log.addOutputLine("Downloading site...", "info");
        setCurrentStatus("Downloading " + url);
        HtmlPage page;
        //5 secs should be enough;
        webClient.getOptions().setTimeout(this.cfg.getTimeOut());     //socket连接和获取数据的超时时间各为5s        
        try {
            page = webClient.getPage(url.toString());
        } catch (Exception ex) {   //网页加载错，则直接返回，会生成报告
        	reportThisPage.setPageStatus(false);
      	    LinkStatus ls = new LinkStatus(url.toString(),this.domain); 
      	    putErrorLinks(ls,url.toString(),true); // 加入出错链接
    	    totalReport.ensureCapacity(totalReport.size()); //加入报告列表
            totalReport.add(reportThisPage);
      	    if(url.isMainSiteOnly()){
      	      ls.setHttpCode("主页加载异常");
      	      pck.saveHostPage(reportThisPage, ls, task.getId());
      	    }else{
      	      ls.setHttpCode("页加载异常");	
      	      pck.doInsert(totalReport, task.getId());
      	    }
      	   
      	   // warner.sendMessage();    //发送报警信息；
      	   	log.addOutputLine("Unable to load url: " + url + " " + ex.getMessage(), "ERROR");
            return;
        }
        LinkStatus hostls = null;
        if(url.isMainSiteOnly()){  //主页ok
        	 hostls = new LinkStatus(url.toString(),this.domain);
             hostls.setHttpCode("200ok");
             hostls.setLinkText("主页");
        }
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle(page.getTitleText());
        log.addOutputLine("Crawling..."+page.getTitleText(), "Info");
        //将加载过的网页url加入已访问列表
        this.alreadyCrawled.add(url.toString());  
        
        // 获取网页所有链接
        List<HtmlAnchor> links = page.getAnchors();
        int linksTotal = links.size();
        
        
        log.addOutputLine("Total links detected: " + linksTotal + " link(s)", "info");
        int linksCount = 1;
        // 循环连接测试一页中的url
        for (HtmlAnchor link : links) {
            if (this.runByte == 0) {  //退出抓取
                return;
            }
            LinkStatus linkToCrawl = null;
            try {    
              String urls = page.getFullyQualifiedUrl(link.getHrefAttribute()).toString();
              if(verifyURL(urls)){ 	//验证htmlunit提取的url是否合法
                linkToCrawl = new LinkStatus(urls, this.domain);
                linkToCrawl.setLinkText(link.asText());
                setCurrentStatus("Checking link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
                log.addOutputLine("Checking link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
                String[] evaluationResult = this.evaluateLink(linkToCrawl);//通过实际连接该url获取响应状态码和类型
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
                
                  //将连接过的url放入已连接hash表，如果已存在，则替换
                  previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                  //Updating counts
                  if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                  {                	
                	if(linkToCrawl.isUp()) //根据状态码判断是可显示的url ，2xx,3xx
                	{
                		addGoodLinks(1);    //好连接数
                	}
                	else   //将坏连接加入界面的坏连接表
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
        //处理图片连接
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
                  if(verifyURL(urls)){ 	//验证htmlunit提取的url是否合法
                    linkToCrawl = new LinkStatus(urls, this.domain, true);
                    linkToCrawl.setLinkText("图片");
                    setCurrentStatus("Checking image " + (imageCount++) + " of " + imagesTotal + " : " + linkToCrawl.getHref());
                    String[] evaluationResult = this.evaluateLink(linkToCrawl);//通过实际连接该url获取响应状态码和类型
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
                       if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())  //非自连接、email、https链接
                       {                	
                    	if(linkToCrawl.isUp())  //根据状态码判断是可显示的url ，2xx,3xx
                    	{
                    		addGoodLinks(1);   //好连接数
                    	}
                    	else
                    	{
                    		this.putErrorLinks(linkToCrawl, url.toString(),false); //坏链加入显示列表
                    		addBadLinks(1);
                    	}
                       }                 
                    //将已连接过的图片url放入已连接表
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
        //保存页面信息
        if(url.isMainSiteOnly()){//主页           
           pck.saveHostPage(reportThisPage, hostls, task.getId());	
        }else{
           pck.doInsert(totalReport, task.getId());
        }
        
        //过滤页内的url，供htmlunit加载用
        // 子站也属于站内链接
        if (reportThisPage.haveInternalLinks()) {
            ArrayList<LinkStatus> lsArray = reportThisPage.getInternalLinks();
            //循环实际下载内部url指向的网页
            for (LinkStatus ls : lsArray) {
                try {
                    log.addOutputLine("URL: " + ls.getHref().toString() + ", Depth Level:" + ls.getHref().getDepthLevel() + " - Allowed is " + this.cfg.getAllowedDepthLevel(), "INFO");
                    if (this.cfg.isCheckSubdomains()) {  //若设置子站检查参数false（未勾选默认）
                        log.addOutputLine("Check subdomains flag is on, checking : " + ls.getHref().toString() + " is inside " + domain.getMainSiteOnly() + " ? ", "INFO");
                        if (ls.isSubDomainDepth(0)) {  //只允许子站的主页网址被加载
                            log.addOutputLine(ls.getHref().toString() + " is NOT inside " + domain.getMainSiteOnly() + "! ", "INFO");
                            continue;
                        }
                        log.addOutputLine(ls.getHref().toString() + " is clearly inside " + domain.getMainSiteOnly() + "! ", "INFO");
                    }
                    
                    if(!ls.isUp())  //根据连接响应状态码，认为可以爬行 2xx 3xx
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL was reported as DOWN in a previous run", "INFO");
                    }
                    else if(alreadyCrawled.contains(ls.getHref().toString())) //已经抓取过
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: Already crawled in a previous run", "INFO");
                    }
                    else if(!ls.isValidForCrawling()) //根据抓取的文档类型（默认text/html）\协议(http),判断是否可以下载，
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable Reason: ContentType! "+ls.getContentType() + " " + ls.getHttpCode(), "INFO");
                    }
                    else if(shouldBeExcluded(ls.getHref().toString())) //在url排除列表里
                    {
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL is present in the Exclusion List", "INFO");
                    }
                    else if (ls.getHref().getDepthLevel() > this.cfg.getAllowedDepthLevel()) {  //超过抓取深度
                    	log.addOutputLine("Declaring " + ls.getHref().toString() + " as not crawleable. Reason: URL is located deeper than the expected", "INFO");
                    }
                    else
                    {
                    	rdepth++;
                    	if(rdepth>15){  //递归/链接跳数超过15次则直接返回
                    		return ;
                    	}
                    	System.out.println("rdepth:"+rdepth);
                    	crawlURL(ls.getHref());     //递归调用抓取             
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
    //给定url串，测试其http联通性，返回能ping通的url object链表
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
        reportThisPage = new UrlReport(specialPageUrl); //将客户定制url看做在一个虚拟的页中。
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle("客户定制页");
        reportThisPage.setId(Uuid.getUUID());
        log.addOutputLine("Crawling..."+"客户定制页", "Info");
        //2 将虚拟网页url加入已访问列表
        this.alreadyCrawled.add(specialPageUrl);  
        log.addOutputLine("start check custom url: " , "info");  
        for(SpecialUrl url:urls){    	  
    	 //1 生成URLObject
          try {
    		urlToCheck = new URLObject(url.getWz());
		  } catch (Exception ex) {
			log.addOutputLine(ex.getMessage(), "ERROR");
			continue;
		  }
    	 //2 测试链接
           try {           	
              linkToCrawl = new LinkStatus(url.getWz(), this.domain);
              linkToCrawl.setCustomLinkType(url.getLx()); //设置链接类型
              linkToCrawl.setLinkText(url.getMswb());  //设置自定义链接的文本
            
              setCurrentStatus("Checking custom link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
              log.addOutputLine("Checking custom link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
              String[] evaluationResult = this.evaluateLink(linkToCrawl);//通过实际连接该url获取响应状态码和类型
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
              
                //将连接过的url放入已连接hash表，如果已存在，则替换
                previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                //Updating counts
                if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                {                	
              	if(linkToCrawl.isUp()) //根据状态码判断是可显示的url ，2xx,3xx
              	{
              		ls.add(url);   //将通过测试的specail 链接加入
              		addGoodLinks(1);    //好连接数
              	}
              	else   //将坏连接加入界面的坏连接表
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
      //保存结果
        log.addOutputLine("Saving report in memory\r\n", "info");
        totalReport.ensureCapacity(totalReport.size());
        totalReport.add(reportThisPage);
        //设置虚拟页的链接
        LinkStatus pageLink = new LinkStatus(specialPageUrl,this.domain); 
        pageLink.setId(Uuid.getUUID());
        pageLink.setHttpCode("200ok");
        pageLink.setTimeOut(false);
        pageLink.setCheckTime(new Date());   
        pageLink.setLinkText("虚拟客户定制页");
        LinkCheckDao lcd = new LinkCheckDao();       
		lcd.doInsert(pageLink, reportThisPage.getId(), task.getId());//保存虚拟页链接
       
		pck.doInsert(totalReport, task.getId(),pageLink); //保存页
      }//IF 
      
      return ls;
    }
    //加载给定url的网页，测试其中的链接，无递归操作，用于定制url的场合
    void cralwPages(ArrayList<SpecialUrl> urls){
            
     for(SpecialUrl url:urls){ 
    	if (this.runByte == 0) {
             return;
         } 
    	if(alreadyCrawled.contains(url.getWz())){    	//已加载过该页	
    		continue ;
    	}
        //Starting Report
        UrlReport reportThisPage = new UrlReport(url.getWz());
        log.addOutputLine("Working on url: " + url.getWz(), "info");
        //1 加载一个网页
        WebClient webClient;
        if (this.cfg.getBrowserEnum() != null) {
            webClient = new WebClient(this.cfg.getBrowserEnum());  // 设置模拟的浏览器类型
        } else {
            webClient = new WebClient();
        }
        if (!this.cfg.getHttpUserName().equals("") && !this.cfg.getHttpPassword().equals("")) {
            webClient.setCredentialsProvider(credentials); //设置网站登录
        }
        webClient.setCache(cacheHandler);  // 缓存
        webClient.getOptions().setCssEnabled(true);  //加载css   applet activex默认是禁止
        webClient.getOptions().setJavaScriptEnabled(false);   //禁止js脚本
        webClient.getOptions().setRedirectEnabled(true);  //启用客户端重定向
       // webClient.getOptions().setUseInsecureSSL(true);  // 无需服务方证书，客户均链接

        //Opening site
        log.addOutputLine("Downloading site...", "info");
        setCurrentStatus("Downloading " + url.getWz());
        HtmlPage page;
        //5 secs should be enough;
        webClient.getOptions().setTimeout(this.cfg.getTimeOut());     //socket连接和获取数据的超时时间各为5s        
        try {
            page = webClient.getPage(url.getWz());
        } catch (Exception ex) {  
        	reportThisPage.setPageStatus(false);
      	    LinkStatus ls = new LinkStatus(url.getWz(),this.domain); 
      	    ls.setHttpCode("页加载异常");
      	    putErrorLinks(ls,url.getWz(),true); // 加入出错链接
      	    totalReport.ensureCapacity(totalReport.size()); //加入报告列表
            totalReport.add(reportThisPage);
             
  	        pck.doInsert(totalReport, task.getId()); //保存
      	   // warner.sendMessage();    //发送报警信息；        	
            log.addOutputLine("Unable to crawl url: " + url.getWz() + " " + ex.getMessage(), "ERROR");
            continue;
        }
        reportThisPage.setPageStatus(true);
        reportThisPage.setTitle(page.getTitleText());
        log.addOutputLine("Crawling..."+page.getTitleText(), "Info");
        //2 将加载过的网页url加入已访问列表
        this.alreadyCrawled.add(url.getWz());  
        
        //3 获取网页所有链接
        List<HtmlAnchor> links = page.getAnchors();
        int linksTotal = links.size();        
        
        log.addOutputLine("Total links detected: " + linksTotal + " link(s)", "info");
        int linksCount = 1;
        //4  循环连接测试一页中的url
        for (HtmlAnchor link : links) {
            if (this.runByte == 0) {  //退出抓取
                return;
            }
            LinkStatus linkToCrawl = null;
            try {
              String ss = page.getFullyQualifiedUrl(link.getHrefAttribute()).toString();
              if(verifyURL(ss)){ 	//验证htmlunit提取的url是否合法           	
                linkToCrawl = new LinkStatus(ss, this.domain);
                linkToCrawl.setLinkText(link.asText());
                setCurrentStatus("Checking link " + (linksCount) + " of " + linksTotal + " : " + linkToCrawl.getHref());
                log.addOutputLine("Checking link " + (linksCount++) + " of " + linksTotal + " : " + linkToCrawl.getHref(), "info");
                String[] evaluationResult = this.evaluateLink(linkToCrawl);//通过实际连接该url获取响应状态码和类型
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
                
                  //将连接过的url放入已连接hash表，如果已存在，则替换
                  previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});

                  //Updating counts
                  if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())
                  {                	
                	if(linkToCrawl.isUp()) //根据状态码判断是可显示的url ，2xx,3xx
                	{
                		addGoodLinks(1);    //好连接数
                	}
                	else   //将坏连接加入界面的坏连接表
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
        //5 处理图片连接
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
                  if(verifyURL(ss)){ 	//验证htmlunit提取的url是否合法
                    linkToCrawl = new LinkStatus(ss, this.domain, true);
                    linkToCrawl.setLinkText("图片");
                    setCurrentStatus("Checking image " + (imageCount++) + " of " + imagesTotal + " : " + linkToCrawl.getHref());
                    String[] evaluationResult = this.evaluateLink(linkToCrawl);//通过实际连接该url获取响应状态码和类型
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
                       if (!linkToCrawl.isSelfAnchor() && !linkToCrawl.isEmailAddress() && !linkToCrawl.isHttpsAddrress())  //非自连接、email链接
                       {                	
                    	if(linkToCrawl.isUp())  //根据状态码判断是可显示的url ，2xx,3xx
                    	{
                    		addGoodLinks(1);   //好连接数
                    	}
                    	else
                    	{
                    		this.putErrorLinks(linkToCrawl, url.getWz(),false); //坏链加入显示列表
                    		addBadLinks(1);
                    	}
                       }                 
                    //将已连接过的图片url放入已连接表
                       previousCheckUrl.put(linkToCrawl.getHref().toString(), new String[]{ linkToCrawl.getHttpCode(), linkToCrawl.getContentType(),linkTime,timeOut});
                     }
                  }//if
                } catch (Exception e) {  //非法url会触发异常如含javascript url
                    log.addOutputLine(e.getMessage(), "Error");
                    e.printStackTrace();
                    continue;
                }
            }
        }
      
        //释放资源Recovering resourcers
        page.cleanUp();
        webClient.closeAllWindows();
        page = null;
        webClient = null;
        //保存
        log.addOutputLine("Saving report in memory\r\n", "info");
        totalReport.ensureCapacity(totalReport.size());
        totalReport.add(reportThisPage);  
       
        pck.doInsert(totalReport, task.getId()); //保存入库
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
    //以前连接过的url，返回null； 对符合要求的连接实际去HTTPConnector连接，返回响应状态码和类型，邮件及# url不连接,
    private String[] evaluateLink(LinkStatus ls) throws Exception {
        credentials.clear();
        if (ls.isInternalLink()) {  //站内连接
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
        //处理非邮件、#url地址
        HTTPConnector httpConn = null;
        HttpURLConnection conn = null;
        //邮件地址、自链接、https链接不被测试
        if (ls.getHrefUnformatted().startsWith("mailto") == false && ls.getHrefUnformatted().indexOf("#") == -1
        		    && ls.getHref().getProtocol().trim().equalsIgnoreCase("https") == false) {
            try {
                if (previousCheckUrl.containsKey(ls.getHref().toString())) {  //该url前面连接过，
                    return previousCheckUrl.get(ls.getHref().toString());
                   // return null;  // 以前测试过的，直接返回null by fh

                }
                
                httpConn = new HTTPConnector(ls.getHref().toString(),this.cfg.getTimeOut());
                conn = httpConn.getConnection();
                String status = "";
                String contentType = "";
                if(conn!=null){
                  status = conn.getHeaderField(null);  //如：http/1.1 200 OK ，why  getResponseCode status code              
                  if(status==null){  // 链接正常但状态码返回为null 
                	  status = "Exception"; // 如 http://www.njqxq.gov.cn:9088被转向到主页或提示链接被重置
                  }
                  contentType = conn.getContentType();
                  if(contentType == null){
                	  contentType ="";
                  }
                }else{ //为空，说明连接出现异常，状态码为异常字符串
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
        } else if (ls.getHref().toString().indexOf("#") >= 0) {  // 页内自身的url
        	 return new String[] { "200 Self Anchor", "","0","false"};       //未检查，默认正常     
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
