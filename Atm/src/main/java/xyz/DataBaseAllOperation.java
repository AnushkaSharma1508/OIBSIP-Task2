package xyz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAllOperation {
	
static Connection con;
	
	public static Connection getConnection()
	{
		try {
			
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String user="root";
		String password="";
		String url="jdbc:mysql://localhost:3306/atm";
		
		con=DriverManager.getConnection(url,user,password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public static void addAcc(String fname,String lname,String acctype,String uname,int pin,float balance)
	{
		try {
			Connection con=DataBaseAllOperation.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into cust_info(first_name,last_name,account_type,username,pin,balance) values(?,?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, acctype);
			ps.setString(4, uname);
			ps.setInt(5, pin);
			ps.setFloat(6, balance);
			
			int i=ps.executeUpdate();
			System.out.println("Account Created successfully!");
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean login(String uname,int pin){  
		boolean status=false;  
		try{   
		     
		Connection con=DataBaseAllOperation.getConnection();
		PreparedStatement ps=con.prepareStatement(  
		"select account_no from cust_info where username=? and pin=?");  
		ps.setString(1,uname);  
		ps.setInt(2,pin);  
		      
		ResultSet rs=ps.executeQuery();  
		status=rs.next(); 
		
		          
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
		return status;  
		}  
	
	
	
	public static float getBalance(int id) {
		float balance=0;
		try {
			Connection con = DataBaseAllOperation.getConnection();
			PreparedStatement p = con.prepareStatement("select balance from cust_info where account_no=?");
			p.setInt(1, id);
			
			
			ResultSet rs = p.executeQuery();
			
			if(rs.next())
			{
				 balance=rs.getFloat(1);
				
			}
			
			con.close();
			
		}
		
		catch(Exception e1) {
			
			e1.printStackTrace();
		}
		return balance;
	}
	
	
	
	
	
	
	public static void deposit(float amount,float bal,int id)
	{
		int status = 0;
		
		try {
			Connection con = DataBaseAllOperation.getConnection();
			PreparedStatement p = con.prepareStatement("update cust_info set balance=? where account_no=?");
			float total=bal+amount;
			p.setFloat(1,total);
			p.setFloat(2,id);
			
			status = p.executeUpdate();
			con.close();
			
		}
		
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	
	
	public static void withdraw(float amount,float bal,int id)
	{
		int status = 0;
		
		try {
			Connection con = DataBaseAllOperation.getConnection();
			PreparedStatement p = con.prepareStatement("update cust_info set balance=? where account_no=?");
			float total=bal-amount;
			p.setFloat(1,total);
			p.setFloat(2,id);
			
			
			status = p.executeUpdate();
			
			con.close();
			
		}
		
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}
	
	
	
	

	

}
