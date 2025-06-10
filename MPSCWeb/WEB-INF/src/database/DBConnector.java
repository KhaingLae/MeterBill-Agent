package database;

import java.sql.Connection;
import java.sql.DriverManager;
/*
      @auther :shashikant vaidya
      this DBConnector class created connection instance.
      getConnection() method return connection object if not exist.
*/
public class DBConnector {
      public static String url = "jdbc:mysql://localhost:3306/";
      public static String dbName = "meter";
      public static String userName = "root";
      public static String password = "";
            public static Connection getConnection() {
            Connection connection = null;
            if (connection != null)
            {
                  return connection;
            }
            try {
                       
                        Class.forName("com.mysql.jdbc.Driver").newInstance();
                        connection = DriverManager.getConnection(url+dbName,userName,password);
            } catch (Exception e) {
                  e.printStackTrace();
            }
            return connection;
      }
}
