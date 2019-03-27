package com.java;

import org.apache.log4j.Logger;

import java.util.Date;


public class Bank {

	static Logger logger = Logger.getLogger(Bank.class.getName());

	public static void main(String[] args) {
		Date date = new Date();

		logger.info("Bank Accessed on " + date);
		UserUI.showMainMenu();

	}
}
