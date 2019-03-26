package com.java;

import java.util.Set;

public interface CustomerDao {
    boolean getCustomerByLogin(String login);
    Customer getCustomerByPassword(String password);
    Customer getCustomerByLoginAndPassword(String login, String password);
    Customer getCustomerByID(int id);
    Set getAllCustomers();
    boolean insertCustomer(Customer customer);
}
