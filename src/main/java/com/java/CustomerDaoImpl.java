package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerDaoImpl implements CustomerDao {
	private Connection con;
	@Override
	public boolean getCustomerByLogin(String login) {
		boolean validLogin = false;
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer WHERE login=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				validLogin = true;

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
		return validLogin;
	}

	@Override
	public Customer getCustomerByPassword(String password) {
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer WHERE password_text=?");
			stmt.setString(1, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return extractCustomerFromResultSet(rs);

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
	public Customer getCustomerByLoginAndPassword(String login, String password) {
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer WHERE login=? AND password_text=?");
			stmt.setString(1, login);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return extractCustomerFromResultSet(rs);

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
	public Customer getCustomerByID(int id) {
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer WHERE cust_id=" + id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return extractCustomerFromResultSet(rs);

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
	public Set<Customer> getAllCustomers() {
		Set<Customer> customers = new HashSet<>();
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Customer");
			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {
				Customer customer = extractCustomerFromResultSet(rs);
				customers.add(customer);

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

		return customers;

	}

	@Override
	public boolean insertCustomer(Customer customer) {
		boolean entryAdded = false;
		con = DBUtil.getInstance();
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Customer (last_name, first_name, "
					+ "email, login, password_text) values (?, ?, ?, ?, ?)");

			stmt.setString(1, customer.getLastName());
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getUserEmail());
			stmt.setString(4, customer.getlogin());
			stmt.setString(5, customer.getPassword());

			int rowsAdded = stmt.executeUpdate();
			con.commit();

			if (rowsAdded == 1) {
				entryAdded = true;
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

		return entryAdded;
	}

	private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {

		Customer customer = new Customer();
		customer.setCustomerID(rs.getInt(1));
		customer.setLastName(rs.getString(2));
		customer.setFirstName(rs.getString(3));
		customer.setUserEmail(rs.getString(4));
		customer.setlogin(rs.getString(5));
		customer.setPassword(rs.getString(6));

		return customer;

	}
}
