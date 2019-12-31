package linkcrawler.manager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import linkcrawler.dao.TaskDao;
import linkcrawler.datatypes.Task;
import linkcrawler.datatypes.URLObject;
import linkcrawler.log.LogController;
import linkcrawler.logic.htmlUnitEngine.Configuration;
import linkcrawler.logic.htmlUnitEngine.HtmlUnitCrawler;

public class TaskManager {
  private ArrayList<Task>   tasks ;  //任务队列
  private ScheduledThreadPoolExecutor  stpe; //调度线程池
  private TaskDao taskDao ;
  
  public TaskManager(){	
	  tasks = new ArrayList<Task>();
	  stpe = new ScheduledThreadPoolExecutor(10); 
	  taskDao = new TaskDao();
	  init();	  
  }
  //从数据库获取可执行的任务，将任务加入运行任务列表，启动任务执行
  public void init(){	
	  //启动任务监控定时器
	  Timer timer = new Timer();
	  timer.schedule(new TaskMonitor(this),10*1000, 10*1000);//延迟10s后执行，10s执行1次  
	  //从库中获取任务记录
	  ArrayList<Task> temptasks =  taskDao.getTasks();
	  if(temptasks == null){ //没有需要执行的任务
		  return ;
	  }
	  //启动任务
	  for(Task ta:temptasks){
		  runTask(ta);
	  }	 
  }
  // run new task	
  public void runTask(Task  task){
	Task ta = getTask(task.getId());
	if(ta == null){ //新任务才加入，相同id的任务不会重复加入		
		tasks.add(task);		
	}else if(ta.getStatus() == Task.RUNING) {	//正在运行的不会被重复执行  	
		return ;
	}
	System.out.println("run task "+task.getName());
	Configuration config = getConfig(task); //配置
    LogController mainlog = new LogController(true); //日志
	HtmlUnitCrawler crawlTask = new HtmlUnitCrawler(config, mainlog,task);	 
	//0延时固定周期执行,
	stpe.scheduleAtFixedRate(crawlTask, 0, task.getScheduleTime(), TimeUnit.MINUTES);

	task.setCrawlTask(crawlTask);
	task.setStatus(Task.RUNING);
	taskDao.updateTaskStatus(task.getId(), 0);  //更新数据库任务记录状态
  }
  // cancel task
  public void deleteTask(int id){
	  
  }
  // stop task
  public void stopTask(String id){
	  Task task = getTask(id);
	  if(task != null){
		  task.getCrawlTask().stopCrawling(); //停止抓取线程
		  tasks.remove(task);  //从任务列表中移除
		  stpe.remove(task.getCrawlTask());	 //将任务从线程池队列中移走	  
		  taskDao.updateTaskStatus(task.getId(), 1);  //更新数据库任务记录状态
		  System.out.println("stop task "+task.getName());
	  }
  }
  // quit
  public void quit(){
	  stpe.shutdownNow();
  }
  // get task by id ,if can not find ,return null
  private Task getTask(String id){
	  for(Task ta : tasks){		
		 if(ta.getId().equals(id)){
			 return ta;
		 }
	 }
	 return null; 
  }
  
  private Configuration getConfig(Task task){
	  URLObject ourl = null;
	  try {
		ourl = new URLObject(task.getGoal(),true);  //主站url
	  } catch (Exception e) {
		 return null;
	  }	  
	  
	  Configuration conf = new Configuration();
	  
	  conf.setName(task.getName());
	  conf.setDomain(ourl);  //设置目标主站网址
	  conf.setAllowedDepthLevel(task.getDepth());
	  conf.setTimeOut(task.getTimeOut());
	  conf.setJobTime(task.getScheduleTime());
	  conf.setbEmailWarn(task.isbEmailWarn());
	  conf.setbTelephoneWarn(task.isbTelephoneWarn());
	  conf.setEmail(task.getWarnEmail());
	  conf.setTelephone(task.getWarnTelephone());
	  conf.setSendEmail(task.getSendEmail());
	  conf.setEmailPassword(task.getEmailPassword());
	  conf.setEmailHost(task.getEmailHost());
	  
	  return conf;	  
  }
public ArrayList<Task> getTasks() {
	return tasks;
}
  
}