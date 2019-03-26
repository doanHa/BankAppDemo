package com.java;

import java.util.Set;

public interface AccountDao {
   Account getAccountByAccountNumber(int accountNumber);
   Account getAccountByCustomerID(int cust_id);
   Set getAllBankAccounts();
   void updateAccount(Account account);
   void insertBankAccount(Customer customer, int routingNumber, char accountType, char joint);



}
