package com.java;

import java.util.List;
import java.util.Scanner;

public class Bank {
	static Scanner scan;

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
			String input = getInput();
			switch (input) {
			case "1":
				customerLogin();
				a = false;
				break;
			case "2":
				customerRegister();
				a = false;
				break;
			case "3":
				employeeLogin();
				a = false;
				break;
			case "4":
				System.exit(0);
			default:
				System.out.println("Please enter a valid input (1/2/3/4/5/6): ");
			}
		} while (a);
		scan.close();
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

	private static void customerRegister() {
		System.out.println("Welcome Valued Customer");
		System.out.println("<---------------------------------------->");
		System.out.println("Please enter your first name: ");
		String firstNameInput = getInput();
		System.out.println("Please enter your last name: ");
		String lastNameInput = getInput();
		System.out.println("Please enter your username: ");
		String usernameInput = getInput();
		System.out.println("Please enter your password: ");
		String passwordInput = getInput();
		System.out.println("Please enter your email address");
		String emailInput = getInput();
		System.out.println("Please wait for your account to be registered");
		// TODO insert an account into the table with above information.
		// TODO then, display a message saying their account has been registered
		// TODO afterward, take them to the main menu and ask for input
		showCustomerActionMenu();
	}

	private static void customerLogin() {
		System.out.println("Please enter your username: ");
		String usernameInput = getInput();
		System.out.println("Please enter your password: ");
		String passwordInput = getInput();
		System.out.println("Please wait");
		// TODO use usernameInput and passwordInput to validate customer login
		// TODO if the inputs is valid, then display the menu for available actions
	}

	private static void showCustomerActionMenu() {
		System.out.println("How may we help you today?");
		System.out.println("1. Apply for an Account");
		System.out.println("2. Show Account Infomation");
		System.out.println("3. Show Account Balancce");
		System.out.println("4. Show Personal Infomation");
		System.out.println("5. Deposit Money to an Account");
		System.out.println("6. Withdraw Money to an Account");
		System.out.println("7. Transfer Money to you Other Account");
		System.out.println("8. Log Out");
		takeUserActionInput();
	}

	private static void takeUserActionInput() {
		String actionInput = null;
		boolean validInput = false;
		do {
			switch (actionInput) {
			case "1": applyForAccount();
				validInput = true;
				break;
			case "2": showCustomerAccountInfo();
				validInput = true;
				break;
			case "3": showCustomerAccountBalance();
				validInput = true;
				break;
			case "4": showCustomerPersonalInfo();
				validInput = true;
				break;
			case "5": showDepositMenu();
				validInput = true;
				break;
			case "6": showWithdrawMenu();
				validInput = true;
				break; 
			case "7": showTransferMenu();
				validInput = true;
				break;
			case "8": showMainMenu();
			case "9":
				validInput = true;
				break;
			}
		} while (!validInput);
	}

	private static void applyForAccount() {
		// TODO Auto-generated method stub
		
	}

	private static void showCustomerAccountInfo() {
		// TODO Auto-generated method stub
		
	}

	private static void showCustomerAccountBalance() {
		// TODO Auto-generated method stub
		
	}

	private static void showCustomerPersonalInfo() {
		// TODO Auto-generated method stub
		
	}

	private static void showDepositMenu() {
		// TODO Auto-generated method stub
		
	}

	private static void showWithdrawMenu() {
		// TODO Auto-generated method stub
		
	}

	private static void showTransferMenu() {
		// TODO Auto-generated method stub
		
	}

	private static String getInput() {
		return scan.nextLine();
	}
	/*
	 * static Logger logger= Logger.getLogger(Demo1.class);
	 * logger.info()/debug()/error()/fatal();
	 */
}
