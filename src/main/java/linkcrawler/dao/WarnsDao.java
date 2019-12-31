package linkcrawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import linkcrawler.datatypes.Warn;

import com.ds.dbmanager.InsertSql;
import com.ds.dbmanager.Manager;

public class WarnsDao {
	private String dbname = "1";
	private String strTbName = "warns";

	public WarnsDao() {
	//	dbname = SysInit.dbname;
	}
	
	public boolean doInsert(Warn warn){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
			String sql="insert into warns (ID,XXWB,XXLX,BJSJ,RW_ID) values(?,?,?,?,?)";
//			InsertSql insertSql = new InsertSql(strTbName);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, warn.getId()); //页id
			String msg = "";
			if(warn.getMsg().length()>140){  //消息文本最大500
				msg = warn.getMsg().substring(0,139); //139个字符
			}else{
				msg = warn.getMsg();
			}
			System.out.println(msg.length());
			System.out.println(msg);
			pstmt.setString(2, msg);  //消息文本
			pstmt.setInt(3,warn.getType());  //消息类型0邮件1短信2邮件短信
			pstmt.setString(4, new SimpleDateFormat(   //报警时间
					"yyyy-MM-dd HH:mm:ss").format(warn.getDate()));
			pstmt.setString(5,warn.getTask_id());

			int res=pstmt.executeUpdate();
			if(res>0){
				System.out.println("数据录入成功");
			}
			pstmt.close();//关闭资源
			conn.close();//关闭资源
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return blReturn;
		
	}
	
}
