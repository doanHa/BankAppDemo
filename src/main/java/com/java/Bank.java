package com.java;

import java.util.Scanner;

public class Bank {
	static Scanner scan;
	Customer[] customerDatabase;
	Account[] accountDatabase;

	public static void main(String[] args) {
		showMainMenu();
		takeUserInputMainMenu();
	}

	private static void showMainMenu() {
		System.out.println("Welcome to the bank");
		System.out.println("<------------------->");
		System.out.println("Please choose an option");
		System.out.println("1. Customer Login");
		System.out.println("2. Customer Register");
		System.out.println("3. Employee Login");
		System.out.println("4. Exit");
	}

	private static void takeUserInputMainMenu() {
		scan = new Scanner(System.in);
		boolean a = true;
		do {
			String input = scan.nextLine();
			switch (input) {
			case "1": customerLogin();
				a = false; break;
			case "2": customerRegister();
				a = false; break;
			case "3": employeeLogin();
				a = false; break;
			case "4": System.exit(0);
			default:
				System.out.println("Please enter a valid input (1/2/3/4/5/6): ");
			}
		} while (a);
		scan.close();
	}

	private static void employeeLogin() {
		// TODO Auto-generated method stub1
		
		System.out.println("Welcome to work! Please log in");
		System.out.println("Please enter your username: ");
		String usernameInput = scan.nextLine();
		System.out.println("Please enter your password: ");
		String passwordInput = scan.nextLine();
//		System.out.println("Please enter your employee id: ");
//		int idInput = Integer.valueOf(scan.nextLine());
	}

	private static void customerRegister() {
		// TODO Auto-generated method stub
		System.out.println("Welcome Valued Customer");
		System.out.println("<---------------------------------------->");
		System.out.println("Please enter your first name: ");
		String firstNameInput = scan.nextLine();
		System.out.println("Please enter your last name: ");
		String lastNameInput = scan.nextLine();
		System.out.println("Please enter your username: ");
		String usernameInput = scan.nextLine();
		System.out.println("Please enter your password: ");
		String passwordInput = scan.nextLine();
		int ssnInput = -1;
		System.out.println("Please enter your ssn");
		do {
			try {
				System.out.println("Please enter your password: ");
				ssnInput = Integer.parseInt(scan.nextLine());
				break;
			}catch(NumberFormatException numFormatEx) {
				System.out.println("Please enter a valid ssn");
			}
		}while(true);
		System.out.println("Please wait for your account to be registered");
//		Customer temp = new Customer();
//		temp.register(firstNameInput, lastNameInput, usernameInput, passwordInput, ssnInput);
//		temp = null;
//		System.out.println("Your account has finished register");
	}

	private static void customerLogin() {
		// TODO Auto-generated method stub
		System.out.println("Please enter your username: ");
		String usernameInput = scan.nextLine();
		System.out.println("Please enter your password: ");
		String passwordInput = scan.nextLine();
		System.out.println("Please wait");
	}
	/*
	 * static Logger logger= Logger.getLogger(Demo1.class);
	 * logger.info()/debug()/error()/fatal();
	 */
}
