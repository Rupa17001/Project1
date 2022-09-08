package com.BankProjectJDBC;
import java.util.*;
public class BankMainClass {

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		CustomerJDBC c_dao = new CustomerJDBC ();
		BankingCustomer b1 = new BankingCustomer();
		
		
		System.out.println("********* Welcome to JAVA Bank **********/n");
		
		System.out.println("Choose Operation : \n1  To Register Account \n2  To Login to Account");
		int op= scan.nextInt();
		
		switch(op) {
			case 1-> {
				// Takes information from user
				System.out.println("---------- Enter the details asked respectively to Register ----------- /n");
				System.out.println("Enter user ID : ");
				int id = scan.nextInt();
				System.out.println("Enter User Name : ");
				String name = scan.next();
				System.out.println("Enter Password : ");
				String pwd = scan.next();
				System.out.println("Enter the Balance in your account : ");
				int balance = scan.nextInt();
				
				//object passes the value to BankingCustomer class
				b1.customerId=id;
				b1.customerName=name;
				b1.customerPassword=pwd;
				b1.customerBalance= balance;
			
				c_dao.connect();// CustomerJDBC connects
			
				int check = c_dao.CustomerRegistration(b1);
				
				if (check>0) {
					System.out.println("----- YOU HAVE REGISTERED SUCCESSFULY -----");
				}
				else {
					System.out.println("!!! SOMETHING WENT WRONG ");
				}
		}
			case 2->{
				//TO login and do transactions
				System.out.println("*** LOGIN ***");
				System.out.println("Enter User Name :  ");
				String name = scan.next();
				System.out.println("Enter Password : ");
				String pwd = scan.next();
				c_dao.connect();
				int check = c_dao. Authentication(name,pwd);// calls method from CustomerJDBC class
				 if(check == 0) {
					System.out.print("Entered Details do not match.");
				}
				 else if (check == -1) {
					System.out.print("--- Not Identified ! \nYou need to REGISTER ");
				}
				
				else{
					System.out.print("Login Successful ");
					
					int op2 = 0;
					while(op2<5) {
						System.out.println("\nChoose type of TRANSACTION : \n1 TO WITHDRAW \n2 To DEPOSIT \n3 To CHECK BALANCE "
								+ "\n4 To CHANGE PIN  \n5 To EXIT");
						op2=scan.nextInt();
						
						switch(op2) {
						case 1->{
							//WITHDRAW
							System.out.print("Enter amount to WITHDRAW : ");
							int amount = scan.nextInt();
							 int result = c_dao.withdraw(check, amount);// calls method from CustomerJDBC class
							 if (result<=0) {
								 System.out.print("Something went wrong !!! ");
							 }
							 else {System.out.print("\t\t*** WITHDRAW SUCCESSFUL ***/nYour CURRENT Balance is  : "+result);}
						}
						case 2->{
							//Deposit
							System.out.print("Enter amount to Deposit : ");
							int amount = scan.nextInt();
							 int result = c_dao.deposit(check, amount);// calls method from CustomerJDBC class
							 if (result<=0) {
								 System.out.print("Something went wrong !!! ");
							 }
							 else {System.out.print("\t\t*** DEPOSIT SUCCESSFUL ***/nYour CURRENT Balance is  : "+result);
							 }
						}
						case 3->{
							//BALANCE CHECK
							System.out.print("Your CURRENT Balance is  : "+c_dao.checkBalance(check));// calls method from CustomerJDBC class
						}
						case 4->{
							//PIN CHANGE
							System.out.println("Enter your Password");
							String paswd=scan.next();
							System.out.println("Enter new password");
							String newpin=scan.next();
							int count=c_dao.pinChange(check, paswd,newpin);// calls method from CustomerJDBC class

						if(count<=0)
						{
							System.out.println("Password didn't match");
						}
						else {
							System.out.println("Password Updated");
						}
						}
						case 5->{
							System.exit(0);
						}
						}
						}
				}
				
			}
			
		
		}
		scan.close();
	}
}
