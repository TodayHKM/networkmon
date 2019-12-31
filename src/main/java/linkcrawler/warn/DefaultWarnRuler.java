package linkcrawler.warn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ds.util.Uuid;

import linkcrawler.datatypes.CustomLinkType;
import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.Warn;
import linkcrawler.logic.htmlUnitEngine.Configuration;

/*
 * �������������ʵ��
 * ��ϱ�������ͷ��ֵĴ�����Ϣ�����ɱ�����Ϣ
 * ��Ϣ�ṹ������  ��������
 */
public class DefaultWarnRuler implements WarnRule{
    private Configuration conf;  //�ں������������
    private HashMap<String,LinkStatus> errorLinks ;  //����������ӣ������жϵ���Դ
    private Warn warn ;
    
    public DefaultWarnRuler(){
    	
    }
    public DefaultWarnRuler(Configuration conf,HashMap<String,LinkStatus> errorLinks){
    	this.conf = conf;
    	this.errorLinks = errorLinks;
    	
    }
    //���⣺���������+�������쳣��+�ո�+���ݣ�
    //�ʼ��Ͷ��ŵ�������һ����
	@Override
	public String getWarnInfo() {
	 int errcnt = 0;	
	 if(conf!=null && (conf.isbEmailWarn()||conf.isbTelephoneWarn()) ){	//�Ƿ���Ҫ����
		 this.warn = new Warn();
		 warn.setId(Uuid.getUUID());
		 if(conf.isbEmailWarn() && conf.isbTelephoneWarn()){
		   warn.setType(2);   //�ʼ�����
		 }else if(conf.isbEmailWarn()){
		   warn.setType(0);
		 }else if(conf.isbTelephoneWarn()){ //����
		   warn.setType(1);	 
		 }
		Iterator iter = errorLinks.entrySet().iterator();
		String specialmsg = "";
		String linkmsg = "";
		String title = conf.getName().trim()+"�����쳣 "+" ";
		int specialerrcnt  = 0;
		int linkerrcnt = 0;
	    while(iter.hasNext()){
	    	 Map.Entry entry = (Map.Entry)iter.next();
	    	 LinkStatus  link = (LinkStatus) entry.getValue();
	    	 String[] status = link.getStatusInfoInArray(); //0-url�� 1 ״̬��
	         if(status[0].equalsIgnoreCase(conf.getDomain().toString())){ //����վ��ҳ��ַ
	        	linkmsg = title +" "+"��վ�����쳣:"+conf.getDomain().toString();
	            warn.setMsg(linkmsg);
	            return linkmsg ;
	    	 }
	         if(link.getCustomLinkType()>0){ //�ͻ������url������
	        	 specialmsg = specialmsg +" "+ getCustomInfo(link.getCustomLinkType())+":"+link.getHref2();
	        	 specialerrcnt++;
	         }else {
	        	 linkmsg = linkmsg +" "+ "��վ�����쳣:"+link.getHref2();
	             linkerrcnt++;
	         }
	         errcnt++;
	         if(errcnt>20){  //���ֻ����20��������Ϣ��
	        	 linkmsg = title + "�ص��쳣:"+specialerrcnt +"һ���쳣:"+(errorLinks.size()-specialerrcnt)+specialmsg+linkmsg+ "......";
	             warn.setMsg(linkmsg);
	             return linkmsg ;  //���ڷ��ʼ�������
	         }	                
	    }  	//while		
   	    linkmsg = title + "�ص��쳣:"+specialerrcnt +"  һ���쳣:"+linkerrcnt+specialmsg+linkmsg;
	    warn.setMsg(linkmsg);
	    return linkmsg ;//���ڷ��ʼ�������
	 }	
	 return null;
	}
     
	
	 private String getCustomInfo(int clt){
		 String str = null;
		 switch (clt) {
		 case  0: str = "��Ŀҳ�쳣";
		                 break;
		 case  1: str = "Ƶ��ҳ�쳣";
                         break;
		 case  2: str = "�ص�ҳ�쳣";
                           break;
		 case  3: str = "����ҳ�쳣";
                           break;
		 default:  break ;		 
		 }
		 return str;
	 }
	public Warn getWarn() {
		return warn;
	}
}
