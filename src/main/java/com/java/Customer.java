package com.java;

public class Customer extends User{
	private int customerID;

	public void register(String firstNameInput, String lastNameInput, String usernameInput, String passwordInput, String email) {
		firstName = firstNameInput;
		lastName = lastNameInput;
		username = usernameInput;
		password = passwordInput;
		userEmail = email;
		//TODO implement register new customer to database
		// implement check if customer is already in the database
		// implement check for valid input
	}
	public void applyForAccount(Account accountType) {
		//TODO implement this method.
		System.out.println("applyForAccount is not implemented yet");
	}
}
