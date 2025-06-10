package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bankquery {
	private static final String URL = "jdbc:mysql://localhost:3306/bank";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Connection connection = null; // manages connection
	private PreparedStatement getAccountid=null;
	private PreparedStatement getBalance=null;
	private PreparedStatement updateBalance=null;
	
	public bankquery() {
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
			getAccountid=connection
					.prepareStatement("SELECT l.accountid FROM linkmpsc l WHERE l.lname=?");		
			getBalance=connection.prepareStatement("Select a.balance From account a where a.accountid=?");
			updateBalance=connection.prepareStatement("update account set balance=? where accountid=?");
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch

	} 
	
	public void updateBalance(int bal, int aid){
		try {
			updateBalance.setInt(1, bal);
			updateBalance.setInt(2, aid);
			updateBalance.executeUpdate();
		System.out.println("update "+bal);
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		}
	
	public int getBalance(int aid){
		int bal=0;
		ResultSet resultSet = null;
		
		try {
			getBalance.setInt(1,aid);
			resultSet= getBalance.executeQuery();
			
			while (resultSet.next()) {
				bal=resultSet.getInt("balance");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return bal;
	}
	
	public int getAccountid(String lname){
		int sid=0;
		ResultSet resultSet = null;
		
		try {
			getAccountid.setString(1,lname);
			resultSet= getAccountid.executeQuery();
			
			while (resultSet.next()) {
				sid=resultSet.getInt("accountid");
				
			} // end while
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
		return sid;
	}
}
