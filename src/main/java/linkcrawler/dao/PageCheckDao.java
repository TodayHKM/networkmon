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
	//保存主页,处理主页链接异常、主页加载异常、主页正常保存
	// page为 null,表示主链接异常
	public boolean saveHostPage(UrlReport page,LinkStatus ls,String taskid){
		boolean blReturn = false;
		Connection conn = myjdbc.getcon();
		try {
			if(page==null){  //链接异常
				if(ls!=null){
					ls.setId(uuid.getUUID32());
					LinkCheckDao lcd = new LinkCheckDao();
					blReturn = lcd.doInsert(ls, null, taskid);	//对应的页链接为空			
				}		
			}else{
			  ls.setId(uuid.getUUID32());
			  if(page.getId()==null){
				  page.setId(uuid.getUUID32());
			  }
			  if(page.isPageStatus()){ //页正常
				//1 生成链接id
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
					//2  写页数据
				  String sql="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				  PreparedStatement pstmt=conn.prepareStatement(sql);
//					InsertSql insertSql = new InsertSql(strTbName);
				  pstmt.setString(1, page.getId());
				  pstmt.setString(2, ls.getId()); // 指向该页的链接id
				  pstmt.setString(3, page.getTitle()); // 指向该页标题
				  pstmt.setInt(4,ls.getCustomLinkType()); // 该页类型：栏目、频道...
				  pstmt.setInt(5, 1); // 页加载状态 1异常
				  pstmt.setInt(6, page.getBadLinks()); // 页内坏链接数
				  pstmt.setString(7, taskid); // 检查该页的任务id
//					blReturn = Manager.doExcute(dbname, insertSql.getSql());
				  int res=pstmt.executeUpdate();
				  if(res>0){
					  System.out.println("数据录入成功");
				  }
				  pstmt.close();//关闭资源
				  conn.close();//关闭资源
					//3 写页内每条链接记录
					LinkCheckDao lcd = new LinkCheckDao();
					blReturn = lcd.doInsert(ls, ls.getId(), taskid);//写指向主页的链接
					//写主页中包含的链接
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
			  }else{//页异常
					//1  写页数据
				  String sql="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				  PreparedStatement pstmt=conn.prepareStatement(sql);
//					InsertSql insertSql = new InsertSql(strTbName);
				  pstmt.setString(1, page.getId());
				  pstmt.setString(2, ls.getId()); // 指向该页的链接id
				  pstmt.setString(3, page.getTitle()); // 指向该页标题
				  pstmt.setInt(4,ls.getCustomLinkType()); // 该页类型：栏目、频道...
				  pstmt.setInt(5, 1); // 页加载状态 1异常
				  pstmt.setInt(6, page.getBadLinks()); // 页内坏链接数
				  pstmt.setString(7, taskid); // 检查该页的任务id
//					blReturn = Manager.doExcute(dbname, insertSql.getSql());
				  int res=pstmt.executeUpdate();
				  if(res>0){
					  System.out.println("数据录入成功");
				  }
				  pstmt.close();//关闭资源
				  conn.close();//关闭资源
				  //2 写主页链接
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
	
	//插入报告中的最后一页记录信息，前提是报告中有2页
	public boolean doInsert(ArrayList<UrlReport> pages,String taskid,LinkStatus pageLinkStatus){
		boolean blReturn = false;
		
		try {
			if(pages == null || pages.size()==0){
				return false;
			}
			UrlReport page = pages.get(pages.size()-1); //获取最近1页
			if(page.getId()==null){  //设置页id
				page.setId(uuid.getUUID32());
			}
					
			if(page.isPageStatus()){  //页加载正常
				//1 生成链接id
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
				//2 获取指向页链接的linkstatus及所在页
				if(pageLinkStatus == null){ // 		
				  for(int i=pages.size()-2;i>=0;i--){ // 从上一页开始扫描
					//扫描内部链接，将上页内部链接逐个与指向下一页的url相比较，进而获取对应的linkstatus
					for(LinkStatus  ls:pages.get(i).getInternalLinks()){
						String url = null;
						try {
							url = ls.getHref().toString();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						if(url.equals(pages.get(pages.size()-1).getPageUrl())){ //上页url与指向最后页的url比较
							pageLinkStatus = ls;
							break;
						}					
					}
					if(pageLinkStatus!=null){  //退出循环
						break;
					}
					////扫描外部链接
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
				//3  写页数据
				Connection conn = myjdbc.getcon();
				String sql2="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql2);
//				InsertSql insertSql = new InsertSql(strTbName);
				pstmt.setString(1, page.getId()); //页id
				pstmt.setString(2, pageLinkStatus.getId()); // 指向该页的链接id
				
				String str = page.getTitle();
				if(str.length()>99){   //超长截断，避免保存时异常，丢失记录
				  str = str.substring(0,99);//99个字符
				}
				pstmt.setString(3, str); // 指向该页标题
				pstmt.setInt(4,pageLinkStatus.getCustomLinkType()); // 该页类型：栏目、频道...
				pstmt.setInt(5, 0); // 页加载状态 0正常
				pstmt.setInt(6, page.getBadLinks()); // 页内坏链接数
				pstmt.setString(7, taskid); // 检查该页的任务id

				int res=pstmt.executeUpdate();
				if(res>0){
					System.out.println("数据录入成功");
				}
				pstmt.close();//关闭资源
				conn.close();//关闭资源
				//4 写页内每条链接记录
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
				
			}else{ //页加载异常
				//1 获取指向页链接的linkstatus
				if(pageLinkStatus == null){
				  for(int i=pages.size()-2;i>=0;i--){
					//扫描内部链接
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
					if(pageLinkStatus!=null){  //退出循环
						break;
					}
					////扫描外部链接
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
				//2  写页数据
				Connection conn = myjdbc.getcon();
				String sql3="insert into pagechecks (ID,YURL_ID,YBT,YLX,YJZZT,YNHLS,SSRW_ID) values(?,?,?,?,?,?,?)";
				PreparedStatement pstmt=conn.prepareStatement(sql3);
//				InsertSql insertSql = new InsertSql(strTbName);
				pstmt.setString(1, page.getId()); //页id
				pstmt.setString(2, pageLinkStatus.getId()); // 指向该页的链接id

				String str = page.getTitle();
				if(str.length()>99){   //超长截断，避免保存时异常，丢失记录
					str = str.substring(0,99);//99个字符
				}
				pstmt.setString(3, str); // 指向该页标题
				pstmt.setInt(4,pageLinkStatus.getCustomLinkType()); // 该页类型：栏目、频道...
				pstmt.setInt(5, 0); // 页加载状态 0正常
				pstmt.setInt(6, page.getBadLinks()); // 页内坏链接数
				pstmt.setString(7, taskid); // 检查该页的任务id

				int res=pstmt.executeUpdate();
				if(res>0){
					System.out.println("数据录入成功");
				}
				pstmt.close();//关闭资源
				conn.close();//关闭资源
				//3 更新该页对应的链接状态及信息
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
