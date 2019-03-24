package com.java;

import java.sql.Connection;

public class CustomerDaoImpl implements CustomerDao {




    @Override
    public Customer getCustomer(String username, String password) {
        Connection con = DBUtil.getInstance();
        return null;
    }

    @Override
    public void createCustomer(String lastname, String firstname, String email, String login, String password) {

    }
}
