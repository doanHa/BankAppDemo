package com.java;
public class Employee extends User{
	int employeeID;
	String employeeType;
	void approveDenyAccount() {
		//TODO change this so employee can change account status from pending to either approved or denied.
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
}
