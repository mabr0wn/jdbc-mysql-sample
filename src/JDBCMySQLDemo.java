package com.theopentutorials.jdbc.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import com.theopentutorials.jdbc.db.DbUtil;
import com.theopentutorials.jdbc.db.JDBCMySQLConnection;
import com.theopentutorials.jdbc.to.Employee;

/*  An application involving Java with database to process any SQL statement must follow these steps:
 *  Establish a connection. (This is done by JDBCMySQLConnection class mentioned above)
 *  Create a Statement object. (Line 50) 
 *  Execute the query. (Line 51)
 *  Process the ResultSet object. This is required only for SELECT SQL query. (Line 44-51)
 *  Close the connection. (Line 57)
 *  We write a class “JDBCMySQLDemo” in package “com.theopentutorials.jdbc.main” to test JDBC MySQL connection and execute a simple JDBC SELECT query.
 *  */

public class JDBCMySQLDemo {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the EmployeeID (* for all):");
		
		int employeeId;
		try { //need to run this try statement so if there is an error code will not crash.
			String strInput = br.readLine();
			JDBCMySQLDemo demo = new JDBCMySQLDemo();
			if (strInput.equals("*"))
			{
				ArrayList<Employee> theEmps = demo.getAllEmployee();
				if (theEmps != null)
				{
					System.out.println();
					for(int i=0; i<theEmps.size(); i++)
					{
						System.out.println(theEmps.get(i));
					}
				}
			}
			else {
				employeeId = Integer.parseInt(strInput);
				Employee employee = demo.getEmployee(employeeId);
				if (employee != null)
				{
					System.out.println(employee);
				} else {
					System.out.println("Employee ID " + strInput + " not found");
				}
			}
		} catch (NumberFormatException e) { //the catch statement will look for errors in the code.
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(int employeeId) {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		
		Employee employee = null;
		String query = "SELECT * FROM employee WHERE emp_id=" + employeeId;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			
			if (rs.next()) {
				employee = new Employee();
				employee.setEmpId(rs.getInt("emp_id"));
				employee.setEmpName(rs.getString("emp_name"));
				employee.setDob(rs.getDate("dob"));
				employee.setSalary(rs.getDouble("salary"));
				employee.setDeptId((rs.getInt("dept_id")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employee;
	}
	public ArrayList<Employee> getAllEmployee() {
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		
		String query = "SELECT * FROM employee ";
		ArrayList<Employee> empList = null;
		try {
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);


			if (!rs.next()) {                            //if rs.next() returns false
                //then there are no rows.
				System.out.println("No records found");
			
			} else {
				empList = new ArrayList<Employee>();
				do {
					// Get data from the current row and use it
					Employee employee = null;
					employee = new Employee();
					employee.setEmpId(rs.getInt("emp_id"));
					employee.setEmpName(rs.getString("emp_name"));
					employee.setDob(rs.getDate("dob"));
					employee.setSalary(rs.getDouble("salary"));
					employee.setDeptId((rs.getInt("dept_id")));
					empList.add(employee);
				} while (rs.next());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empList;
	}
	

}
