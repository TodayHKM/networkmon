/*
 * ��������࣬���۲������ϢԴ;�������������
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
       	 
       String str = warnRuler.getWarnInfo(); //��ȡ������Ϣ��������Ϣ�ɱ����������
       if(str != null){	 // ��������
	        this.setChanged();
	        this.notifyObservers(str);
       }    
     }       
     
     public Warn getWarn(){
    	 return warnRuler.getWarn();
     }
}
