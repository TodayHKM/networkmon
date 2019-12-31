package linkcrawler.dao;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.sql.*;

//import com.ds.dbmanager.InsertSql;
import com.ds.dbmanager.Manager;
//import com.ds.util.Uuid;

import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.UrlReport;
import linkcrawler.logic.htmlUnitEngine.HtmlUnitCrawler;

public class CheckTaskDao {
	private String dbname = "1";
	private String strTbName = "checktask";

	public CheckTaskDao() {
	//	dbname = SysInit.dbname;
	}
	public boolean doInsert(HtmlUnitCrawler crawler){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
			ArrayList<UrlReport> totalReport = crawler.getTotalReport();
//			InsertSql insertSql = new InsertSql(strTbName);
			String sql="insert into checktask (ID,RW_ID,WZZT,URL_YCCS,YMJZYCCS,LJCSYCCS,JCKSSJ,JCJSSJ) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uuid.getUUID32()); //id
			pstmt.setString(2, crawler.getTask().getId()); //����id
			if(crawler.getErrorLinks().size()<1){
				pstmt.setInt(3,0); //0 ��վ����
			}else{
				pstmt.setInt(3,1);
			}
			pstmt.setInt(4,crawler.getErrorLinks().size());//url�쳣����,���ظ���
			int pageErrCnt = 0;
			int timeoutCnt = 0;
			for(int i=0;i<totalReport.size();i++){
				if(!totalReport.get(i).isPageStatus()){
					pageErrCnt++;
				}				
			}
			Iterator iter = crawler.getErrorLinks().entrySet().iterator();
		    while(iter.hasNext()){
		    	 Map.Entry entry = (Map.Entry)iter.next();
		    	 String key = (String)entry.getKey();
		    	 LinkStatus  link = (LinkStatus) entry.getValue();
		         if(link.isTimeOut()){
		        	 timeoutCnt++; 
		         }
		     }
			pstmt.setInt(5,pageErrCnt)   ;//ҳ������쳣����
			pstmt.setInt(6,timeoutCnt)   ;//���ӳ�ʱ�쳣���������ظ���
			
			pstmt.setString(7,new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(crawler.getStartTime()));   //��鿪ʼʱ��
			pstmt.setString(8,new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(crawler.getEndTime()));//������ʱ��

			int res=pstmt.executeUpdate();
			if(res>0){
				System.out.println("����¼��ɹ�");
			}
			pstmt.close();//�ر���Դ
			conn.close();//�ر���Դ

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

//		return blReturn ;
		return true;
	}	

}
