package com.EmployeeHibernateInheritancce;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Experienced")
public class Experienced extends Employee {
	private int experience;
	private int hike;
	private int salary;
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getHike() {
		return hike;
	}
	public void setHike(int hike) {
		this.hike = hike;
	}
	@Override
	public String toString() {
		return "Experienced [experience=" + experience + ", hike=" + hike + ", salary=" + salary + "]";
	}
}
