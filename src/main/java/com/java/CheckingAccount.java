package com.java;

public class CheckingAccount extends Account {

	CheckingAccount(int id) {
		accountNumber= id;
	}

	@Override
	void setLimit() {
		accountLimit = 1000000000000.0;
	}
}
