package com.java;

public abstract class Account{
	protected int accountNumber;
	private int routingNumber;
	protected double balances;
	private char accountType;
	protected double accountLimit;
	private char accountStatus;

	void deposit(double amount, int userID, int receivingAccountID) {
		//TODO check userID for validation
		//TODO if userID is valid, get balances from database and put it in balances variable
		if(amount < 0)
			System.out.println("Invalid amount");
		if(amount > accountLimit || (balances + amount) > accountLimit)
			System.out.println("Exceed Account Limit");
		else
			balances += amount;
		//TODO update balances in the database
	}
	void withdraw(double amount, int userID) {
		//TODO check userID for validation
		//TODO if userID is valid, get balances from database and put it in balances variable
		if(amount < 0)
			System.out.println("Invalid amount");
		else if(amount > accountLimit || (balances - amount) < 0)
			System.out.println("Exceed Account Limit");
		else
			balances -= amount;
		//TODO update balances in the database
	}
	void transfer(double amount, int userID, int receivingAccountID) {
		//TODO check userID for validation
		//TODO check receivingAccountID to see if it belong to userID
		//TODO if userID is valid, get balance from database and put it in balances variable
		//TODO if receivingAccountID belong to the user, then do the following.
		if(amount < 0)
			System.out.println("Invalid amount");
		else if(amount > accountLimit || (balances - amount) < 0)
			System.out.println("Exceed Account Limit");
		else
		{
			balances -= amount;
			//TODO deposit into the account receiving account money
		}
		//TODO update balances in the database
	}
	public int showAccountNumber() {
		return accountNumber;
	}
	public double showBalances() {
		return balances;
	}


	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(int routingNumber) {
		this.routingNumber = routingNumber;
	}

	public double getBalances() {
		return balances;
	}

	public void setBalances(double balances) {
		this.balances = balances;
	}

	public char getAccountType() {
		return accountType;
	}

	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	public double getAccountLimit() {
		return accountLimit;
	}

	public void setAccountLimit(double accountLimit) {
		this.accountLimit = accountLimit;
	}

	public char getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(char accountStatus) {
		this.accountStatus = accountStatus;
	}
}
