package com.EmployeeHibernateInheritancce;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Fresher")
public class Fresher extends Employee {
private int contract_Period;
private int increment;
private int salary;
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public int getContract_Period() {
	return contract_Period;
}
public void setContract_Period(int contract_Period) {
	this.contract_Period = contract_Period;
}
public int getIncrement() {
	return increment;
}
public void setIncrement(int increment) {
	this.increment = increment;
}
@Override
public String toString() {
	return "Fresher [contract_Period=" + contract_Period + ", increment=" + increment + ", salary=" + salary + "]";
}

}
