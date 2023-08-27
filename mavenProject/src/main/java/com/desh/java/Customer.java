package com.desh.java;

public class Customer {

	String id;
	String name;
	Long phone;
	String email;
	
	public Customer(String id, String name, Long phone, String email) {

		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	

	public Customer() {
		
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
