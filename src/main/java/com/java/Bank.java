package com.java;

import java.util.Scanner;

public class Bank {
	// HACK: Need to make sure we handles the NullPointerExceptions from passing
	// values for records that do not exist
	// (i.e user passing in wrong log in info, same with employer)
	public static void main(String[] args) {
		UserUI.showMainMenu();
	}


	private static void employeeLogin() {
		System.out.println("Welcome to work! Please log in");
		System.out.println("Please enter your username: ");
		String usernameInput = getInput();
		System.out.println("Please enter your password: ");
		String passwordInput = getInput();
		// TODO use usernameInput and passwordInput to check for validation
		// TODO then show them the menu of what they can do

	}



	/*
	 * static Logger logger= Logger.getLogger(Demo1.class);
	 * logger.info()/debug()/error()/fatal();
	 */
}
