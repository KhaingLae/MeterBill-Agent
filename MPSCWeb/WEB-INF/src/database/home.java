package database;

public class home {
	int hid;
	String hname;
	int bsid;
	int tid;
	int lid;
	public home(){
		
	}
	public home(int id,String name,int bsidd,int tidd,int lidd ){
		hid=id;
		hname=name;
		bsid=bsidd;
		tid=tidd;
		lid=lidd;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public int getBsid() {
		return bsid;
	}
	public void setBsid(int bsid) {
		this.bsid = bsid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
}
