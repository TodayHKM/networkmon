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
  private ArrayList<Task>   tasks ;  //�������
  private ScheduledThreadPoolExecutor  stpe; //�����̳߳�
  private TaskDao taskDao ;
  
  public TaskManager(){	
	  tasks = new ArrayList<Task>();
	  stpe = new ScheduledThreadPoolExecutor(10); 
	  taskDao = new TaskDao();
	  init();	  
  }
  //�����ݿ��ȡ��ִ�е����񣬽�����������������б���������ִ��
  public void init(){	
	  //���������ض�ʱ��
	  Timer timer = new Timer();
	  timer.schedule(new TaskMonitor(this),10*1000, 10*1000);//�ӳ�10s��ִ�У�10sִ��1��  
	  //�ӿ��л�ȡ�����¼
	  ArrayList<Task> temptasks =  taskDao.getTasks();
	  if(temptasks == null){ //û����Ҫִ�е�����
		  return ;
	  }
	  //��������
	  for(Task ta:temptasks){
		  runTask(ta);
	  }	 
  }
  // run new task	
  public void runTask(Task  task){
	Task ta = getTask(task.getId());
	if(ta == null){ //������ż��룬��ͬid�����񲻻��ظ�����		
		tasks.add(task);		
	}else if(ta.getStatus() == Task.RUNING) {	//�������еĲ��ᱻ�ظ�ִ��  	
		return ;
	}
	System.out.println("run task "+task.getName());
	Configuration config = getConfig(task); //����
    LogController mainlog = new LogController(true); //��־
	HtmlUnitCrawler crawlTask = new HtmlUnitCrawler(config, mainlog,task);	 
	//0��ʱ�̶�����ִ��,
	stpe.scheduleAtFixedRate(crawlTask, 0, task.getScheduleTime(), TimeUnit.MINUTES);

	task.setCrawlTask(crawlTask);
	task.setStatus(Task.RUNING);
	taskDao.updateTaskStatus(task.getId(), 0);  //�������ݿ������¼״̬
  }
  // cancel task
  public void deleteTask(int id){
	  
  }
  // stop task
  public void stopTask(String id){
	  Task task = getTask(id);
	  if(task != null){
		  task.getCrawlTask().stopCrawling(); //ֹͣץȡ�߳�
		  tasks.remove(task);  //�������б����Ƴ�
		  stpe.remove(task.getCrawlTask());	 //��������̳߳ض���������	  
		  taskDao.updateTaskStatus(task.getId(), 1);  //�������ݿ������¼״̬
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
		ourl = new URLObject(task.getGoal(),true);  //��վurl
	  } catch (Exception e) {
		 return null;
	  }	  
	  
	  Configuration conf = new Configuration();
	  
	  conf.setName(task.getName());
	  conf.setDomain(ourl);  //����Ŀ����վ��ַ
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