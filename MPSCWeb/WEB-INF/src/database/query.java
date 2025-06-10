package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class query {
	private static final String URL = "jdbc:mysql://localhost:3306/meter";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Connection connection = null; // manages connection
	private PreparedStatement selectAllTown = null;
	private PreparedStatement selectAllStreet = null;
	private PreparedStatement selectBlockByTown = null;
	private PreparedStatement selectStreetByBlock = null;
	private PreparedStatement selectHomeByBlock = null;
	private PreparedStatement getBsid = null;
	private PreparedStatement selectBillByHome=null;
	private PreparedStatement selectNoByHomeName=null;
	private PreparedStatement selectNoByHomeNameTown=null;
	private PreparedStatement selectHomeNameByNo=null;
	private PreparedStatement selectTbid=null;
	private PreparedStatement existHome=null;
	private PreparedStatement selecttidByTname=null;
	private PreparedStatement selectTnameByTid=null;
	private PreparedStatement login=null;
	private PreparedStatement saveHome=null;
	private PreparedStatement saveLogin=null;
	private PreparedStatement findtid=null;
	private PreparedStatement findblockid=null;
	private PreparedStatement findstreetid=null;
	private PreparedStatement getLoginCount=null;
	private PreparedStatement getHome=null;
	private PreparedStatement getMeterUnits=null;
	private PreparedStatement getReceiveDate=null;
	private PreparedStatement getExpireDate=null;
	private PreparedStatement getBalanceByTown=null;
	private PreparedStatement saveReportPath=null;
	private PreparedStatement getTbid=null;
	private PreparedStatement getPdfPath=null;
	private PreparedStatement getTidFromLid=null;
	private PreparedStatement getUserBill=null;
	private PreparedStatement saveFileDetail=null;
	private PreparedStatement saveFile=null;
	private PreparedStatement getLidFromLname=null;
	private PreparedStatement getLnameFromLid=null;
	private PreparedStatement setReceiveDate=null;
	private PreparedStatement getTidFromHid=null;
	private PreparedStatement getLidFromTid=null;
	private PreparedStatement getBalanceByYear=null;
	private PreparedStatement changePassword=null;
	private PreparedStatement getBidFromBname=null;
	// constructor
	public query() {
		try

		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// create query that selects all entries in the AddressBook
			selectAllTown = connection
					.prepareStatement("SELECT tname FROM town");
			selectAllStreet = connection
					.prepareStatement("SELECT sname FROM street");
			selectBlockByTown = connection
					.prepareStatement("SELECT bname FROM block WHERE tid=?");
			selectStreetByBlock = connection
					.prepareStatement("SELECT s.sname from street s, blockstreet bs where s.sid=bs.sid and bs.bid=?");
			selecttidByTname=connection
					.prepareStatement("SELECT t.tid from town t where t.tname=?");
			selectTnameByTid=connection
					.prepareStatement("SELECT t.tname from town t where t.tid=?");
			selectHomeByBlock = connection.prepareStatement("SELECT * FROM home WHERE bsid=?");
			selectTbid = connection.prepareStatement("SELECT * FROM totalbill WHERE year=?");
			selectBillByHome=connection.prepareStatement("Select hb.Bal from homebill hb where hb.hid=?");
			selectNoByHomeName=connection.prepareStatement("Select h.hid from home h where h.hname=? and h.bsid=?");
			selectNoByHomeNameTown=connection.prepareStatement("Select h.hid from home h where h.hname=? and h.tid=?");
			selectHomeNameByNo=connection.prepareStatement("Select h.hname from home h where h.hid=?");
			existHome=connection.prepareStatement("Select * from home h where h.tid=? and h.hname=?");
			login=connection.prepareStatement("Select * from login h where h.username=? and h.password=?");
			saveHome=connection.prepareStatement("Insert into home(hname,bsid,tid,lid) values (?,?,?,?)");
			saveLogin=connection.prepareStatement("Insert into login(username,password,usertype) values (?,?,?)");
			saveFileDetail=connection.prepareStatement("Insert into detailbill(hid,bill,units,receivedate,tbid) values (?,?,?,?,?)");
			saveFile=connection.prepareStatement("Insert into totalbill(tid,month,year,expiredate,report) values (?,?,?,?,?)");
			findblockid=connection.prepareStatement("Select b.bid from block b where b.bname=? and b.tid=?");
			findstreetid=connection.prepareStatement("Select s.sid from street s where s.sname=?");
			getBsid = connection.prepareStatement("SELECT bsid from blockstreet where bid=? and sid=?");
			getLoginCount = connection.prepareStatement("SELECT * from login");
			getHome=connection.prepareStatement("SELECT * from home h where h.lid=?");
			getTbid=connection.prepareStatement("Select tb.tbid from totalbill tb where tb.tid=? and tb.year=? and tb.month=?");
			getMeterUnits=connection.prepareStatement("Select db.units from detailbill db where db.hid=? and db.tbid=?");
			getReceiveDate=connection.prepareStatement("Select db.receivedate from detailbill db where db.hid=? and db.tbid=?");
			getExpireDate=connection.prepareStatement("Select tb.expiredate from totalbill tb where tb.tid=? and tb.year=? and tb.month=?");
			getBalanceByTown=connection.prepareStatement("Select db.hid,db.bill,db.receivedate from detailbill db where db.tbid=?");
			getBalanceByYear=connection.prepareStatement("Select db.hid,db.bill,db.units,db.receivedate from detailbill db where db.tbid=? and db.hid=?");
			saveReportPath=connection.prepareStatement("update totalbill set report=? where tid=? and year=? and month=?");
			getPdfPath=connection.prepareStatement("Select tb.report from totalbill tb where tb.tid=? and tb.year=? and tb.month=?");
			getTidFromLid = connection.prepareStatement("SELECT t.tid FROM town t where t.lid=?");
			getUserBill=connection.prepareStatement("Select db.hid,db.bill,db.units, db.receivedate from detailbill db where db.tbid=?");
			getLidFromLname = connection.prepareStatement("SELECT l.lid FROM login l where l.username=?");
			getLnameFromLid = connection.prepareStatement("SELECT l.username FROM login l where l.lid=?");
			setReceiveDate=connection.prepareStatement("update detailbill set receivedate=? where hid=? and tbid=?");
			getTidFromHid=connection.prepareStatement("Select h.tid from home h where h.hid=?");
			getLidFromTid=connection.prepareStatement("Select t.lid from town t where t.tid=?");
			changePassword=connection.prepareStatement("update login set password=? where username=?");
			getBidFromBname = connection.prepareStatement("SELECT b.bid FROM block b where b.bname=?");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch

	} 
	
	public int getLoginCount(){
		int count=0;
		ResultSet resultSet = null;
		
		try {
			
			resultSet= getLoginCount.executeQuery();
			
			while (resultSet.next()) {
				count=resultSet.getInt("lid");				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return count;
	}
	
	public int getBsid(int bid,int sid){
		int bsid=0;
		ResultSet resultSet = null;
		
		try {
			getBsid.setInt(1,bid);
			getBsid.setInt(2, sid);
			resultSet= getBsid.executeQuery();
			
			while (resultSet.next()) {
				bsid=resultSet.getInt("bsid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return bsid;
	}
	public int findblockid(String block,int town){
		int bid=0;
		ResultSet resultSet = null;
		
		try {
			findblockid.setString(1,block);
			findblockid.setInt(2, town);
			resultSet= findblockid.executeQuery();
			
			while (resultSet.next()) {
				bid=resultSet.getInt("bid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return bid;
	}
	public int findstreetid(String street){
		int sid=0;
		ResultSet resultSet = null;
		System.out.println("street name..."+street);
		try {
			findstreetid.setString(1,street);
			resultSet= findstreetid.executeQuery();
			
			while (resultSet.next()) {
				sid=resultSet.getInt("sid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return sid;
	}
	
	public int getTidFromLid(int lid){
		int tid=0;
		ResultSet resultSet = null;
		
		try {
			getTidFromLid.setInt(1, lid);
			resultSet= getTidFromLid.executeQuery();
			
			while (resultSet.next()) {
				tid=resultSet.getInt("tid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return tid;
	}
	public int getLidFromLname(String name){
		int lid=0;
		ResultSet resultSet = null;
		
		try {
			getLidFromLname.setString(1, name);
			resultSet= getLidFromLname.executeQuery();
			
			while (resultSet.next()) {
				lid=resultSet.getInt("lid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return lid;
	}
	public int getBidFromBname(String name){
		int bid=0;
		ResultSet resultSet = null;
		
		try {
			getBidFromBname.setString(1, name);
			resultSet= getBidFromBname.executeQuery();
			
			while (resultSet.next()) {
				bid=resultSet.getInt("bid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return bid;
	}
	public String getLnameFromLid(int lid){
		String name="";
		ResultSet resultSet = null;
		
		try {
			getLnameFromLid.setInt(1, lid);
			resultSet= getLnameFromLid.executeQuery();
			
			while (resultSet.next()) {
				name=resultSet.getString("username");
								
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return name;
	}
	public int getTidFromHid(int hid){
		int tid=0;
		ResultSet resultSet = null;
		
		try {
			getTidFromHid.setInt(1, hid);
			resultSet= getTidFromHid.executeQuery();
			
			while (resultSet.next()) {
				tid=resultSet.getInt("tid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return tid;
	}
	public int getLidFromTid(int tid){
		int lid=0;
		ResultSet resultSet = null;
		
		try {
			getLidFromTid.setInt(1, tid);
			resultSet= getLidFromTid.executeQuery();
			
			while (resultSet.next()) {
				lid=resultSet.getInt("lid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return lid;
	}
	public void saveFileDetail(int hid, int units, int tbid,int bill){
		try {
		System.out.println("saving hid : "+hid+" units : "+units);
		saveFileDetail.setInt(1, hid);
		saveFileDetail.setInt(2, bill);
		saveFileDetail.setInt(3, units);
		saveFileDetail.setString(4, "");
		saveFileDetail.setInt(5, tbid);		
		saveFileDetail.executeUpdate();
		
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void saveFile(int tid,int month, int year,String expire){
		try {
		System.out.println("saving tid : "+tid+" month : "+month+" year :"+year);
		saveFile.setInt(1, tid);
		saveFile.setInt(2, month);
		saveFile.setInt(3, year);
		saveFile.setString(4, expire);
		saveFile.setString(5, "");	
		saveFile.executeUpdate();
		
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void saveHome(String hname,int bsid,int tid, int lid){
		try {
		saveHome.setString(1, hname);
		saveHome.setInt(2, bsid);
		saveHome.setInt(3, tid);
		saveHome.setInt(4, lid);
		saveHome.executeUpdate();
		System.out.println("insert "+hname);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void saveLogin(String username,String password,int usertype){
		try {
			saveLogin.setString(1, username);
			saveLogin.setString(2, password);
			saveLogin.setInt(3, usertype);
			saveLogin.executeUpdate();
		System.out.println("insert "+username);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void saveReportPath(String report, int tid,int year,int month){
		try {
			saveReportPath.setString(1, report);
			saveReportPath.setInt(2, tid);
			saveReportPath.setInt(3, year);
			saveReportPath.setInt(4, month);
			saveReportPath.executeUpdate();
		System.out.println("insert "+report);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void setReceiveDate(String rdate, int hid,int tbid){
		try {
			setReceiveDate.setString(1, rdate);
			setReceiveDate.setInt(2, hid);
			setReceiveDate.setInt(3, tbid);
			setReceiveDate.executeUpdate();
		System.out.println("insert "+rdate+" in hid "+hid+ " and tbid "+tbid);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	public void changePassword(String pass,String name){
		try {
			changePassword.setString(1, pass);
			changePassword.setString(2, name);
			changePassword.executeUpdate();
		System.out.println("change password "+pass+" to username "+name);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	
	public List<login> login(String username,String pwd) {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			login.setString(1,username);
			login.setString(2, pwd);
			resultSet = login.executeQuery();
			
			while (resultSet.next()) {
				results.add(new login(resultSet.getInt("lid"),resultSet.getString("username")
						,resultSet.getString("password"),resultSet.getInt("usertype")));
				
			} // end while
		} // end trys
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}

	public List<home> getHome(int lid) {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			getHome.setInt(1, lid);
			resultSet = getHome.executeQuery();
			
			while (resultSet.next()) {
				results.add(new home(resultSet.getInt("hid"),resultSet.getString("hname")
						,resultSet.getInt("bsid"),resultSet.getInt("tid"),resultSet.getInt("lid")));
				
			} // end while
		} // end trys
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}
	
	public List<balance> getBalanceByTown(int tbid) {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			System.out.println("tbid..."+tbid);
			getBalanceByTown.setInt(1, tbid);
			resultSet = getBalanceByTown.executeQuery();
			
			while (resultSet.next()) {
				results.add(new balance(resultSet.getInt("hid"), resultSet.getInt("bill"),
						resultSet.getString("receivedate")));
				
			} // end while
		} // end trys
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}
	
	public userbill getBalanceByYear(int tbid, int hid) {
		userbill ub=null;
		ResultSet resultSet = null;
		try {
			
			getBalanceByYear.setInt(1, tbid);
			getBalanceByYear.setInt(2,hid);
			resultSet = getBalanceByYear.executeQuery();
			
			while (resultSet.next()) {
				
				ub=new userbill(resultSet.getInt("hid"), resultSet.getInt("bill"),resultSet.getInt("units"),
						resultSet.getString("receivedate"));
				
			} // end while
		
		} // end trys
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return ub;
	}
	
	public List<userbill> getUserBill(int tbid) {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			System.out.println("tbid..."+tbid);
			getUserBill.setInt(1, tbid);
			resultSet = getUserBill.executeQuery();
			
			while (resultSet.next()) {
				results.add(new userbill(resultSet.getInt("hid"), resultSet.getInt("bill"),resultSet.getInt("units"),
						resultSet.getString("receivedate")));
				
			} // end whileus
		} // end trys
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}
	
	public int getTbid(int tid, int year, int month){
		ResultSet resultSet = null;
		int tbid=0;
		try {
			System.out.println("tid : "+tid+" year : "+year+" month :"+month);
			getTbid.setInt(1,tid);
			getTbid.setInt(2,year);
			getTbid.setInt(3,month);
			resultSet=getTbid.executeQuery();	
		while(resultSet.next()){		
				tbid=resultSet.getInt("tbid");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return tbid;
	}
	
	public String getPdfPath(int tid, int year, int month){
		ResultSet resultSet = null;
		String report="";
		try {
			getPdfPath.setInt(1,tid);
			getPdfPath.setInt(2,year);
			getPdfPath.setInt(3,month);
			resultSet=getPdfPath.executeQuery();	
		while(resultSet.next()){		
				report=resultSet.getString("report");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return report;
	}
	
	public int getMeterUnits(int hid, int tbid){
	ResultSet resultSet = null;
	int units=0;
	try {
		getMeterUnits.setInt(1,hid);
		getMeterUnits.setInt(2,tbid);
		resultSet=getMeterUnits.executeQuery();	
	while(resultSet.next()){		
			units=resultSet.getInt("units");			
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			resultSet.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
			close();
		} // end catch
	} // end finally
	return units;
}
	public String getReceiveDate(int hid, int tbid){
		ResultSet resultSet = null;
		String rdate="";
		try {
			getReceiveDate.setInt(1,hid);
			getReceiveDate.setInt(2,tbid);
			resultSet=getReceiveDate.executeQuery();	
		while(resultSet.next()){		
				rdate=resultSet.getString("receivedate");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return rdate;
	}
	
	public String getExpireDate(int tid, int year, int month){
		ResultSet resultSet = null;
		String expire="";
		try {
			getExpireDate.setInt(1,tid);
			getExpireDate.setInt(2,year);
			getExpireDate.setInt(3,month);
			resultSet=getExpireDate.executeQuery();	
			System.out.println(tid+ " "+year+" "+month);
		while(resultSet.next()){		
				expire=resultSet.getString("expiredate");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return expire;
	}


	public int selecttidByTname(String town){
		ResultSet resultSet = null;
		int tid=0;
		try {
			selecttidByTname.setString(1, town);
		resultSet=selecttidByTname.executeQuery();	
		while(resultSet.next()){		
				tid=resultSet.getInt("tid");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return tid;
	}
	public String selectTnameByTid(int tid){
		ResultSet resultSet = null;
		String tname="";
		try {
			selectTnameByTid.setInt(1,tid);
		resultSet=selectTnameByTid.executeQuery();	
		while(resultSet.next()){		
			tname=resultSet.getString("tname");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return tname;
	}
	public String selectHomeNameByNo(int no){
		ResultSet resultSet = null;
		String homename="";
		try {
			selectHomeNameByNo.setInt(1, no);
		resultSet=selectHomeNameByNo.executeQuery();	
		while(resultSet.next()){		
				homename=resultSet.getString("hname");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return homename;
	}
	
	public int selectNoByHomeName(String homename, int bsid){
		ResultSet resultSet = null;
		int homeno=0;
		try {
			selectNoByHomeName.setString(1, homename);
			selectNoByHomeName.setInt(2, bsid);
		resultSet=selectNoByHomeName.executeQuery();	
		while(resultSet.next()){		
				homeno=resultSet.getInt("hid");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return homeno;
	}
	
	public int selectNoByHomeNameTown(String homename, int tid){
		ResultSet resultSet = null;
		int homeno=0;
		try {
			selectNoByHomeNameTown.setString(1, homename);
			selectNoByHomeNameTown.setInt(2, tid);
		resultSet=selectNoByHomeNameTown.executeQuery();	
		while(resultSet.next()){		
				homeno=resultSet.getInt("hid");			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return homeno;
	}
	
	public int selectBillByHome(int hno){
		ResultSet resultSet = null;
		int bil=0;
		try {
			selectBillByHome.setInt(1, hno);
		resultSet=selectBillByHome.executeQuery();	
		while(resultSet.next()){
		
				bil=resultSet.getInt("Bal");
			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return bil;
	}

	public List selectAllTown() {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			resultSet = selectAllTown.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				results.add(resultSet.getString("tname"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}

	public List selectAllStreet() {
		List results=new ArrayList();
		ResultSet resultSet = null;
		try {
			resultSet = selectAllStreet.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				results.add(resultSet.getString("sname"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}

	public List selectBlockByTown(int tid) {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
			selectBlockByTown.setInt(1, tid);
			resultSet = selectBlockByTown.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				results.add(resultSet.getString("bname"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}

	public List selectStreetByBlock(int bid) {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
			selectStreetByBlock.setInt(1, bid);
			resultSet = selectStreetByBlock.executeQuery();
			int i = 0;
			System.out.println("bid..."+bid);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("sname"));
				results.add(resultSet.getString("sname"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}

	public List selectHomeByBlock() {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
			resultSet = selectHomeByBlock.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				results.add(resultSet.getString("sname"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}
	
	public List selectTbid(int year) {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
			selectTbid.setInt(1, year);
			resultSet = selectTbid.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				results.add(resultSet.getInt("tbid"));
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return results;
	}
	
	public Boolean existHome(int t,String h) {
		Boolean exist=false;
		ResultSet resultSet = null;
		
		try {
			existHome.setInt(1, t);
			existHome.setString(2, h);
			resultSet = existHome.executeQuery();
			
			while (resultSet.next()) {
				exist=true;
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		finally {
			try {
				resultSet.close();
			} // end try
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
				close();
			} // end catch
		} // end finally
		return exist;
	}

	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
	} // end method close
} // end class PersonQueries

