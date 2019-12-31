package linkcrawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ds.dbmanager.InsertSql;
import com.ds.dbmanager.Manager;
import com.ds.dbmanager.UpdateSql;
import com.ds.util.Uuid;

import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.UrlReport;

public class LinkCheckDao {
	private String dbname = "1";
	private String strTbName = "linkchecks";

	public LinkCheckDao() {
	//	dbname = SysInit.dbname;
	}
	//插入链接记录，
	public boolean doInsert(LinkStatus ls,String pageid,String taskid){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
//			InsertSql insertSql = new InsertSql(strTbName);
			String sql="insert into linkchecks (ID,LJ_URL,LJWB,ZTXX,SFCS,ZT,LJJCRQ,LJSZWY_ID,SSRW_ID,LJLX) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);


			pstmt.setString(1, ls.getId());
			String str = ls.getHref2().trim();
			if(str.length()>149){//超长截断，避免保存时异常，丢失记录
			  str = str.substring(0,149);//149个字符
			}
			pstmt.setString(2, str); // 链接url
			
			str = ls.getLinkText().trim();
			if(str.length()>99){//超长截断，避免保存时异常，丢失记录
			  str = str.substring(0,99); 
			}
			pstmt.setString(3, str); // 链接文本
			
			str = ls.getHttpCode().trim();
			if(str.length()>99){
			  str = str.substring(0,99);
			}
			pstmt.setString(4,str); // 状态信息
			if(ls.isTimeOut()){
				pstmt.setInt(5, 0); // 0是超时
			}else{
				pstmt.setInt(5, 1);
			}
			if(ls.isUp()){
				pstmt.setInt(6, 0); // 0状态正常
			}else{
				pstmt.setInt(6, 1);
			}
			pstmt.setString(7, new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(ls.getCheckTime())); // 链接检查时间
			pstmt.setString(8, pageid); // 链接所属页的id
			pstmt.setString(9, taskid); //检查该页的任务id
			pstmt.setInt(10, ls.getCustomLinkType()); //链接类型：0栏目、1频道...

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
		
		
		return true;
	}
	public boolean updateLinkPageError(String id){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
			String sql="UPDATE linkchecks SET ZT=?, ZTXX=? WHERE ID=1";
			PreparedStatement pstmt=conn.prepareStatement(sql);

//			UpdateSql sql = new UpdateSql(strTbName, " id='" + id + "'");
			pstmt.setInt(1, 1);
			pstmt.setString(2, "页加载异常");
//			blReturn = Manager.doExcute(dbname, sql.getSql());
			pstmt.executeUpdate();
			System.out.println("update linkchecks" + "****************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
}
