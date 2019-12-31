package linkcrawler.datatypes;

import java.util.ArrayList;

import linkcrawler.logic.htmlUnitEngine.HtmlUnitCrawler;

public class Task {
	public final static byte RUNING = 0;
	public final static byte STOP = 1;
	public final static byte CMDSTOP = 1; 
	private String id; // �����ʶ Ψһ
	private String name; // ������
	private String goal; // Ŀ����վ��ַ
	private String departmemt ;
	private int scheduleTime = 60 ; // ����ʱ�� ����λ������
	private int depth = 1; // ץȡ���
	private int timeOut = 5000 ; // ����ץȡ��ʱ ��λ  ms
	private int status ; // ״̬��1ֹͣ 0 ����
	private int operation ;//�������1ֹͣ 0 ����
	
	private String warnEmail; // ��������
	private String warnTelephone; // �����绰
    private String sendEmail;
    private String emailPassword;
	private String emailHost ; //��������������� 15850675602@139.com
	private boolean bEmailWarn = false;
	private boolean bTelephoneWarn = false;
	
	
	private HtmlUnitCrawler crawlTask ; //��Ӧ�ļ������
	
	private ArrayList<SpecialUrl>  specialUrls ;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public int getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(int scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut*1000;
	}

	public String getWarnEmail() {
		return warnEmail;
	}

	public void setWarnEmail(String warnEmail) {
		this.warnEmail = warnEmail;
	}

	public String getWarnTelephone() {
		return warnTelephone;
	}

	public void setWarnTelephone(String warnTelephone) {
		this.warnTelephone = warnTelephone;
	}

	public HtmlUnitCrawler getCrawlTask() {
		return crawlTask;
	}

	public void setCrawlTask(HtmlUnitCrawler crawlTask) {
		this.crawlTask = crawlTask;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartmemt() {
		return departmemt;
	}

	public void setDepartmemt(String departmemt) {
		this.departmemt = departmemt;
	}

	public boolean isbEmailWarn() {
		return bEmailWarn;
	}

	public void setbEmailWarn(boolean bEmailWarn) {
		this.bEmailWarn = bEmailWarn;
	}

	public boolean isbTelephoneWarn() {
		return bTelephoneWarn;
	}

	public void setbTelephoneWarn(boolean bTelephoneWarn) {
		this.bTelephoneWarn = bTelephoneWarn;
	}

	public ArrayList<SpecialUrl> getSpecialUrls() {
		return specialUrls;
	}

	public void setSpecialUrls(ArrayList<SpecialUrl> specialUrls) {
		this.specialUrls = specialUrls;
	}
}
