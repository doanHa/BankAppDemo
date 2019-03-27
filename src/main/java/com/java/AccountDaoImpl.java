package com.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
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
    public Set<Account> getAccountsByCustomerID(int cust_id) {
        Set<Account> accounts = new HashSet<>();
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
    public Set<Account> getAllBankAccounts() {
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
    public Set<Account> getPendingAccounts() {
        Set<Account> accounts = new HashSet<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ACCOUNT WHERE ACNT_STATUS = 'P'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = new AccountDaoImpl().extractAccountFromResultSet(rs);
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
        boolean entryAdded = false;
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET ACNT_STATUS=? WHERE ACNT_NUMBER=?");
            stmt.setString(1, String.valueOf(account.getAccountStatus()));
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                entryAdded = true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return entryAdded;
    }


    @Override
    public boolean updateAccountBalance(Account account) {
        boolean entryAdded = false;
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET BALANCE =? WHERE ACNT_NUMBER=?");
            stmt.setDouble(1, account.getBalances());
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                entryAdded = true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return entryAdded;
    }

    @Override
    public boolean updateAccountLimit(Account account) {
        boolean entryAdded = false;
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET ACNT_LIMIT=? WHERE ACNT_NUMBER=?");
            stmt.setDouble(1, account.getAccountLimit());
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                entryAdded = true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return entryAdded;
    }

    @Override
    public boolean updateAccountAnnualInterestRate(SavingAccount account) {
        boolean entryAdded = false;
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNT SET AN_INT_RATE=? WHERE ACNT_NUMBER=?");
            stmt.setInt(1, (int)account.getAnnualInterestRate());
            stmt.setInt(2, account.getAccountNumber());

            int rowsAdded = stmt.executeUpdate();
            con.commit();

            if(rowsAdded == 1) {
                entryAdded = true;
            }


        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {/*ignored*/}
        }

        return entryAdded;
    }

    @Override
    public boolean insertBankAccount(Customer customer, int routingNumber, char accountType, char joint,
                                     int jointCust_id) {
        boolean entryAdded = false;
        int row1Added = 0;
        int row2Added = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{CALL INSERT_ACCOUNT(?, ?, ?, ?)}");
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setDouble(2,routingNumber);
            cstmt.setString(3, String.valueOf(accountType));
            cstmt.setString(4, String.valueOf(joint));;
            cstmt.execute();
            int acnt_number = cstmt.getInt(1);

            if(joint == 'N') {
                row1Added = insertCustomerAccount(customer.getCustomerID(), acnt_number);
                if (row1Added == 1) {
                    entryAdded = true;
                }
            } else {
                row1Added = insertCustomerAccount(customer.getCustomerID(), acnt_number);
                row2Added = insertCustomerAccount2(jointCust_id, acnt_number);
                if ((row1Added + row2Added) == 2) {
                    entryAdded = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later1.");
        } finally {
            {
                if(con != null) try {
                    con.close();
                } catch (SQLException e) {/*ignored*/}
            }
        }
        return entryAdded;
    }

    protected Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        //HACK: Implement Factory method pattern for Checking and Saving accounts
        char accountType = rs.getString(4).charAt(0);
        if (accountType == 'c') {
            CheckingAccount account = new CheckingAccount();
            account.setAccountNumber(rs.getInt(1));
            account.setRoutingNumber(rs.getInt(2));
            account.setBalances(rs.getDouble(3));
            account.setAccountType(accountType);
            account.setAccountStatus(rs.getString(5).charAt(0));

            return account;
        } else {
            SavingAccount account = new SavingAccount();
            account.setAccountNumber(rs.getInt(1));
            account.setRoutingNumber(rs.getInt(2));
            account.setBalances(rs.getDouble(3));
            account.setAccountType(accountType);
            account.setAccountStatus(rs.getString(5).charAt(0));
            account.setAnnualInterestRate(rs.getInt(8));

            return account;
        }


    }

    private int insertCustomerAccount(int cust_id, int acnt_number) {
        int rowsAdded = 0;
        try {
            PreparedStatement stmtCustomerAccount = con.prepareStatement("INSERT INTO CUSTOMERACCOUNT (CUST_ID, " +
                    "ACNT_NUMBER) VALUES (?, ?)");

            stmtCustomerAccount.setInt(1, cust_id);
            stmtCustomerAccount.setInt(2, acnt_number);

            rowsAdded = stmtCustomerAccount.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later2.");
        } finally {
            {
                if(con != null) try {
                    con.close();
                } catch (SQLException e) {/*ignored*/}
            }
        }
        return rowsAdded;
    }
    private int insertCustomerAccount2(int cust_id, int acnt_number) {
        int rowsAdded = 0;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/database.properties"));
            Class.forName(properties.getProperty("driver"));
            Connection conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty(
                    "username"), properties.getProperty("password"));

            PreparedStatement stmtCustomerAccount = conn.prepareStatement("INSERT INTO CUSTOMERACCOUNT (CUST_ID, " +
                    "ACNT_NUMBER) VALUES (?, ?)");

            stmtCustomerAccount.setInt(1, cust_id);
            stmtCustomerAccount.setInt(2, acnt_number);

            rowsAdded = stmtCustomerAccount.executeUpdate();
            conn.commit();
            conn.close();

        } catch (SQLException e) {
            //System.out.println("Unable to connect please try again later3.");
        } catch (Exception e) {
            System.out.println("file MIA");
        }  finally {
            {
                if(con != null) try {
                    con.close();
                } catch (SQLException e) {/*ignored*/}
            }
        }
        return rowsAdded;
    }
}
