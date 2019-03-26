package com.java;

import java.util.Scanner;

public class UserUI {
	static Scanner scan;
	public static void showMainMenu() {
		System.out.println("Welcome to the bank");
		System.out.println("<------------------->");
		System.out.println("Please choose an option");
		System.out.println("1. Customer Login");
		System.out.println("2. Customer Register");
		System.out.println("3. Employee Login");
		System.out.println("4. Exit");
		takeUserInputMainMenu();
	}

	protected static void takeUserInputMainMenu() {
		scan = new Scanner(System.in);
		boolean validInput = false;
		do {
			String input = getInput();
			if(input.equals("1") || input.equals("2"))
			{
				new CustomerUI(input);
				validInput = true;
			}
			else if(input.contentEquals("3"))
			{
				EmployeeUI.employeeLogin();
				validInput = true;
			}
			else if(input.equals("4")) {
				scan.close();
				System.exit(0);
			} else
				System.out.println("Please enter a valid input (1 | 2 | 3 | 4): ");
		} while (!validInput);
		scan.close();
	}
	protected static String getInput() {
		return scan.nextLine();
	}

}
