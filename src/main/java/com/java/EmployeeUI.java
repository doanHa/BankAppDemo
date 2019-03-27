package com.java;

public class EmployeeUI extends UserUI{
	private static EmployeeDaoImpl employeeConn;
	protected static void employeeLogin() {
		System.out.println("Welcome to work! Please log in");
		System.out.println("Please enter your username: ");
		String usernameInput = getInput();
		System.out.println("Please enter your password: ");
		String passwordInput = getInput();
		// TODO use usernameInput and passwordInput to check for validation
		// TODO then show them the menu of what they can do
		employeeConn = new EmployeeDaoImpl();
		Employee temp = employeeConn.getEmployeeByLoginAndPassword(usernameInput, passwordInput);
		if (temp == null) {
			System.out.println("Incorrect input\n\n"); // TODO add to log file
			UserUI.showMainMenu();
		} else {
			// TODO add to log file
			showEmployeeActionMenu(temp);
		}

	}
	protected static void showEmployeeActionMenu(Employee temp) {
		System.out.println("What work wouldl you like to complete today?");
		System.out.println("1. Approve/Deny Account");
		System.out.println("2. Show Customer Account Information");
		System.out.println("3. Show Customer Account Balances");
		System.out.println("4. Show Customer Personal Information");
		System.out.println("7. Log Out");
		
	}
	private static void takeCustomerActionInput(Customer customer) {
		String actionInput = null;
		boolean validInput = false;
		// TODO add to log each user choice here.
		do {
			actionInput = getInput();
			switch (actionInput) {
			case "8":
				showMainMenu();
			default:
				System.out.println("Please enter a valid option (1 | 2 | 3 | 4 | 5 | 6 | 7 | 8)");
			}
		} while (!validInput);
	}

}
