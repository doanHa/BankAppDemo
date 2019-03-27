package com.java;

import java.util.List;
import java.util.Set;

public interface AccountDao {
   Account getAccountByAccountNumber(int accountNumber);
   Set getAccountsByCustomerID(int cust_id);
   Set getAllBankAccounts();
   Set getPendingAccounts();
   boolean updateAccountStatus(Account account);
   boolean updateAccountBalance(Account account);
   boolean updateAccountLimit(Account account);
   boolean updateAccountAnnualInterestRate(SavingAccount account);
   boolean insertBankAccount(Customer customer, int routingNumber, char accountType, char joint, int cust_id);



}
