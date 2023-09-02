package databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.User;

public class DatabaseConnect {

	public ArrayList<User> showUsers() throws Exception {

		ArrayList<User> list = new ArrayList<>();

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed showUsers");
			} else {
				System.out.println(" DB connection created showUsers");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from users");

			while (set.next()) {

				list.add(new User(set.getString(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getString(6), set.getString(7), set.getString(8), set.getString(9) ));
				System.out.println("user id: " + set.getString(1) + " | name: " + set.getString(2) + " | address: " + set.getString(3));
			}

			con.close();

			return list;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return list;

		}

	}

	public boolean updateUserDetails(String name, String phone, String email, String gender, String latitude,
			String longitude, String profession, String address) {
		
		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed registerUser");
			} else {
				System.out.println(" DB connection created registerUser");
			}

			// Accessing Data from table
			PreparedStatement ptst = con.prepareStatement("insert into users(name, phone, email, gender, latitude, longitude, profession, address) values(?,?,?,?,?,?,?,?)");

			ptst.setString(1, name);
			ptst.setString(2, phone);
			ptst.setString(3, email);
			ptst.setString(4, gender);
			ptst.setString(5, latitude);
			ptst.setString(6, longitude);
			ptst.setString(7, profession);
			ptst.setString(8, address);
			ptst.executeUpdate();

			con.close();

			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		
	}

	public boolean logIn(String userId, String password) {
		
		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed logIn");
			} else {
				System.out.println(" DB connection created logIn");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select id from users");

			while (set.next()) {

				if(set.getString(1).equals(userId)) {
					return true;
				}
				System.out.println("user id: " + set.getString(1)+" "+ userId);
			}

			con.close();

			return false;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

	}

	public boolean signUp(String userUID, String password, String name, String phone, String email) {
		
		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed signUp");
			} else {
				System.out.println(" DB connection created signUp");
			}

			// Accessing Data from table
			PreparedStatement ptst = con.prepareStatement("insert into users(useruid, password, name, phone, email) values(?,?,?,?,?)");

			ptst.setString(1, userUID);
			ptst.setString(2, password);
			ptst.setString(3, name);
			ptst.setString(4, phone);
			ptst.setString(5, email);
			ptst.executeUpdate();

			con.close();

			return true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		
	}
	
}
