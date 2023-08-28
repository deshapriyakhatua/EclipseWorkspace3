package com.desh.java;

public class Customer {

	String id;
	String phone;
	String password;
	
	public Customer(String id, String phone, String password) {

		this.id = id;
		this.phone = phone;
		this.password = password;
	}
	

	public Customer() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getPassword() {
		return phone;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	
	
}
