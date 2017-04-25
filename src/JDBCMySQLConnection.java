package com.theopentutorials.jdbc.db;

//step 1: use interfaces from java.sql package
import java sql Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCMySQLConnection {
    //static reference to itself
    private static JDBCMySQLConnection instance = new JDBCMySQLConnection();
    public static final String URL = "jdbc:mysql://localhost:3306/sakila"
    public static final String USER = "mbrown";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
  
    //Change to private constructor
    private JDBCMySQLConnection() {
      try {
          //Step 2: load MySQL java driver
          Class.forName(DRIVER_CLASS);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      
    }
    private Connection createConnection() {
      
      Connection connection = null;
      try {
          //Step 3: Establish Java MySQL connection
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
          System.out.println("Error unable to Connect to Database");
      }
      return connection
    }
    
    public static Connection getConnectio() {
      return instance.createConnection();
    }
    
}
//we will not be able to connect since the password is hidden so the 
//catch which is establish so your code does not crash will return a String
//error listed above.  very simple code to connect MYSQL.
