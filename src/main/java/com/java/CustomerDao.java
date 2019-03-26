package com.java;

import java.util.Set;

public interface CustomerDao {
    Customer getCustomerByLoginAndPassword(String username, String password);
    Customer getCustomerByID(int id);
    Set getAllCustomers();
    boolean insertCustomer(Customer customer);
}
