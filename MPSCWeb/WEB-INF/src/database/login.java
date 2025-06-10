package database;

public class login {
	int lid;
	String username;
	String password;
	int usertype;
	login(){
		
	}
	login(int id,String uname,String pwd, int type){
		lid=id;
		username=uname;
		password=pwd;
		usertype=type;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
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
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	
	

}
