package com.java;

public class SavingAccount extends Account{
	double annualInterestRate;
	void incurInterest() {
		balances *= (1+annualInterestRate);
	}
	@Override
	void setLimit() {
		accountLimit = 100000000000.0;	
	}
}
