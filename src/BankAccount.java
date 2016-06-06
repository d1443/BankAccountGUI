// File:         BankAccountV2
// Created:      2016/04/14 creation date
// Last Changed: Date: 2016/04/19 
// Author:       Drew Burritt

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;


	public class BankAccount
	{
			public ArrayList <Transaction> transactions = new ArrayList<Transaction> ();
		    private String accountNumber;
		    private double balance;
		    private double withdrawalFee;
		    private double anualInterestRate;
		    
		    public static void main(String[] args) 
		    {
		    	
		    	
		    }
		    
		    public ArrayList<Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime)
		    {
		    	int startPosition = 0;
		    	int endPosition= 0;
		    	if (startTime != null)
		    	{
			    	for (int i = 0; i < transactions.size(); i++)
			    	{
			    		if (startTime.isAfter(transactions.get(i).dt) || startTime.equals(transactions.get(i).dt))
			    		{
			    			if ( i <= transactions.size() -2 )
			    			{
				    			if (startTime.isBefore(transactions.get(i+1).dt))
				    			{
				    				startPosition = i;
				    				break;
				    			}
			    			}
			    			else 
			    			{
			    				startPosition = i + 1;
			    				break;
			    			}
			    		}
			    	}
		    	}
		    	if (endTime == null)
		    	{
		    		endPosition = transactions.size();
		    	}
		    	else
		    	{
			    	for (int i = 0; i < transactions.size(); i++)
			    	{
			    		if (endTime.isAfter(transactions.get(i).dt) || endTime.equals(transactions.get(i).dt))
			    		{
			    			if ( i <= transactions.size() -2 )
			    			{
				    			if (endTime.isBefore(transactions.get(i+1).dt))
				    			{
				    				endPosition = i+1;
				    				break;
				    			}
			    			}
			    			else 
			    			{
			    				endPosition = i + 1;
			    			}
			    		}
			    	}
		    	}
		    	ArrayList <Transaction> getTransactions = new ArrayList<Transaction> ();
		    	for (int i = startPosition; i < endPosition; i++)
		    	{
		    	Transaction newTransaction = new Transaction(transactions.get(i).dt,transactions.get(i).amount, transactions.get(i).description);
		    	getTransactions.add(newTransaction);
		    	}
		    	return getTransactions;
		    }
		    
		    /**
		     * Constructor with account number
		     */
		    public BankAccount(String accountNumber)
		    {
		        this.accountNumber = accountNumber;
		        this.balance = 0.00;
		        this.withdrawalFee = 0.00;
		        this.anualInterestRate = 0.00;
		    }
		    
		     /**
		     * Constructor for bank account with account number and balance
		     */
		    public BankAccount(String accountNumber, double initialBalance)
		    {
		        this.accountNumber = accountNumber;
		        this.balance = initialBalance;
		        this.withdrawalFee = 0.00;
		        this.anualInterestRate = 0.00;
		        isOverDrawn();
		    
		    }
		    
		     /**
		     * Constructor for bank account with account number, balance, withdrawal fee and annual interest rate
		     */
		       public BankAccount(String accountNumber, double initialBalance, double withdrawalFee, double anualInterestRate)
		    {
		        this.accountNumber = accountNumber;
		        this.balance = initialBalance;
		        this.withdrawalFee = withdrawalFee;
		        this.anualInterestRate = anualInterestRate;
		        isOverDrawn();
		    
		    }
		       
		    public int getTransactionIndex(LocalDateTime transactionTime)
		    {
		    	for (int i = 0; i < transactions.size(); i++)
		    	{
		    		if (transactionTime.isAfter(transactions.get(i).dt))
		    		{
		    			if ( i <= transactions.size() -2 )
		    			{
			    			if (transactionTime.isBefore(transactions.get(i+1).dt))
			    			{
			    				return i+1;
			    			}
		    			}
		    			else 
		    			{
		    				return i +1 ;
		    			}
		    		}
		    	}
		    	return 0;
		    }
		    
		    public void deposit (LocalDateTime transactionTime, double amount, String description)
		    {
		    	Transaction newTransaction = new Transaction(transactionTime, amount, description);
		    	transactions.add (getTransactionIndex(transactionTime),newTransaction);	
		    	this.balance = this.balance + amount;
		        isOverDrawn();		    }
		    
		    public void deposit(double amount, String description)
		    {
		    	Transaction newTransaction = new Transaction(amount, description);
		    	transactions.add (newTransaction);	
		    	this.balance = this.balance + amount;
		        isOverDrawn();
		    }
		    /**
		     * deposit money in bank account
		     */
		    public void deposit(double amount)
		    {
		        this.balance = this.balance + amount;
		        isOverDrawn();
		    }
		    
		    
		    public void withdraw(LocalDateTime transactionTime, double amount, String description)
		    {
		    	Transaction newTransaction = new Transaction(transactionTime, amount, description);
		    	transactions.add (getTransactionIndex(transactionTime),newTransaction);	
		    	this.balance = this.balance - (amount + this.withdrawalFee);
		        isOverDrawn();
		    }
		    
		    public void withdraw(double amount, String description)
		    {
		    	Transaction newTransaction = new Transaction(amount, description);
		    	transactions.add (newTransaction);			
		    	this.balance = this.balance - (amount + this.withdrawalFee);
		        isOverDrawn();
		    }
		    
		    /** 
		     * withdrawal money from bank account
		     */
		   public void withdraw(double amount)
		    {
		        this.balance = this.balance - (amount + this.withdrawalFee);
		        isOverDrawn();
		    }
		    /** 
		     * determines if account is overdrawn
		     */
		   public boolean isOverDrawn()

		    {
		
		        if (this.balance < 0 )
		        {
		            return true;
		        }
		        else 
		        { 
		            return false;
		        }
		       
		    }
		    
		    /** 
		     *returns account information
		     */
		   public String toString()
		   { 
		      String string;
		      DecimalFormat df = new DecimalFormat("0.00");
		      if (isOverDrawn())
		      {
		       string = "BankAccount " + this.accountNumber + ": ($" + df.format(this.balance) + ")";
		       string = string.replace("-","");
		      }  
		      else 
		      {
		       string = "BankAccount " + this.accountNumber  + ": $" + (df.format(this.balance));
		      }
		     
		    return string;
		   }
		    	  
		   public String getAccountNumber()
		   {
		     return this.accountNumber;
		   }
		    
		   public double getBalance()
		   {
		        return Math.round (this.balance * 100.0) / 100.0;  
		   }
		    
		   public double getAnnualInterestRate()
		   {
		        return this.anualInterestRate;
		   }
		    
		   public double getWithdrawalFee()
		   {
		        return this.withdrawalFee;
		   }
		    
		   public void setWithdrawalFee(double withdrawalFee)
		   {
		        this.withdrawalFee = withdrawalFee;
		   }
		    
		   public void setAnnualInterestRate(double anualInterestRate)
		   {
		        this.anualInterestRate = anualInterestRate;
		   }
		
	




	}


