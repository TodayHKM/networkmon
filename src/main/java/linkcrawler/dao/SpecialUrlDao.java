package linkcrawler.dao;

import java.util.ArrayList;

import com.ds.common.util.Convert;
import com.ds.dbmanager.Manager;

import linkcrawler.datatypes.SpecialUrl;
import linkcrawler.datatypes.Task;

public class SpecialUrlDao {
	private String dbname = "1";
	private String strTbName = "specialurls";

	public SpecialUrlDao() {
	//	dbname = SysInit.dbname;
	}
	//��ȡ�ͻ��������ַ
	public ArrayList<SpecialUrl> getSpecialUrls(String taskid){
		ArrayList<SpecialUrl>  urls = null;
		String strSql = "select * from specialurls where rw_id='"+taskid+"'";
		String[][] strData = Manager.doQuery(dbname, strSql);
		SpecialUrl su = null;
		if(strData.length>0){
		  urls  = new ArrayList<SpecialUrl>();
		}
		for(int i=0;i<strData.length;i++){
			su = new SpecialUrl();
			su.setId(Convert.getValue(strData[i][0])); //
			su.setWz(Convert.getValue(strData[i][1]));//��ַ
	        su.setMswb(Convert.getValue(strData[i][2]));  //�����ı�
			su.setLx(Convert.getStringValueInt(strData[i][3]));//������
			su.setRw_id(Convert.getValue(strData[i][4]));//��������id
			
		    urls.add(su);
		}//for		
		
		
	    return urls;
	}
}
