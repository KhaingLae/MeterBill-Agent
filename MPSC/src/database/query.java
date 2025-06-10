package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class query {
	private static final String URL = "jdbc:mysql://localhost:3306/addressbookdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Connection connection = null; // manages connection
	private PreparedStatement selectAllTown = null;
	private PreparedStatement selectBlockByTown = null;
	private PreparedStatement selectStreetByBlock = null;
	private PreparedStatement selectHomeByBlock = null;
	private PreparedStatement getBsid = null;

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
			selectBlockByTown = connection
					.prepareStatement("SELECT bname FROM block WHERE tid=?");
			selectStreetByBlock = connection
					.prepareStatement("SELECT s.sname from street s, blockstreet bs where s.sid=bs.sid and bs.bid=?");
			getBsid = connection
					.prepareStatement("SELECT bsid from blockstreet where sid=? and bid=?");
			selectHomeByBlock = connection
					.prepareStatement("SELECT * FROM home WHERE bsid=?");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.exit(1);
		} // end catch

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

	public List selectBlockByTown() {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
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

	public List selectStreetByBlock() {
		List results = new ArrayList();
		ResultSet resultSet = null;
		try {
			resultSet = selectStreetByBlock.executeQuery();
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

	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} // end catch
	} // end method close
} // end class PersonQueries

