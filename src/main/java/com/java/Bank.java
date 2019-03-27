package com.java;



public class Bank {
	// HACK: Need to make sure we handles the NullPointerExceptions from passing
	// values for records that do not exist
	// (i.e user passing in wrong log in info, same with employer)
	public static void main(String[] args) {
		//UserUI.showMainMenu();
		AccountDaoImpl a = new AccountDaoImpl();
		CheckingAccount account = new CheckingAccount();
		account.setAccountNumber(10000024);
		account.deposit(200.00);
		boolean i = a.updateAccountBalance(account);
		System.out.println(i);
	}
	/*
	 * static Logger logger= Logger.getLogger(Demo1.class);
	 * logger.info()/debug()/error()/fatal();
	 */
}
