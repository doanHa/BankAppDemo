package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection con = DBUtil.getInstance();
    @Override
    public Employee getEmployeeByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Employee WHERE login=? AND password_text=?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return extractEmployeeFromResultSet(rs);

            }

        } catch (SQLException e) {
            System.out.println("Unable to connect please try again later.");
        } finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    /* ignored */}
        }

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

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {

        Employee employee = new Employee();
        employee.setEmployeeID(rs.getInt(1));
        employee.setLastName(rs.getString(2));
        employee.setFirstName(rs.getString(3));
        employee.setUserEmail(rs.getString(4));
        employee.setlogin(rs.getString(5));
        employee.setPassword(rs.getString(6));

        return employee;

    }
}
