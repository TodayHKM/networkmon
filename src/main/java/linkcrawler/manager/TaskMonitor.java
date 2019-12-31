package linkcrawler.manager;

import java.util.ArrayList;
import java.util.TimerTask;

import linkcrawler.dao.TaskDao;
import linkcrawler.datatypes.Task;

public class TaskMonitor extends TimerTask {
    private TaskManager tm ;
    private TaskDao td ;
	
    public TaskMonitor(TaskManager tm){
		this.tm = tm;	
		td = new TaskDao();
	}
	@Override
	public void run() {
		//1 处理命令执行的任务
		 ArrayList<Task> temptasks =  td.getTasks();
		 if(temptasks != null){
			 //启动任务
			  for(Task ta:temptasks){
				  tm.runTask(ta);
			  }		 
		 }	
		 System.out.println("timer run ");
	   //2 处理命令停止的任务，前提显然是在运行的任务
		 for(Task ta:tm.getTasks()){  //扫描当前任务
			 if(td.getOperCmd(ta.getId())== Task.CMDSTOP){ //如果当前任务的命令是停止
				 tm.stopTask(ta.getId());
			 }
		 }
		 
		 
	}

}
