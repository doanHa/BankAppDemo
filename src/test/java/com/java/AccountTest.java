//package com.java;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public abstract class AccountTest {
//	Account testAccount;
//	@Test
//	public void canary() {
//		assert(true);
//	}
//	@Before
//	public abstract void init();
//	//test for deposit method
//	@Test
//	public void testDepositZeroAmount() {
//		testAccount.deposit(0.0);
//		assertEquals(0.0, testAccount.showBalances(),0.0);
//	}
//	@Test
//	public void testDepositPositiveAmount() {
//		testAccount.deposit(100.3);
//		assertEquals(100.3, testAccount.showBalances(),0.0);
//	}
//	@Test
//	public void testDepositNegativeAmount() {
//		testAccount.deposit(-10.0);
//		assertEquals(0.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testDepositExcessDepositLimit() {
//		testAccount.deposit(10000000001.0);
//	}
//	@Test
//	public void testDepositExcessAccountLimit() {
//		for(int i = 0; i<101;i++)
//		{
//			testAccount.deposit(10000000000.0);
//		}
//		assertEquals(1000000000000.0, testAccount.showBalances(), 0.0);
//	}
//	
//	//test for withdraw method	
//	@Test
//	public void testWithdrawZeroAmount() {
//		testAccount.deposit(10000000000.0);
//		testAccount.withdraw(0.0);
//		assertEquals(10000000000.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testWithdrawPositiveAmount() {
//		testAccount.deposit(10000000000.0);
//		testAccount.withdraw(100.0);
//		assertEquals(9999999900.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testWithdrawNegativeAmount() {
//		testAccount.deposit(10000000000.0);
//		testAccount.withdraw(-100.0);
//		assertEquals(10000000000.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testWithdrawExcessWithdrawLimit() {
//		testAccount.deposit(10000000000.0);
//		testAccount.withdraw(-100.0);
//		assertEquals(10000000000.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testWithdrawExcessBalances() {
//		testAccount.withdraw(200.0);
//		assertEquals(0.0, testAccount.showBalances(), 0.0);
//	}
//	@Test
//	public void testTransferWithinBalancesAndNotExcessTransferLimit() {
//		Account temp = new CheckingAccount(575, new Customer());
//		temp.setLimit();
//		testAccount.deposit(10000000.0);
//		testAccount.transfer(10000.0, temp);
//		if(testAccount.showBalances() == 9990000.0 && temp.showBalances() == 10000.0)
//			assert(true);
//		else 
//			assert(false);
//	}
//	@Test
//	public void testTransferWithinBalancesButExcessTransferLimit() {
//		Account temp = new CheckingAccount(575, new Customer());
//		temp.setLimit();
//		testAccount.deposit(1000000002.0);
//		testAccount.transfer(1000000001.0, temp);
//		if(testAccount.showBalances() == 1000000002.0 && temp.showBalances() == 0.0)
//			assert(true);
//		else 
//			assert(false);
//	}
//	@Test
//	public void testTransferNegativeAmount() {
//		Account temp = new CheckingAccount(575, new Customer());
//		temp.setLimit();
//		testAccount.deposit(1000000002.0);
//		testAccount.transfer(-100.0, temp);
//		if(testAccount.showBalances() == 1000000002.0 && temp.showBalances() == 0.0)
//			assert(true);
//		else 
//			assert(false);
//	}
//	@Test
//	public void testTransferExcessBalances() {
//		Account temp = new CheckingAccount(575, new Customer());
//		temp.setLimit();
//		testAccount.deposit(100000000.0);
//		testAccount.transfer(1000000000.0, temp);
//		if(testAccount.showBalances() == 100000000.0 && temp.showBalances() == 0.0)
//			assert(true);
//		else 
//			assert(false);
//	}
//}
