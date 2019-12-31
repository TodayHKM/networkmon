package linkcrawler.datatypes;

import java.util.Date;

public class Warn {
  private String id ;
  private String msg  ;
  private Date date = new Date();
  private int type ;
  private String task_id ;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	  	this.msg = msg;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getTask_id() {
	return task_id;
}
public void setTask_id(String task_id) {
	this.task_id = task_id;
}
  
}
