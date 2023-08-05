package xyz;

import java.util.List;
import java.util.Scanner;

public class AtmMain {
	
	public static void main(String a[])
	{
		Scanner sc=new Scanner(System.in);
		boolean i=true;
		while(i)
		{
			System.out.println("PRESS 1 TO CREATE ACCOUNT.");
			System.out.println("PRESS 2 TO LOGIN ACCOUNT.");
			
			int choice=sc.nextInt();
			
			switch(choice)
			{
			
			case 1: System.out.println("Enter Account-Holder FirstName-");
					String fname=sc.next();
					System.out.println("Enter Account-Holder LastName-");
					String lname=sc.next();
					System.out.println("Account Type-");
					String accountype=sc.next();
					System.out.println("Enter Username(can contain letters,special characters & digits)-");
					String uname=sc.next();
					System.out.println("Enter PIN(4-digits only)-");
					int pin=sc.nextInt();
					System.out.println("Deposit Amount-");
					float balance=sc.nextFloat();
					DataBaseAllOperation.addAcc(fname,lname,accountype, uname, pin, balance);
					
					break;
					
			case 2: System.out.println("Enter Username-");
					String username=sc.next();
					System.out.println("Enter PIN-");
					int pass=sc.nextInt();
					boolean validate=DataBaseAllOperation.login(username, pass);
					
					if(validate==true)
					{
						while(i)
						{
						System.out.println("WELCOME TO THE MAIN MENU:\n");
						System.out.println("PRESS 1 TO DEPOSIT");
						System.out.println("PRESS 2 TO WITHDRAW");
						System.out.println("PRESS 3 TO VIEW BALANCE");
						System.out.println("PRESS 4 TO TRANSFER AMOUNT");
						System.out.println("PRESS 5 TO OUIT");
						int choice1=sc.nextInt();
						
						switch(choice1)
						{
								
						case 1: System.out.println("Please Enter your Account-no:");
								int accno=sc.nextInt();
								System.out.println("Please Enter Amount to deposit:");
								float amount=sc.nextFloat();
								float bal=DataBaseAllOperation.getBalance(accno);
								DataBaseAllOperation.deposit(amount,bal,accno);
								System.out.println("Amount Succesfully Deposited!");
								break;
								
						case 2: System.out.println("Please Enter your Account-no:");
								int acno=sc.nextInt();
								System.out.println("Please Enter Amount to withdraw:");
								float amt=sc.nextFloat();
								float balnce=DataBaseAllOperation.getBalance(acno);
								if(balnce>100)
								{
								DataBaseAllOperation.withdraw(amt,balnce,acno);
								System.out.println("Amount Succesfully Withdrawn!");
								System.out.println("Please collect your cash!!");
								}
								else
								{
									System.out.println("Your account has Insuficient funds");
								}
						
								break;
								
								
						case 3: System.out.println("Please Enter your Account-no:");
								int ano=sc.nextInt();
								float b=DataBaseAllOperation.getBalance(ano);
								System.out.println("BALANCE :"+b);
								
								break;
								
						case 4: System.out.println("Please Enter your Account-no:");
								int withdrawaccno=sc.nextInt();
								System.out.println("Enter Amount to Transfer:");
								float transferamt=sc.nextInt();
								System.out.println("Enter Account-no");
								int depositaccno=sc.nextInt();
							
								float cust1=DataBaseAllOperation.getBalance(withdrawaccno);
								if(cust1>100)
								{
								DataBaseAllOperation.withdraw(transferamt, cust1,withdrawaccno);
								float cust2=DataBaseAllOperation.getBalance(depositaccno);
								DataBaseAllOperation.deposit(transferamt, cust2,depositaccno);
								System.out.println("Money Transfered Successfully!!");
								}
								else
								{
									System.out.println("Insufficient funds! Transaction Cancelled");
								}
								break;
								
						case 5: System.exit(0);
						}
						
						}
					}
					
					else
					{
						System.out.println("NO ACCOUNT EXISTED FROM SUCH USERNAME!! \n PLEASE CREATE ACCOUNT!!");
					}
					
					
					
			}
		}
	}

}
