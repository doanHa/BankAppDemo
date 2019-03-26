package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SuppressWarnings("Duplicates")
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
    public List<Account> getAccountsByCustomerID(int cust_id) {
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT A1.* FROM ACCOUNT A1 JOIN CUSTOMERACCOUNT C2 on A1" +
                    ".ACNT_NUMBER = C2" +
                    ".ACNT_NUMBER WHERE C2.CUST_ID =" + cust_id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Account account = extractAccountFromResultSet(rs);
                accounts.add(account);
            }

        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }
        return accounts;
    }

    @Override
    public Set getAllBankAccounts() {
        Set<Account> accounts = new HashSet<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACCOUNT");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = extractAccountFromResultSet(rs);
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }
        return accounts;
    }

    @Override
    public boolean updateAccountStatus(Account account) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET ACNT_STATUS=? WHERE ACNT_NUMBER=?");
            stmt.setString(1, String.valueOf(account.getAccountStatus()));
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return false;
    }


    @Override
    public boolean updateAccountBalance(Account account) {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET BALANCE WHERE ACNT_NUMBER=?");
            stmt.setDouble(1, account.getBalances());
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return false;
    }

    @Override
    public boolean updateAccountLimit(Account account) {
        return false;
    }

    @Override
    public boolean updateAccountAnnualInterestRate(Account account) {
        return false;
    }

    @Override
    public boolean insertBankAccount(Customer customer, int routingNumber, char accountType, char joint) {
        //HACK: on insert to Account, need to also do an additional insert to add CUST_ID & ACNT_NUMBER to
        // CUSTOMERACCOUNT
        try {
            PreparedStatement stmtAccount = con.prepareStatement("INSERT INTO ACCOUNT (ROUT_NUMBER, ACNT_TYPE, JOINT)" +
                    " VALUES" +
                    " (?, ?, ?");

            stmtAccount.setInt(1, routingNumber);
            stmtAccount.setString(2, String.valueOf(accountType));
            stmtAccount.setString(3, String.valueOf(joint));


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
        return false;
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
