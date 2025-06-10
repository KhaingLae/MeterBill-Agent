package form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String username,password,home,street,block,township;
	List<String> townList;
	List<String> streetList;
	String txttown;
	String townname;
	int townid;
	String loginname;
	int first;
	int second;	
	int third;
	int total;
	String month;
	String year;
	int units;
	String expire;
	int bill;
	String receive;
	String report;
	int percent;
	int actualbill;
	String rpass;
	String npass;
	String cpass;
	int block_id;
	String blockname;
	String streetname;
	
	public List<String> getStreetList() {
		return streetList;
	}

	public void setStreetList(List<String> streetList) {
		this.streetList = streetList;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getBlockname() {
		return blockname;
	}

	public void setBlockname(String blockname) {
		this.blockname = blockname;
	}

	public int getBlock_id() {
		return block_id;
	}

	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}

	public String getRpass() {
		return rpass;
	}

	public void setRpass(String rpass) {
		this.rpass = rpass;
	}

	public String getNpass() {
		return npass;
	}

	public void setNpass(String npass) {
		this.npass = npass;
	}

	public String getCpass() {
		return cpass;
	}

	public void setCpass(String cpass) {
		this.cpass = cpass;
	}


	public int getActualbill() {
		return actualbill;
	}

	public void setActualbill(int actualbill) {
		this.actualbill = actualbill;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getThird() {
		return third;
	}

	public void setThird(int third) {
		this.third = third;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public int getTownid() {
		return townid;
	}

	public void setTownid(int townid) {
		this.townid = townid;
	}

	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}

	public List<String> getTownList() {
		return townList;
	}

	public void setTownList(List<String> townList) {
		this.townList = townList;
	}

	public String getTxttown() {
		return txttown;
	}

	public void setTxttown(String txttown) {
		this.txttown = txttown;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}



}
