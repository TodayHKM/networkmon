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
	//�������Ӽ�¼��
	public boolean doInsert(LinkStatus ls,String pageid,String taskid){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
//			InsertSql insertSql = new InsertSql(strTbName);
			String sql="insert into linkchecks (ID,LJ_URL,LJWB,ZTXX,SFCS,ZT,LJJCRQ,LJSZWY_ID,SSRW_ID,LJLX) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);


			pstmt.setString(1, ls.getId());
			String str = ls.getHref2().trim();
			if(str.length()>149){//�����ضϣ����Ᵽ��ʱ�쳣����ʧ��¼
			  str = str.substring(0,149);//149���ַ�
			}
			pstmt.setString(2, str); // ����url
			
			str = ls.getLinkText().trim();
			if(str.length()>99){//�����ضϣ����Ᵽ��ʱ�쳣����ʧ��¼
			  str = str.substring(0,99); 
			}
			pstmt.setString(3, str); // �����ı�
			
			str = ls.getHttpCode().trim();
			if(str.length()>99){
			  str = str.substring(0,99);
			}
			pstmt.setString(4,str); // ״̬��Ϣ
			if(ls.isTimeOut()){
				pstmt.setInt(5, 0); // 0�ǳ�ʱ
			}else{
				pstmt.setInt(5, 1);
			}
			if(ls.isUp()){
				pstmt.setInt(6, 0); // 0״̬����
			}else{
				pstmt.setInt(6, 1);
			}
			pstmt.setString(7, new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(ls.getCheckTime())); // ���Ӽ��ʱ��
			pstmt.setString(8, pageid); // ��������ҳ��id
			pstmt.setString(9, taskid); //����ҳ������id
			pstmt.setInt(10, ls.getCustomLinkType()); //�������ͣ�0��Ŀ��1Ƶ��...

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
			pstmt.setString(2, "ҳ�����쳣");
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
