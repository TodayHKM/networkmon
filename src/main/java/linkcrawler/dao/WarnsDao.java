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
			pstmt.setString(1, warn.getId()); //ҳid
			String msg = "";
			if(warn.getMsg().length()>140){  //��Ϣ�ı����500
				msg = warn.getMsg().substring(0,139); //139���ַ�
			}else{
				msg = warn.getMsg();
			}
			System.out.println(msg.length());
			System.out.println(msg);
			pstmt.setString(2, msg);  //��Ϣ�ı�
			pstmt.setInt(3,warn.getType());  //��Ϣ����0�ʼ�1����2�ʼ�����
			pstmt.setString(4, new SimpleDateFormat(   //����ʱ��
					"yyyy-MM-dd HH:mm:ss").format(warn.getDate()));
			pstmt.setString(5,warn.getTask_id());

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
		
		return blReturn;
		
	}
	
}
