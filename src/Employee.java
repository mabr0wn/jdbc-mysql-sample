package com.theopentutorials.jdbc.to;

import java.util.Date;
//to add some dates to our code.

public class Employee {
  
  private int empId;
  private String empName;
  private Date dob;
  private double salary;
  private int deptId;
  
  //creating methods, gets stay the same, sets will change.
  public int getEmpId() {
    return empId;
  }
  public void setEmpId(int empId) {
    this.empId = empId;
  }
  public String getEmpName() {
    return empName;
  }
  public void setEmpName(String empName) {
    this.empName = empName;
  }
  pubic Date getDob() {
    return dob;
  }
  public void setDob(Date dob)  {
    this.dob = dob;
  }
  public double getSalary() {
    return salary;
  }
  public void setSalary(double salary) {
    this.salary = salary;
  }
  public int getDeptId() {
    return deptId;
  }
  public void setDeptId(int deptId) {
    this.deptID = deptId;
  }
  
  @override
  public String toString() {
      return "Employee [empId=" + empId + ", empName=" + empName + ", dob=" + dob + ", salary=" + salary + ", deptId="
				+ deptId + "]";
  }
 }
