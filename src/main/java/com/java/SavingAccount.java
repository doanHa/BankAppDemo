package com.java;

public class SavingAccount extends Account{
	
	double annualInterestRate;
	void incurInterest(int accountID) {
		//TODO check accountID for validation
		//TODO getAnnualInterestRate from the database
		balances *= (1+annualInterestRate);
		//TODO update balances in the database
	}
	
	public static double getLimit() {
		return 10000000000000.0;
		//TODO replace the above line with getting accountLimit from the database.
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
}
