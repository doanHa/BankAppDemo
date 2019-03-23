//package com.java;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//public class CheckingAccountTest extends AccountTest {
//	@Override
//	public void init() {
//		testAccount = new CheckingAccount(556, new Customer());
//		testAccount.setLimit();
//	}
//	@Test
//	public void testShowAccountID() {
//		assertEquals(556, testAccount.showAccountID());
//	}
//	@Test
//	public void testShowAccountOwner() {
//		assertEquals(Customer.class, testAccount.showAccountOwners()[0].getClass());
//	}
//}
