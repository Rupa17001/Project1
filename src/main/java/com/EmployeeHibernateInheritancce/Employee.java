package com.EmployeeHibernateInheritancce;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//what it does :  no need to input the Id manually,it auto increments ID(primary key), 
private int empId;
private String empName;
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
@Override
public String toString() {
	return "Employee [empId=" + empId + ", empName=" + empName + "]";
}
}
