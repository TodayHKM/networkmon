package linkcrawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import linkcrawler.datatypes.SpecialUrl;
import linkcrawler.datatypes.Task;

import com.ds.common.util.Convert;
import com.ds.dbmanager.Manager;
import com.ds.dbmanager.UpdateSql;
import com.ds.sys.SysInit;

public class TaskDao {
	private String dbname = "1";
	private String strTbName = "task";
    
	public TaskDao() {
	//	dbname = SysInit.dbname;
	}
   //获取命令执行的任务
	public ArrayList<Task> getTasks(){
		ArrayList<Task>  tasks = null;
		Task task = null;
//		String strSql = "select * from task where cz=0 ";
//		String[][] strData = Manager.doQuery(dbname, strSql);
		Connection conn = myjdbc.getcon();
		try {
		String strSql = "select * from task where cz=0 ";
		PreparedStatement pst = conn.prepareStatement(strSql);
		ResultSet rs = pst.executeQuery();
		  tasks = new ArrayList<Task>();

		while (rs.next()) {
			task = new Task();
			task.setId(rs.getString("ID")); //
			task.setName(rs.getString("RWMC"));//任务名称
			task.setGoal(rs.getString("JKWZ_URL"));  //监控网站URL
			task.setDepartmemt(rs.getString("FZBM"));//负责部门
			task.setScheduleTime(rs.getInt("JKPL"));//监控频率
			task.setDepth(rs.getInt("JKSD"));//监控深度
			task.setTimeOut(rs.getInt("CSSZ"));//超时设置
			task.setStatus(Integer.parseInt(rs.getString("ZT")));//状态
			task.setOperation(Integer.parseInt(rs.getString("CZ")));//操作
			task.setWarnEmail(rs.getString("FZBM"));//报警接收邮箱
			task.setSendEmail(rs.getString("FSYX"));//发送邮箱
			task.setEmailPassword(rs.getString("FSYXMM"));//发送邮箱密码
			task.setEmailHost(rs.getString("YXZJ")); //邮箱主机
			task.setWarnTelephone(rs.getString("BJDH"));;  //报警电话
			int temp = Integer.parseInt(rs.getString("SFYXBJ"));//是否邮箱报警
			if(temp == 1)
		      task.setbEmailWarn(false);
		    else{
		       task.setbEmailWarn(true);
		    }
		    temp = Integer.parseInt(rs.getString("SFDXBJ"));//是否短信报警
		    if(temp == 1)
			      task.setbTelephoneWarn(false);
			else{
			       task.setbTelephoneWarn(true);
			}
//		    task.setSpecialUrls(new SpecialUrlDao().getSpecialUrls(task.getId()));

		    tasks.add(task);
		}//for
		return tasks;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		try {
			if(conn == null)
				conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		return tasks;
}
	// 获取操作命令
	public int getOperCmd(String id ){
		int cmd = -1;

		try {
			String strSql = "select cz  from task where id='"+id+"'";
			Connection conn = myjdbc.getcon();
			String strSql2 = "select * from task where cz=0 ";
			PreparedStatement pst = conn.prepareStatement(strSql2);
			ResultSet rs = pst.executeQuery();
//		String[][] strData = Manager.doQuery(dbname, strSql);
			if(rs.next()){
				cmd = rs.getInt("CZ");
			}
			return cmd ;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
			return cmd;
	}
	
	
	public boolean updateTaskStatus(String id,int staus){
		Connection conn = myjdbc.getcon();

		try {
			String sql3="UPDATE task SET ZT=? WHERE ID=0";
			PreparedStatement pstmt=conn.prepareStatement(sql3);
			pstmt.setInt(1, staus);
			pstmt.executeUpdate();
			boolean rs = pstmt.execute();
			if(rs){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
//		boolean blReturn = Manager.doExcute(dbname, sql.getSql());
	//	System.out.println(sql.getSql() + "****************************");
	}
	
}
