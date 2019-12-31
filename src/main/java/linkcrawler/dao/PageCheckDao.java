package linkcrawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.UrlReport;

//import com.ds.dbmanager.InsertSql;
import com.ds.dbmanager.Manager;
//import com.ds.util.Uuid;

public class PageCheckDao {
	private String dbname = "1";
	private String strTbName = "pagechecks";

	public PageCheckDao() {
	//	dbname = SysInit.dbname;
	}
	//������ҳ,������ҳ�����쳣����ҳ�����쳣����ҳ��������
	// pageΪ null,��ʾ�������쳣
	public boolean saveHostPage(UrlReport page,LinkStatus ls,String taskid){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
			if(page==null){  //�����쳣
				if(ls!=null){
					ls.setId(uuid.getUUID32());
					LinkCheckDao lcd = new LinkCheckDao();
					blReturn = lcd.doInsert(ls, null, taskid);	//��Ӧ��ҳ����Ϊ��			
				}		
			}else{
			  ls.setId(uuid.getUUID32());
			  if(page.getId()==null){
				  page.setId(uuid.getUUID32());
			  }
			  if(page.isPageStatus()){ //ҳ����
				//1 ��������id
					for(LinkStatus ls2 : page.getInternalLinks()){
						ls2.setId(uuid.getUUID32());
					}
					for(LinkStatus ls2: page.getExternalLinks()){
						ls2.setId(uuid.getUUID32());
					}
					for(LinkStatus ls2 : page.getImagesSrc()){
						ls2.setId(uuid.getUUID32());
					}
					for(LinkStatus ls2 : page.getSpecialLinks()){
						ls2.setId(uuid.getUUID32());
					}			
					//2  дҳ����
				  String sql="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				  PreparedStatement pstmt=conn.prepareStatement(sql);
//					InsertSql insertSql = new InsertSql(strTbName);
				  pstmt.setString(1, page.getId());
				  pstmt.setString(2, ls.getId()); // ָ���ҳ������id
				  pstmt.setString(3, page.getTitle()); // ָ���ҳ����
				  pstmt.setInt(4,ls.getCustomLinkType()); // ��ҳ���ͣ���Ŀ��Ƶ��...
				  pstmt.setInt(5, 1); // ҳ����״̬ 1�쳣
				  pstmt.setInt(6, page.getBadLinks()); // ҳ�ڻ�������
				  pstmt.setString(7, taskid); // ����ҳ������id
//					blReturn = Manager.doExcute(dbname, insertSql.getSql());
				  int res=pstmt.executeUpdate();
				  if(res>0){
					  System.out.println("����¼��ɹ�");
				  }
				  pstmt.close();//�ر���Դ
				  conn.close();//�ر���Դ
					//3 дҳ��ÿ�����Ӽ�¼
					LinkCheckDao lcd = new LinkCheckDao();
					blReturn = lcd.doInsert(ls, ls.getId(), taskid);//дָ����ҳ������
					//д��ҳ�а���������
					for(LinkStatus ls2 : page.getInternalLinks()){
						lcd.doInsert(ls2, page.getId(), taskid);
					}
					for(LinkStatus ls2 : page.getExternalLinks()){
						lcd.doInsert(ls2, page.getId(), taskid);
					}
					for(LinkStatus ls2 : page.getImagesSrc()){
						lcd.doInsert(ls2, page.getId(), taskid);
					}
					for(LinkStatus ls2 : page.getSpecialLinks()){
						lcd.doInsert(ls2, page.getId(), taskid);
					}				  
			  }else{//ҳ�쳣
					//1  дҳ����
				  String sql="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				  PreparedStatement pstmt=conn.prepareStatement(sql);
//					InsertSql insertSql = new InsertSql(strTbName);
				  pstmt.setString(1, page.getId());
				  pstmt.setString(2, ls.getId()); // ָ���ҳ������id
				  pstmt.setString(3, page.getTitle()); // ָ���ҳ����
				  pstmt.setInt(4,ls.getCustomLinkType()); // ��ҳ���ͣ���Ŀ��Ƶ��...
				  pstmt.setInt(5, 1); // ҳ����״̬ 1�쳣
				  pstmt.setInt(6, page.getBadLinks()); // ҳ�ڻ�������
				  pstmt.setString(7, taskid); // ����ҳ������id
//					blReturn = Manager.doExcute(dbname, insertSql.getSql());
				  int res=pstmt.executeUpdate();
				  if(res>0){
					  System.out.println("����¼��ɹ�");
				  }
				  pstmt.close();//�ر���Դ
				  conn.close();//�ر���Դ
				  //2 д��ҳ����
					LinkCheckDao lcd = new LinkCheckDao();
					blReturn = lcd.doInsert(ls, page.getId(), taskid);
			  }			
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return blReturn;
	}
	public boolean doInsert(ArrayList<UrlReport> pages,String taskid){
		return this.doInsert(pages, taskid, null);
	}
	
	//���뱨���е����һҳ��¼��Ϣ��ǰ���Ǳ�������2ҳ
	public boolean doInsert(ArrayList<UrlReport> pages,String taskid,LinkStatus pageLinkStatus){
		boolean blReturn = false;
		
		try {
			if(pages == null || pages.size()==0){
				return false;
			}
			UrlReport page = pages.get(pages.size()-1); //��ȡ���1ҳ
			if(page.getId()==null){  //����ҳid
				page.setId(uuid.getUUID32());
			}
					
			if(page.isPageStatus()){  //ҳ��������
				//1 ��������id
				for(LinkStatus ls : page.getInternalLinks()){
					ls.setId(uuid.getUUID32());
				}
				for(LinkStatus ls : page.getExternalLinks()){
					ls.setId(uuid.getUUID32());
				}
				for(LinkStatus ls : page.getImagesSrc()){
					ls.setId(uuid.getUUID32());
				}
				for(LinkStatus ls : page.getSpecialLinks()){
					ls.setId(uuid.getUUID32());
				}
				//2 ��ȡָ��ҳ���ӵ�linkstatus������ҳ
				if(pageLinkStatus == null){ // 		
				  for(int i=pages.size()-2;i>=0;i--){ // ����һҳ��ʼɨ��
					//ɨ���ڲ����ӣ�����ҳ�ڲ����������ָ����һҳ��url��Ƚϣ�������ȡ��Ӧ��linkstatus
					for(LinkStatus  ls:pages.get(i).getInternalLinks()){
						String url = null;
						try {
							url = ls.getHref().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						if(url.equals(pages.get(pages.size()-1).getPageUrl())){ //��ҳurl��ָ�����ҳ��url�Ƚ�
							pageLinkStatus = ls;
							break;
						}					
					}
					if(pageLinkStatus!=null){  //�˳�ѭ��
						break;
					}
					////ɨ���ⲿ����
					for(LinkStatus  ls:pages.get(i).getExternalLinks()){
						String url = null;
						try {
							url = ls.getHref().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(url.equals(pages.get(pages.size()-1).getPageUrl())){
							pageLinkStatus = ls;
							break;
						}					
					}
					if(pageLinkStatus!=null){
						break;
					}
				  }
				}//			
				//3  дҳ����
				Connection conn = myjdbc.getcon();
				String sql2="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql2);
//				InsertSql insertSql = new InsertSql(strTbName);
				pstmt.setString(1, page.getId()); //ҳid
				pstmt.setString(2, pageLinkStatus.getId()); // ָ���ҳ������id
				
				String str = page.getTitle();
				if(str.length()>99){   //�����ضϣ����Ᵽ��ʱ�쳣����ʧ��¼
				  str = str.substring(0,99);//99���ַ�
				}
				pstmt.setString(3, str); // ָ���ҳ����
				pstmt.setInt(4,pageLinkStatus.getCustomLinkType()); // ��ҳ���ͣ���Ŀ��Ƶ��...
				pstmt.setInt(5, 0); // ҳ����״̬ 0����
				pstmt.setInt(6, page.getBadLinks()); // ҳ�ڻ�������
				pstmt.setString(7, taskid); // ����ҳ������id

				int res=pstmt.executeUpdate();
				if(res>0){
					System.out.println("����¼��ɹ�");
				}
				pstmt.close();//�ر���Դ
				conn.close();//�ر���Դ
				//4 дҳ��ÿ�����Ӽ�¼
				LinkCheckDao lcd = new LinkCheckDao();
				for(LinkStatus ls : page.getInternalLinks()){
					lcd.doInsert(ls, page.getId(), taskid);
				}
				for(LinkStatus ls : page.getExternalLinks()){
					lcd.doInsert(ls, page.getId(), taskid);
				}
				for(LinkStatus ls : page.getImagesSrc()){
					lcd.doInsert(ls, page.getId(), taskid);
				}
				for(LinkStatus ls : page.getSpecialLinks()){
					lcd.doInsert(ls, page.getId(), taskid);
				}	
				
			}else{ //ҳ�����쳣
				//1 ��ȡָ��ҳ���ӵ�linkstatus
				if(pageLinkStatus == null){
				  for(int i=pages.size()-2;i>=0;i--){
					//ɨ���ڲ�����
					for(LinkStatus  ls:pages.get(i).getInternalLinks()){
						String url = null;
						try {
							url = ls.getHref().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(url.equals(pages.get(pages.size()-1).getPageUrl())){
							pageLinkStatus = ls;
							break;
						}					
					}
					if(pageLinkStatus!=null){  //�˳�ѭ��
						break;
					}
					////ɨ���ⲿ����
					for(LinkStatus  ls:pages.get(i).getExternalLinks()){
						String url = null;
						try {
							url = ls.getHref().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(url.equals(pages.get(pages.size()-1).getPageUrl())){
							pageLinkStatus = ls;
							break;
						}					
					}
					if(pageLinkStatus!=null){
						break;
					}
				  }//for
				}//			
				//2  дҳ����
				Connection conn = myjdbc.getcon();
				String sql3="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql3);
//				InsertSql insertSql = new InsertSql(strTbName);
				pstmt.setString(1, page.getId()); //ҳid
				pstmt.setString(2, pageLinkStatus.getId()); // ָ���ҳ������id

				String str = page.getTitle();
				if(str.length()>99){   //�����ضϣ����Ᵽ��ʱ�쳣����ʧ��¼
					str = str.substring(0,99);//99���ַ�
				}
				pstmt.setString(3, str); // ָ���ҳ����
				pstmt.setInt(4,pageLinkStatus.getCustomLinkType()); // ��ҳ���ͣ���Ŀ��Ƶ��...
				pstmt.setInt(5, 0); // ҳ����״̬ 0����
				pstmt.setInt(6, page.getBadLinks()); // ҳ�ڻ�������
				pstmt.setString(7, taskid); // ����ҳ������id

				int res=pstmt.executeUpdate();
				if(res>0){
					System.out.println("����¼��ɹ�");
				}
				pstmt.close();//�ر���Դ
				conn.close();//�ر���Դ
				//3 ���¸�ҳ��Ӧ������״̬����Ϣ
				LinkCheckDao lcd = new LinkCheckDao();
				lcd.updateLinkPageError(pageLinkStatus.getId());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return blReturn;		
	}	
}
