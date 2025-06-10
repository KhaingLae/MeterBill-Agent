package database;

public class userbill {
	int hid;
	String hname;
	int bill;
	int units;
	String receiveDate;
	
	public userbill(){
		
	}
	
	public userbill(int id, int b, int u, String rd){
		hid=id;
		bill=b;
		units=u;
		receiveDate=rd;
	}
	 
	public userbill(String home, int b, int u){
		hname=home;
		bill=b;
		units=u;		
	}
	
	public userbill(String home, int b, int u, String rd){
		hname=home;
		bill=b;
		units=u;
		receiveDate=rd;
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
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

}
