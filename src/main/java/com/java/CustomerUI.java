package com.java;

public class CustomerUI extends UserUI {
	private static CustomerDaoImpl customerConnection;

	public CustomerUI(String input) {
		switch (input) {
		case "1":
			customerLogin();
			break;
		case "2":
			customerRegister();
			break;
		}
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
		customerConnection = new CustomerDaoImpl();
		Customer temp = new Customer();
		temp = temp.register(firstNameInput, lastNameInput, usernameInput, passwordInput, emailInput);
		if (customerConnection.insertCustomer(temp)) {
			System.out.println("Your account has been registered.\nYou can log into your account by using you username and password.");
		} else {
			System.out.println("Your account could not be registered at this time, please try again later");
		}
		// TODO add to log file
		UserUI.showMainMenu();
	}

	private static void customerLogin() {
		System.out.println("Please enter your username: ");
		String usernameInput = getInput();
		System.out.println("Please enter your password: ");
		String passwordInput = getInput();
		System.out.println("Please wait");
		// TODO use usernameInput and passwordInput to validate customer login
		// TODO if the inputs is valid, then display the menu for available actions
		customerConnection = new CustomerDaoImpl();
		Customer temp = customerConnection.getCustomerByLoginAndPassword(usernameInput, passwordInput);
		if (temp == null) {
			System.out.println("Incorrect input\n\n"); // TODO add to log file
			UserUI.showMainMenu();
		} else {
			// TODO add to log file
			showCustomerActionMenu(temp);
		}

	}

	private static void showCustomerActionMenu(Customer customer) {
		System.out.println("How may we help you today, " + customer.getFirstName() + " ?");
		System.out.println("1. Apply for an Account");
		System.out.println("2. Show Account Infomation");
		System.out.println("3. Show Account Balancce");
		System.out.println("4. Show Personal Infomation");
		System.out.println("5. Deposit Money to an Account");
		System.out.println("6. Withdraw Money to an Account");
		System.out.println("7. Transfer Money to you Other Account");
		System.out.println("8. Log Out");
		takeCustomerActionInput(customer);
	}

	private static void takeCustomerActionInput(Customer customer) {
		String actionInput = null;
		boolean validInput = false;
		// TODO add to log each user choice here.
		do {
			actionInput = getInput();
			switch (actionInput) {
			case "1":
				applyForAccount();
				validInput = true;
				break;
			case "2":
				showCustomerAccountInfo(customer);
				validInput = true;
				break;
			case "3":
				showCustomerAccountBalance(customer);
				validInput = true;
				break;
			case "4":
				showCustomerPersonalInfo(customer);
				validInput = true;
				break;
			case "5":
				showDepositMenu(customer);
				validInput = true;
				break;
			case "6":
				showWithdrawMenu(customer);
				validInput = true;
				break;
			case "7":
				showTransferMenu(customer);
				validInput = true;
				break;
			case "8":
				showMainMenu();
			default:
				System.out.println("Please enter a valid option (1 | 2 | 3 | 4 | 5 | 6 | 7 | 8)");
			}
		} while (!validInput);
	}

	private static void applyForAccount() {
		// TODO Implement this
		
	}

	private static void showCustomerAccountInfo(Customer customer) {
		// TODO implement this
		// System.out.println("Your Account Infomation Is As Followed: ");

	}

	private static void showCustomerAccountBalance(Customer customer) {
		// TODO implement this

	}

	private static void showCustomerPersonalInfo(Customer customer) {
		// TODO add to log
		System.out.println("Your info is as followed:");
		System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
	}

	private static void showDepositMenu(Customer customer) {
		// TODO Auto-generated method stub

	}

	private static void showWithdrawMenu(Customer customer) {
		// TODO Auto-generated method stub

	}

	private static void showTransferMenu(Customer customer) {
		// TODO Auto-generated method stub

	}
}
