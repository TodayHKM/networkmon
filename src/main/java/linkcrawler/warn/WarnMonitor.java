/*
 * 报警监控类，被观察对象，消息源;报警规则持有者
 */
package linkcrawler.warn;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import linkcrawler.datatypes.LinkStatus;
import linkcrawler.datatypes.Warn;
import linkcrawler.logic.htmlUnitEngine.Configuration;

public class WarnMonitor extends Observable  {
     private WarnRule  warnRuler ;
	 
     public WarnMonitor(){
    	 this.warnRuler = new DefaultWarnRuler();
     }
     public WarnMonitor(WarnRule warnRule){
    	 this.warnRuler = warnRule;
     }
     
     public WarnMonitor(Configuration conf,HashMap<String,LinkStatus> errorLinks){
    	 this.warnRuler = new DefaultWarnRuler(conf,errorLinks);
    	 this.addObserver(new EmailWarner(conf));
     }
     
     
     public void sendMessage(){
       	 
       String str = warnRuler.getWarnInfo(); //获取报警信息，报警信息由报警规则决定
       if(str != null){	 // 触发报警
	        this.setChanged();
	        this.notifyObservers(str);
       }    
     }       
     
     public Warn getWarn(){
    	 return warnRuler.getWarn();
     }
}
