package com.java;

public interface AccountDao {
    public Account getAccount(int id);
    public void updateAccount(int id);
    public void createBankAccount(int routingNumber, char accountType, char joint);



}
