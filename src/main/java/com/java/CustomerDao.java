package com.java;

public interface CustomerDao {
    public Customer getCustomer(String username, String password);
    public void createCustomer(String lastname, String firstname, String email, String login, String password);
}
