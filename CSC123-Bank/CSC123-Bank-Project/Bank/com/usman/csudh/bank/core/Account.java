package com.usman.csudh.bank.core;
//Yvette Chavez (ychavez48@toromail.csudh.edu
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static String accountName;
	private static Customer accountHolder;
	private static ArrayList<Transaction> transactions;
	 private static List<Account> accounts;
	private boolean open=true;
	private int accountNumber;

	protected Account(String name, Customer customer) {
		accountName=name;
		accountHolder=customer;
		transactions=new ArrayList<Transaction>();
		accountNumber=uniquecounter.nextValue();
	}
	 public Account(List<Account> accounts) {
	        this.accounts = accounts;
	    }
	
	public String getAccountName() {
		return accountName;
	}

	public Customer getAccountHolder() {
		return accountHolder;
	}
	
	public static void listAccount(int accountNumber) {

        // look up the account in the accounts list
        Account account = null;
        for (Account a : accounts) {
            if (a.getAccountNumber() == accountNumber) {
                account = a;
                break;
            }
        }
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        String accountStatus = account.getAccountStatus();

        // display the account details to the user
        System.out.printf("%d(%s): %s: %s: %.2f: %.2f: %s\n",
                accountNumber, accountName, accountHolder, getBalance(), accountStatus);
    }
        
	private String getAccountStatus() {
		// TODO Auto-generated method stub
		return null;
	}



	public static double getBalance() {
		double workingBalance=0;
		
		for(Transaction t: transactions) {
			if(t.getType()==Transaction.CREDIT)workingBalance+=t.getAmount();
			else workingBalance-=t.getAmount();
		}
		
		return workingBalance;
	}
	
	
	
	
	public void deposit(double amount)  throws AccountClosedException{
		double balance=getBalance();
		if(!isOpen()&&balance>=0) {
			throw new AccountClosedException("\nAccount is closed with positive balance, deposit not allowed!\n\n");
		}
		transactions.add(new Transaction(Transaction.CREDIT,amount));
	}
	
	
	
	
	public void withdraw(double amount) throws InsufficientBalanceException {
			
		double balance=getBalance();
			
		if(!isOpen()&&balance<=0) {
			throw new InsufficientBalanceException("\nThe account is closed and balance is: "+balance+"\n\n");
		}
		
		transactions.add(new Transaction(Transaction.DEBIT,amount));
	}
	
	public void close() {
		open=false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public String toString() {
		String aName=accountNumber+"("+accountName+")"+" : "+accountHolder.toString()+ " : "+getBalance()+" : "+(open?"Account Open":"Account Closed");
		return aName;
	}
	 
	public void printTransactions(OutputStream out) throws IOException {
		
		out.write("\n\n".getBytes());
		out.write("------------------\n".getBytes());
	
		for(Transaction t: transactions) {
			out.write(t.toString().getBytes());
			out.write((byte)10);
		}
		out.write("------------------\n".getBytes());
		out.write(("Balance: "+getBalance()+"\n\n\n").getBytes());
		out.flush();
		
	}
}