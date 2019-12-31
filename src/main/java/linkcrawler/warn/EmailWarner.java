package linkcrawler.warn;

import java.util.Observable;
import java.util.Observer;

import linkcrawler.logic.htmlUnitEngine.Configuration;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/*
 * 电子邮件报警, 观察者
 */
public class EmailWarner implements Observer {
    private String userName = "dalei_main2000"; //发送的 email地址
    private String passWord = "fanghong2006" ;
    private String  emailHost = "smtp.163.com";
    private String from = "dalei_main2000@163.com" ;
    private String to = "15850675602@139.com" ;
    
    public EmailWarner(){
    	
    }
    
    public EmailWarner(Configuration conf){
    	this.from = conf.getSendEmail();   // 发送邮箱
    	String strs[] = from.split("@");   //提取出发送邮箱用户名
    	if(strs[0]!=null && strs[0].length()>1){
    	    this.userName = strs[0];
    	}
    	this.passWord = conf.getEmailPassword(); //发送邮箱密码
    	
    	this.to = conf.getEmail();  //报警目的邮箱
    	this.emailHost = conf.getEmailHost(); //发送邮箱主机
    }
    
    public EmailWarner(String userName,String passWord){
    	this.userName = userName;
    	this.passWord = passWord;
    }
    //通过线程发送
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
			email.setHostName(emailHost);  //设置邮件服务器
			email.setAuthentication(userName, passWord); //设置登录用户、密码
			email.addTo(to);    //添加收件人
			email.setFrom(from);	//设置发件人
			String[] strs = getInfo(msg);
			email.setSubject(strs[0]);  //设置邮件标题
			email.setMsg(strs[1]);	 // 设置邮件内容		
			email.send(); //比较耗时
			System.out.println("send email success");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    private String[]  getInfo(String msg){  //通过空格区分出标题和正文
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
