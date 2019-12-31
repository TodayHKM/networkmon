package linkcrawler.datatypes;

public class SpecialUrl {
   String id ;
   String wz;
   String mswb ;
   int lx ;
   String rw_id ;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getWz() {
	return wz;
}
public void setWz(String wz) {
	if(wz.lastIndexOf("/")==wz.length()-1){    //去掉最后的/
		this.wz = wz.substring(0, wz.length()-1);
	}else{
	    this.wz = wz;
	}
}
public String getMswb() {
	return mswb;
}
public void setMswb(String mswb) {
	this.mswb = mswb;
}
public int getLx() {
	return lx;
}
public void setLx(int lx) {
	this.lx = lx;
}
public String getRw_id() {
	return rw_id;
}
public void setRw_id(String rw_id) {
	this.rw_id = rw_id;
}
public String toString(){
	return wz;
}

}
