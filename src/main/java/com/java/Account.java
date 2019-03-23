package com.java;

public abstract class Account{
	protected int accountNumber;
	private int routingNumber;
	protected double balances;
	protected double accountLimit;

	void deposit(double amount, int userID) {
		if(amount < 0)
			System.out.println("Invalid amount");
		if(amount > accountLimit || (balances + amount) > accountLimit)
			System.out.println("Exceed Account Limit");
		else
			balances += amount;
	}
	void withdraw(double amount, int userID) {
		if(amount < 0)
			System.out.println("Invalid amount");
		else if(amount > accountLimit || (balances - amount) < 0)
			System.out.println("Exceed Account Limit");
		else
			balances -= amount;
	}
	void transfer(double amount, int userID, int receivingAccountID) {
		if(amount < 0)
			System.out.println("Invalid amount");
		else if(amount > accountLimit || (balances - amount) < 0)
			System.out.println("Exceed Account Limit");
		else
		{
			balances -= amount;
		}
	}
	abstract void setLimit();
	public int showAccountNumber() {
		return accountNumber;
	}
	public double showBalances() {
		return balances;
	}
}
