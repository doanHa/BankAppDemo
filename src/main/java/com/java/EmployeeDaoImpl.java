package com.java;

import java.sql.Connection;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection con = DBUtil.getInstance();
    @Override
    public Employee getEmployeeByLoginAndPassword(String Login, String password) {

        return null;
    }

    @Override
    public Set<Account> getPendingAccounts() {
        return null;
    }

    @Override
    public boolean updateAccountStatus(Account account) {
        return false;
    }
}
