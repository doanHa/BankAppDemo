package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoImpl implements AccountDao {
    Connection con = DBUtil.getInstance();


    @Override
    public Account getAccountByAccountNumber(int accountNumber) {

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACCOUNT WHERE ACNT_NUMBER =" + accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAccountFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/* ignored*/}
        }
        return null;
    }


    @Override
    public Account getAccountByCustomerID(int cust_id) {
        return null;
    }

    @Override
    public Set getAllBankAccounts() {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACCOUNT");
            ResultSet rs = stmt.executeQuery();
            Set<Account> accounts = new HashSet<>();

            while (rs.next()) {
                Account account = extractAccountFromResultSet(rs);
                accounts.add(account);
            }

            return accounts;
        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }
        return null;
    }

    @Override
    public void updateAccount(Account account) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET BALANCE=?, ACNT_STATUS=?, ACNT_LIMIT=? " +
                    "WHERE ACNT_NUMBER=?");
            stmt.setDouble(1, account.getBalances());
            stmt.setString(2, String.valueOf(account.getAccountStatus()));
            stmt.setDouble(3, account.getAccountLimit());
            stmt.setInt(4, account.getAccountNumber());

        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }


    }

    @Override
    public void insertBankAccount(Customer customer, int routingNumber, char accountType, char joint) {
        //HACK: on insert to Account, need to also do an additional insert to add CUST_ID & ACNT_NUMBER to
        // CUSTOMERACCOUNT
        try {
            PreparedStatement stmtAccount = con.prepareStatement("INSERT INTO ACCOUNT (ROUT_NUMBER, ACNT_TYPE, JOINT)" +
                    " VALUES" +
                    " (?, ?, ?");
            PreparedStatement stmtCustomerAccount = con.prepareStatement("INSERT INTO CUSTOMERACCOUNT (CUST_ID, " +
                    "ACNT_NUMBER) VALUES (?, ?)");
        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            {
                if(con != null) try {
                    con.close();
                } catch (SQLException e) {/*ignored*/}
            }
        }

    }

    public Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        //HACK: Implement Factory method pattern for Checking and Saving accounts
        char accountType = rs.getString(4).charAt(0);
        if (accountType == 'c') {
            CheckingAccount account = new CheckingAccount();
            account.setAccountNumber(rs.getInt(1));
            account.setRoutingNumber(rs.getInt(2));
            account.setBalances(rs.getDouble(3));
            account.setAccountType(accountType);

            return account;
        } else {
            SavingAccount account = new SavingAccount();
            account.setAccountNumber(rs.getInt(1));
            account.setRoutingNumber(rs.getInt(2));
            account.setBalances(rs.getDouble(3));
            account.setAccountType(accountType);
            account.setAnnualInterestRate(rs.getInt(8));

            return account;
        }


    }
}
