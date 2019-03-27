package com.java;

import java.util.Iterator;
import java.util.Set;

public class EmployeeUI extends UserUI {
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
		System.out.println("8. Log Out");
		takeEmployeeActionInput(temp);
	}

	private static void takeEmployeeActionInput(Employee employee) {
		String actionInput = null;
		boolean validInput = false;
		// TODO add to log each user choice here.
		do {
			actionInput = getInput();
			switch (actionInput) {
			case "1":
				showApproveDenyMenu(employee);
				break;
			case "2":
				showCustomersAccountsInfo();
				break;
			case "3":
				break;
			case "4":
				break;
			case "8":
				showMainMenu();
			default:
				System.out.println("Please enter a valid option (1 | 2 | 3 | 4 | 5 | 6 | 7 | 8)");
			}
		} while (!validInput);
	}

	private static void showCustomersAccountsInfo() {
		AccountDaoImpl accountConn = new AccountDaoImpl();
		Set<Account> accounts = accountConn.getAllBankAccounts();
		Iterator<Account> accountsIterator = accounts.iterator();
		while(accountsIterator.hasNext()) {
			Account account = accountsIterator.next();
			System.out.println("|Account Number       |" + account.getAccountNumber());
			System.out.println("|Routing Number       |" + account.getRoutingNumber());
			System.out.println("|Account Type         |" + account.getAccountType());
			System.out.println("|Account Limit        |" + account.getAccountLimit());
			if (account instanceof SavingAccount)
				System.out.println("|Annual Interest Rate |" + ((SavingAccount) account).getAnnualInterestRate());
			System.out.println("|Account Status       |" + account.getAccountStatus());
			System.out.println("----------------------");
		}
	}

	private static void showApproveDenyMenu(Employee emp) {
		// TODO Auto-generated method stub
		AccountDaoImpl accountConnection = new AccountDaoImpl();
		Set<Account> pendingAccount = accountConnection.getPendingAccounts();
		System.out.println("Here Is The List of Pending Account");
		Iterator<Account> pAccIterator = pendingAccount.iterator();
		while (pAccIterator.hasNext()) {
			Account temp = pAccIterator.next();
			System.out.println("| Account Number    |" + temp.getAccountNumber());
			System.out.println("| Routing Number    |" + temp.getRoutingNumber());
			System.out.println("| Account Type      |" + temp.getAccountType());
		}
		changeAccountStatus(emp, pendingAccount);
	}

	private static void changeAccountStatus(Employee emp, Set<Account> pendingAccounts) {
		System.out.println("Please Enter The Account Number of The Account you want to work with");
		System.out.println("Enter \"stop\" to exit to the work menu");
		String input = getInput();
		if (input.equalsIgnoreCase("stop")) {
			showEmployeeActionMenu(emp);
		} else {
			boolean validInput = false;
			int accountIDInput = 0;
			do {
				try {
					accountIDInput = Integer.parseInt(input);

				} catch (NumberFormatException numFEx) {
					System.out.println("Invalid Input");
					continue;
				}
				AccountDaoImpl accountConn = new AccountDaoImpl();
				Account temp = accountConn.getAccountByAccountNumber(accountIDInput);
				if (temp == null) {
					System.out.println("Could not find this Account in database");
					showEmployeeActionMenu(emp);
					return;
				}
				System.out.println("What is your decision? (Approve/Deny)");
				String decision = getInput();
				if (decision.equalsIgnoreCase("Approve") || decision.equalsIgnoreCase("Deny")) {
					temp.setAccountStatus(decision.toUpperCase().charAt(0));
					accountConn.updateAccountStatus(temp);
					if(temp instanceof CheckingAccount)
						temp.setAccountLimit(1000000000.0);
					else {
						temp.setAccountLimit(50000000000.0);
						((SavingAccount)temp).setAnnualInterestRate(3);
						accountConn.updateAccountAnnualInterestRate((SavingAccount)temp);
					}
					accountConn.updateAccountLimit(temp);
					validInput = true;
					System.out.println("All done!");
				} else
					System.out.println("Please enter a valid decision (Approve/Deny)");
			} while (!validInput);
		}
		showEmployeeActionMenu(emp);
	}

}
