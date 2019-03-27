package com.java;



public class Bank {
	// HACK: Need to make sure we handles the NullPointerExceptions from passing
	// values for records that do not exist
	// (i.e user passing in wrong log in info, same with employer)
	public static void main(String[] args) {
		AccountDaoImpl act = new AccountDaoImpl();
		Customer custom = new Customer();
		custom.setCustomerID(2005);
		act.insertBankAccount(custom, 11248, 'S', 'Y',1);
		//UserUI.showMainMenu();
	}
	/*
	 * static Logger logger= Logger.getLogger(Demo1.class);
	 * logger.info()/debug()/error()/fatal();
	 */
}
