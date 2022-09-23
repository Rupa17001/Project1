package com.EmployeeHibernateInheritancce;

import java.util.List;
import java.util.Scanner;

;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//object for different classes
       Employee emp = new Employee();
       Fresher fr = new Fresher();
       Experienced ex = new Experienced();
       Dao dao = new Dao();
       Scanner scan = new Scanner(System.in);
       int salary,id,hike,increment,contract,experience,count = 0;
       String name;
       while (count <6) {
    	   System.out.println("---------------- PRESS ---------------\n1. TO Register a Fresher Employee\n2. TO Register an Experienced Employee\n3. To delete an Employee\n4.To fetch detail of specific Employee\n5. To fetch all Employees detail\n6 To Exit");
    	   count = scan.nextInt();
    	   
    	   switch(count) {
    	   case 1->{
    		   dao.connect();
    		   System.out.println("Enter Name :");
    		   name= scan.next();
    		   fr.setEmpName(name);
    		  
    		   System.out.println("Enter Salary :");
    		   salary = scan.nextInt();
    		   fr.setSalary(salary);
    		   System.out.println("Enter Increment :");
    		   increment = scan.nextInt();
    		   fr.setIncrement(increment);
    		   System.out.println("Enter Contract Period :");
    		   contract = scan.nextInt();
    		   fr.setContract_Period(contract);
    		   
    	   		dao.addFrsEmp(fr);
    	   		System.out.println("Employee Registered as a Fresher");
    	   		dao.disconnect();
    	   		
    	   }
    	   
    	   // To add information of Experienced employee
    	   case 2 ->{
    		   dao.connect();// contains configuration
    		   System.out.println("Enter Name :");
    		   name= scan.next();
    		   ex.setEmpName(name);
    		   System.out.println("Enter Salary :");
    		   salary = scan.nextInt();
    		   ex.setSalary(salary);
    		   System.out.println("Enter hike :");
    		   hike = scan.nextInt();
    		   ex.setHike(hike);
    		   System.out.println("Enter Experience :");
    		   experience = scan.nextInt();
    		   ex.setExperience(experience);
    		  
    	   		dao.addExpEmp(ex);
    	   		dao.disconnect();//disconnects from the table
    	   		System.out.println("Experienced Employee is  Registered ");
    	   		
    	   }
    	   //To remove particular Employee from his/her Id
    	   case 3 ->{
    		   System.out.println("Enter the Id of the particular Employee Whose Data has to be deleted :");
    		   id = scan.nextInt();
    		   dao.connect();
    		   
    		   int ct = dao.delEmployee(id);
    		   dao.disconnect();
    		   if (ct !=0 ) {
    			   System.out.println("Employee has been removed from the table. \n TO check you can Press 5");}
    			   else{
    				   System.out.println("OOOps ! Something is wrong here.");
    			   }
    		   
    		   
    	   }
    	   
    	   // to get detail of particular employee from id 
    	   case 4->{
    		   System.out.println("Enter the Id of the particular Employee Whose Data you want to see :");
    		   id = scan.nextInt();
    		   dao.connect();
    		   int ct =  dao.getEmployee(id);
    		   dao.disconnect();
    		   if (ct<1) {
    			   System.out.println("OOOps ! Something is wrong here.");
    		   }
    		   
    	   }
    	   // retrieves the whole table 
    	   case 5 ->{
    		   dao.connect();
    		  
			List<Employee> emp1 = dao.getAll();// all the data of the table is fetched in the list
    		   dao.disconnect();
    		   for(Employee e: emp1) {
    			   System.out.println(e);
    		   }
    		   
    	   }
    	   case 6->{
    		   System.exit(0);
    	   }
    	   }//ends Switch
       
       }
       
       
    		}
 }
