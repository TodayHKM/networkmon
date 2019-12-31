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
		//1 ��������ִ�е�����
		 ArrayList<Task> temptasks =  td.getTasks();
		 if(temptasks != null){
			 //��������
			  for(Task ta:temptasks){
				  tm.runTask(ta);
			  }		 
		 }	
		 System.out.println("timer run ");
	   //2 ��������ֹͣ������ǰ����Ȼ�������е�����
		 for(Task ta:tm.getTasks()){  //ɨ�赱ǰ����
			 if(td.getOperCmd(ta.getId())== Task.CMDSTOP){ //�����ǰ�����������ֹͣ
				 tm.stopTask(ta.getId());
			 }
		 }
		 
		 
	}

}
