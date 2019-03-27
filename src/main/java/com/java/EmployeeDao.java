package com.java;

import java.util.Set;

public interface EmployeeDao {
    Employee getEmployeeByLoginAndPassword(String login, String password);
    Set<Account> getPendingAccounts();
    boolean updateAccountStatus(Account account);

}
