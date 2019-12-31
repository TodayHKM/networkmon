/**
 * 命令行版的程序入口
 */
package linkcrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.Task;
import linkcrawler.datatypes.URLObject;
import linkcrawler.log.LogController;
import linkcrawler.logic.htmlUnitEngine.Configuration;
import linkcrawler.logic.htmlUnitEngine.HtmlUnitCrawler;
import linkcrawler.manager.TaskManager;
import linkcrawler.report.ReportController;
import linkcrawler.report.ReportType;

public class CrawlerMain {

    static Task getTask(int id){
    	 Task ta = new Task();
		 ta.setId(String.valueOf(id));
		 ta.setName("栖霞区政府网站"+id);
		 ta.setGoal("http://www.njqxq.gov.cn/index.html");
		 ta.setScheduleTime(10);  //10分钟调度一次
		 
		 ta.setWarnEmail("15850675602@139.com");
		 ta.setEmailHost("smtp.163.com");
		 ta.setSendEmail("dalei_main2000@163.com");
		 ta.setEmailPassword("fanghong2006");
		 
		 return ta;
    }
   
	public static void main(String[] args) {
		
		 TaskManager jm = new TaskManager();
//		 Task ta1 = getTask(1);
//		 Task ta2 = getTask(2);
//		 Task ta3 = getTask(1);
//
//		 jm.runTask(ta1);
//		 jm.runTask(ta2);
//		 jm.runTask(ta3);
//
	/*	
		 HtmlUnitCrawler job = null;
		 LogController mainlog = new LogController(true);
		 ArrayList<String> exclusionListArray = new ArrayList<String>();
		 String url = "http://www.njqxq.gov.cn/index.html";
		 URLObject urlCheck = null;
		 try {
			urlCheck = new URLObject(url, true);
		 } catch (Exception e) {
			e.printStackTrace();
			return ;
		 }
		 Configuration config = new Configuration();
		 config.setDomain(urlCheck);
		 config.setAllowedDepthLevel(1);  //设置url子路径深度
		 config.setExclusionListArray(exclusionListArray);
		 //启动检测任务
		 ScheduledThreadPoolExecutor ses =  newScheduledThreadPool(3);		 
		 job = new HtmlUnitCrawler(config, mainlog);
		 ses.scheduleAtFixedRate(job, 0, 3, TimeUnit.MINUTES);
		 
		 job.start();
		 
	     while(job.isAlive()){  //等待爬行结束
	    	 
	     }
	     //生成execl报告
	     ReportController rc = new ReportController(url, job.getReport(), ReportType.EXCEL);
	     rc.start();
	     //输出错误结果
	     System.out.println("bad links:");
	     HashMap<String,LinkStatus> errorLinks = job.getErrorLinks();
	     Iterator iter = errorLinks.entrySet().iterator();
	     while(iter.hasNext()){
	    	 Map.Entry entry = (Map.Entry)iter.next();
	    	 String key = (String)entry.getKey();
	    	 LinkStatus  link = (LinkStatus) entry.getValue();
	         System.out.println(key+" "+link.getHttpCode()+" "+link.getLinkText());
	     }  	 	     
	    //移除任务
	    job.stopCrawling();
	    ses.remove(job);
	    */
	    //
	    try {
			Thread.sleep(10*60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    jm.stopTask("1");
	    jm.stopTask("2");
	} //main

}
