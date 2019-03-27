package com.java;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
		temp = customerConnection.getCustomerByLoginAndPassword(usernameInput, passwordInput);
		temp = temp.register(firstNameInput, lastNameInput, usernameInput, passwordInput, emailInput);
		if (customerConnection.insertCustomer(temp)) {
			System.out.println(
					"Your account has been registered.\nYou can log into your account by using you username and password.");
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
				applyForAccount(customer);
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

	private static void applyForAccount(Customer customer) {
		// TODO Implement this
		int[] routingNumber = new int[3];
		routingNumber[0] = 1001000;
		routingNumber[1] = 1002007;
		routingNumber[2] = 1005000;
		int accountRoutingNumber = (int) (Math.random() * 3);
		System.out.println("Please Answer The Following Questions");
		System.out.println("Would you like to create a checking or saving account? (Checking/Saving)");
		String account_type = getInput();
		System.out.println("Would you like this to be a joint account? (Y/N)");
		String joint = null;
		boolean validInput = false;
		int joint_customer_id = 0;
		do {
			joint = getInput();
			switch (joint) {
			case "Y":
				System.out.println("Please Enter Your Joint Partner User ID: ");
				do {
					try {
						joint_customer_id = Integer.parseInt(getInput());
						validInput = true;
					} catch (NumberFormatException nunFEx) {
						System.out.println("Incorrect Input");
					}
				} while (!validInput);

				break;
			case "N":
				validInput = true;
				break;
			default:
				System.out.println("Please enter a valid input (Y/N)");
			}
		} while (!validInput);
		// TODO implement this part.
		AccountDaoImpl accountConn = new AccountDaoImpl();
		accountConn.insertBankAccount(customer, routingNumber[accountRoutingNumber], account_type.charAt(0),
				joint.charAt(0), joint_customer_id);
		System.out.println("All done!");
		showCustomerActionMenu(customer);
	}

	private static void showCustomerAccountInfo(Customer customer) {
		// TODO implement this
		System.out.println("Your Account Infomation Is As Followed: ");
		Set<Account> customerAccounts = getAllAccountForCustomer(customer);
		Iterator<Account> accountIterator = customerAccounts.iterator();
		while (accountIterator.hasNext()) {
			Account account = accountIterator.next();
			System.out.println("|Account Number       |" + account.getAccountNumber());
			System.out.println("|Routing Number       |" + account.getRoutingNumber());
			System.out.println("|Account Type         |" + account.getAccountType());
			System.out.println("|Account Limit        |" + account.getAccountLimit());
			if (account instanceof SavingAccount)
				System.out.println("|Annual Interest Rate |" + ((SavingAccount) account).getAnnualInterestRate());
			System.out.println("|Account Status       |" + account.getAccountStatus());
			System.out.println("----------------------");
		}
		showCustomerActionMenu(customer);
	}

	private static void showCustomerAccountBalance(Customer customer) {
		// TODO implement this
		System.out.println("Your Account Balances Are As Followed: ");
		Set<Account> customerAccounts = getAllAccountForCustomer(customer);
		Iterator<Account> accountIterator = customerAccounts.iterator();
		while (accountIterator.hasNext()) {
			Account account = accountIterator.next();
			System.out.println("|Account Number       |" + account.getAccountNumber());
			System.out.println("|Account Balances     |" + account.getBalances());
			System.out.println("----------------------");
		}
		showCustomerActionMenu(customer);
	}

	private static void showCustomerPersonalInfo(Customer customer) {
		System.out.println("Your info is as followed:");
		System.out.println("|Name      |  " + customer.getFirstName() + " " + customer.getLastName() + "  |");
		System.out.println("|Email     |  " + customer.getUserEmail() + "  |");
		System.out.println("|Username  |  " + customer.getlogin() + "     |");
		System.out.println("|User ID   |  " + customer.getCustomerID() + "     |");
		System.out.println();
		showCustomerActionMenu(customer);
	}

	private static void showDepositMenu(Customer customer) {
		// TODO implements this
		System.out.println("Here is a list of your account: ");
		showAllAccountForCustomer(customer);
		System.out.println("<----------------------------------------------------->");
		System.out.println(
				"Reminder: If you want to deposit into an account that is not yours,\n\tPlease provide the receiving account number!");
		int accNumberDeposit = 0;
		AccountDaoImpl accountConnection = new AccountDaoImpl();
		boolean validInput = false;
		do {
			try {
				System.out.println("Please enter an account number to deposit: ");
				accNumberDeposit = Integer.parseInt(getInput());
				validInput = true;
			} catch (NumberFormatException numFEx) {
				System.out.println("Invalid account number");
			}
		} while (!validInput);
		Account temp = accountConnection.getAccountByAccountNumber(accNumberDeposit);

		if (temp == null) {
			System.out.println("Could Not Get Account Information.");
			showCustomerActionMenu(customer);
			return;
		}
		if (temp.getAccountStatus() == 'P')
			System.out.println("Your Account Is Not Approved Yet");
		else if (temp.getAccountStatus() == 'D')
			System.out.println("You Account Was Denied");
		else if (temp.getAccountStatus() == 'C')
			System.out.println("Your Account Was Closed");
		else {
			validInput = false;
			double amountToDeposit = 0;
			do {
				try {
					System.out.println("Please enter the amount you want to deposit: ");
					amountToDeposit = Double.parseDouble(getInput());
					validInput = true;
				} catch (NumberFormatException numFEx) {
					System.out.println("Invalid input. Please only put in numbers");
				}
			} while (!validInput);

			temp.deposit(amountToDeposit);

			if (accountConnection.updateAccountBalance(temp)) {
				System.out.println("All Done!");
			}
		}
		showCustomerActionMenu(customer);
	}

	private static void showWithdrawMenu(Customer customer) {
		// TODO implement this
		System.out.println("Here is a list of your account: ");
		showAllAccountForCustomer(customer);
		System.out.println("<----------------------------------------------------->");
		System.out.println("Reminder: You can only withdraw from your own account!");
		System.out.println("Please enter an accoun number to withdraw: ");
		String accNumWithdraw = getInput();
		System.out.println("Please enter the amount you want to withdraw: ");
		String amountToWithdraw = getInput();
		System.out.println("Please wait a minute!");
		AccountDaoImpl accountConnection = new AccountDaoImpl();
		boolean validInput = false;
		int accNumberWithdraw = 0;
		do {
			try {
				accNumberWithdraw = Integer.parseInt(accNumWithdraw);
			} catch (NumberFormatException numFEx) {
				System.out.println("Invalid account number");
			}
		} while (!validInput);

		Account temp = accountConnection.getAccountByAccountNumber(accNumberWithdraw);
		if (temp == null) {
			System.out.println("Could Not Get Account Information.");
			showCustomerActionMenu(customer);
			return;
		}
		if (temp.getAccountStatus() == 'P')
			System.out.println("Your Account Is Not Approved Yet");
		else if (temp.getAccountStatus() == 'D')
			System.out.println("You Account Was Denied");
		else if (temp.getAccountStatus() == 'C')
			System.out.println("Your Account Was Closed");
		else {
			validInput = false;
			double amountToDeposit = 0;
			do {
				try {
					System.out.println("Please enter the amount you want to deposit: ");
					amountToDeposit = Double.parseDouble(getInput());
					validInput = true;
				} catch (NumberFormatException numFEx) {
					System.out.println("Invalid input. Please only put in numbers");
				}
			} while (!validInput);

			temp.deposit(amountToDeposit);

			if (accountConnection.updateAccountBalance(temp)) {
				System.out.println("All Done!");
			}
		}
		showCustomerActionMenu(customer);
		showCustomerActionMenu(customer);
	}

	private static void showTransferMenu(Customer customer) {
		System.out.println("Here Is A List Of Your Account: ");
		showAllAccountForCustomer(customer);
		System.out.println("<----------------------------------------------------->");
		System.out.println("Reminder: You can only transfer between your own account!");
		System.out.println("Please Enter An Account Number To Transfer From: ");
		String accNumTransferFrom = getInput();
		System.out.println("Please Enter An AccountNumber To Transfer To: ");
		String accNumTransferTo = getInput();
		System.out.println("Please Enter The Ammount To Transfer: ");
		String amountToTransfer = getInput();
		System.out.println("Please wait a minute!");
		System.out.println("All Done!");
		showCustomerActionMenu(customer);
	}

	private static Set<Account> getAllAccountForCustomer(Customer customer) {
		// TODO implement this
		AccountDaoImpl accountConnection = new AccountDaoImpl();
		Set<Account> test = accountConnection.getAccountsByCustomerID(customer.getCustomerID());
		return test;
	}

	private static void showAllAccountForCustomer(Customer customer) {
		Set<Account> test = getAllAccountForCustomer(customer);
		int x = 1;
		Iterator<Account> customerAccounts = test.iterator();
		while (customerAccounts.hasNext()) {
			Account temp = customerAccounts.next();
			if (temp instanceof CheckingAccount)
				System.out.println("Checking account " + x + " Account Number is " + temp.getAccountNumber()
						+ " Account balances is " + temp.getBalances());
			else
				System.out.println("Saving Account " + x + " Account Number is " + temp.getAccountNumber()
						+ " Account balances is " + temp.getBalances());
			x++;
		}
	}
}
