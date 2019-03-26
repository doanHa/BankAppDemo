package com.java;

public class Customer extends User{
	  private int customerID;

	 Customer() {

	 }


	Customer(String lastname, String firstname, String userEmail, String login, String password) {
		this.lastName = lastname;
		this.firstName = firstname;
		this.userEmail = userEmail;
		this.login = login;
		this.password = password;
	}

	public void register(String firstNameInput, String lastNameInput, String loginInput, String passwordInput,
						 String email) {
		firstName = firstNameInput;
		lastName = lastNameInput;
		login = loginInput;
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


	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
