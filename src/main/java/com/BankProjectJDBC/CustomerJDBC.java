package com.BankProjectJDBC;

import java.sql.*; //import java package

public class CustomerJDBC {

	Connection con = null; //initially connection is null
	
	public void connect () throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC driver
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Anudip","root","17Rup@01"); // here connection is established 
		
	}
	
	public int CustomerRegistration(BankingCustomer b1) throws Exception{
		
		String query = "Insert into BankCustomer(Id,Name,Password,Balance) values (?,?,?,?)";/* Query to insert data in table
		** note that the field's name should be same as the field's name in Database */
		
		PreparedStatement pst = con.prepareStatement(query);// object to execute query
		pst.setInt(1, b1.customerId);
		pst.setString(2, b1.customerName);
		pst.setString(3, b1.customerPassword);
		pst.setInt(4, b1.customerBalance);
		
		int count = pst.executeUpdate();
		
		return count;
	}//Method CustomerRegistration ends here
	
	
	//Method to fetch details and check the password authentication to login
	public int Authentication(String userName, String password)throws Exception
	{
		//This query takes the 
		String query ="SELECT * FROM BankCustomer WHERE Name = '"+userName+"'";
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if (rs.next()) {
			String cPassword = rs.getString(3);// it matches the entered password that's in parameter to the password in table
			
			if(cPassword.equals(password) ) {
				int cId = rs.getInt(1);
				return cId;
			}
			else {
				return 0;
				}
			
		}//outer if closes here
		else {
			return -1;
		}//outer else ends here
	}//Authentication method ends here
	
	
	public int withdraw(int cId, int amount) throws Exception
	{
		String query ="SELECT * FROM BankCustomer WHERE Id ="+cId;//query to fetches Id is stored in variable
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		
		int acBalance = rs.getInt(4);
		
		if (acBalance > amount) {//if amount > than Balance then only user can withdraw
			acBalance-=amount;// amount is subtracted
			String query2 ="update BankCustomer set Balance="+acBalance+" where Id="+cId;
			Statement st2=con.createStatement();
			st2.executeUpdate(query2);
			return acBalance;
		}
		else {
			return -1;
		}
	}
	
	
	public int deposit(int cId, int amount) throws Exception
	{
		String query ="SELECT * FROM BankCustomer WHERE Id = "+cId;//query to fetches Id is stored in variable
		
		Statement st=con.createStatement();//creates object executes the query
		ResultSet rs=st.executeQuery(query);//executes query and Stores the result of st object
		rs.next();// brings pointer to the column
		
		int acBalance = rs.getInt(4);// set existing balance value in scBalance variable
		
		
			acBalance+=amount;// adds amount entered by user to the acBAlance
			String query2 = "update BankCustomer set Balance="+acBalance+" where Id="+cId;// Updating balance in database
			Statement st2=con.createStatement();//creates object executes the query
			st2.executeUpdate(query2);//executes query
			return acBalance;
	}
	
	public int checkBalance(int cId)throws Exception{
		//checking account balance
		String query="select * from BankCustomer where Id="+cId;//query to fetches Id
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int accBal=rs.getInt(4);
		return accBal;
		
	}
	
	public int pinChange(int cId, String oldPin,String newPin)throws Exception{
		//getting details of the user
		String query="select * from BankCustomer where Id="+cId;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();//moves the pointer in DB table
		String pwd=rs.getString(3);//the rs object fetches the data from 3rd column
		//matching current password to update the password
		if(pwd.equals(oldPin)) {
			String query2="update BankCustomer set Password="+newPin+" where Id="+cId;
			PreparedStatement pst=con.prepareStatement(query2);
			int count=pst.executeUpdate();
			return count;
		}
		else {
			return -1;
		}
	}
}//class CustomerJDBC ends here

