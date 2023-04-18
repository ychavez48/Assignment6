package com.usman.csudh.bank.core;
//Yvette Chavez (ychavez48@toromail.csudh.edu)
import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	double overdraftLimit;
	
	public CheckingAccount(Customer customer,double od) {
		super("Checking",customer);
		this.overdraftLimit=od;
	}


	//Withdrawal is not possible if the account is closed and has zero or negative
	public  void withdraw(double amount) throws InsufficientBalanceException{

		if (getBalance() + overdraftLimit - amount < 0) {
			throw new InsufficientBalanceException("Not enough funds to cover withdrawal");
		}

		super.withdraw(amount);

	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}


	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	
	
}
