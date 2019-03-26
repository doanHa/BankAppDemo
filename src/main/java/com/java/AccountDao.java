package com.java;

import java.util.List;
import java.util.Set;

public interface AccountDao {
   Account getAccountByAccountNumber(int accountNumber);
   List<Account> getAccountsByCustomerID(int cust_id);
   Set getAllBankAccounts();
   boolean updateAccountStatus(Account account);
   boolean updateAccountBalance(Account account);
   boolean updateAccountLimit(Account account);
   boolean updateAccountAnnualInterestRate(Account account);
   boolean insertBankAccount(Customer customer, int routingNumber, char accountType, char joint);



}
