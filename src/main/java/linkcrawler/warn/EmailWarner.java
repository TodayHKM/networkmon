package linkcrawler.warn;

import java.util.Observable;
import java.util.Observer;

import linkcrawler.logic.htmlUnitEngine.Configuration;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/*
 * �����ʼ�����, �۲���
 */
public class EmailWarner implements Observer {
    private String userName = "dalei_main2000"; //���͵� email��ַ
    private String passWord = "fanghong2006" ;
    private String  emailHost = "smtp.163.com";
    private String from = "dalei_main2000@163.com" ;
    private String to = "15850675602@139.com" ;
    
    public EmailWarner(){
    	
    }
    
    public EmailWarner(Configuration conf){
    	this.from = conf.getSendEmail();   // ��������
    	String strs[] = from.split("@");   //��ȡ�����������û���
    	if(strs[0]!=null && strs[0].length()>1){
    	    this.userName = strs[0];
    	}
    	this.passWord = conf.getEmailPassword(); //������������
    	
    	this.to = conf.getEmail();  //����Ŀ������
    	this.emailHost = conf.getEmailHost(); //������������
    }
    
    public EmailWarner(String userName,String passWord){
    	this.userName = userName;
    	this.passWord = passWord;
    }
    //ͨ���̷߳���
    @Override
	public void update(Observable arg0, Object arg1) {
       final String str = (String)arg1;
       if(str!=null && str.trim().length()>1 ){
            new Thread(new Runnable() {            	
            	public void run(){
            		sendEmail(str);
            	}
            	
            }).start();     
       }
	}
	
	public void sendEmail(String msg){
		try {
			Email email = new SimpleEmail();
			email.setCharset("UTF-8");
			email.setHostName(emailHost);  //�����ʼ�������
			email.setAuthentication(userName, passWord); //���õ�¼�û�������
			email.addTo(to);    //����ռ���
			email.setFrom(from);	//���÷�����
			String[] strs = getInfo(msg);
			email.setSubject(strs[0]);  //�����ʼ�����
			email.setMsg(strs[1]);	 // �����ʼ�����		
			email.send(); //�ȽϺ�ʱ
			System.out.println("send email success");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private String[]  getInfo(String msg){  //ͨ���ո����ֳ����������
    	String[]  strs = new String[2];
    	for(int i=0;i<msg.length();i++){
    		if(msg.charAt(i)==' '){
    			strs[0] =  msg.substring(0, i);
    			strs[1] = msg.substring(i+1,msg.length());
    			break;
    		}
    	}
    	return strs;
    }
}
