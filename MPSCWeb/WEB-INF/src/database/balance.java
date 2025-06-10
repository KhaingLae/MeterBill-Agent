package database;

public class balance {
	int hid;
	int bill;
	String receivedate;
	String hname;
	
	public balance(){
		
	}
	public balance(int h,int b, String rd){
		hid=h;
		bill=b;
		receivedate=rd;
	}
	public balance(String name,int b, String rd){
		hname=name;
		bill=b;
		receivedate=rd;
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
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public String getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(String receivedate) {
		this.receivedate = receivedate;
	}

}
