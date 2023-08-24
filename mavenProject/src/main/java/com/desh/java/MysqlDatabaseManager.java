package com.desh.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MysqlDatabaseManager {

	public boolean registerCustomerDetails(String id, String name, Long phone, String email) throws Exception {

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_backend";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed registerCustomerDetails");
				con.close();
				return false;
			} else {
				System.out.println(" DB connection created registerCustomerDetails");
			}

			// adding new row to the table
			PreparedStatement ptst = con.prepareStatement("insert into customers (id, name, phone, email) values (?, ?, ?, ?)");
			
			ptst.setString(1, id);
			ptst.setString(2, name);
			ptst.setLong(3, phone);
			ptst.setString(4, email);
			ptst.executeUpdate();
			
			con.close();

			return true;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

	public ArrayList<Employee> showCustomers() throws Exception {
		
		ArrayList<Employee> list = new ArrayList<>();

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_backend";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed showCustomers");
			} else {
				System.out.println(" DB connection created showCustomers");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from customers");
		
			while (set.next()) {
				
				list.add(new Employee(set.getString(1), set.getString(2), Long.parseLong(set.getString(3)), set.getString(4)));
				System.out.println("user id: " + set.getString(1) + " | name: " + set.getString(2) + " | phone: " + set.getString(3) + " | email: " + set.getString(4));
			}

			con.close();

			return list;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		}

	}
	
	public boolean registerCustomerPassword(String id, String password) throws Exception {

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_backend";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed registerCustomerPassword");
				con.close();
				return false;
			} else {
				System.out.println(" DB connection created registerCustomerPassword");
			}

			// adding new row to the table
			PreparedStatement ptst = con.prepareStatement("insert into user_passwords (id, password) values (?, ?)");
			
			ptst.setString(1, id);
			ptst.setString(2, password);
			
			ptst.executeUpdate();
			
			con.close();

			return true;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

	public ArrayList<UserPassword> showUserPasswords() throws Exception {
		
		ArrayList<UserPassword> list = new ArrayList<>();

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_backend";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed showUserPasswords");
			} else {
				System.out.println(" DB connection created showUserPasswords");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from user_passwords");
		
			while (set.next()) {
				
				list.add(new UserPassword(set.getString(1), set.getString(2)));
				System.out.println("user id: " + set.getString(1) + " | password: " + set.getString(2));
			}

			con.close();

			return list;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		}

	}
	
public ArrayList<Category> showCategories() throws Exception {
		
		ArrayList<Category> list = new ArrayList<>();

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_backend";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed showCategories");
			} else {
				System.out.println(" DB connection created showCategories");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from food_categories");
		
			while (set.next()) {
				
				list.add(new Category(set.getString(1), set.getString(2),set.getString(3)));
				System.out.println("category id: " + set.getString(1) + " | name: " + set.getString(2) + " | imageURL: " + set.getString(3));
			}

			con.close();

			return list;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;

		}

	}


}
