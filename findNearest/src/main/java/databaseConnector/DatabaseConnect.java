package databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objects.User;

public class DatabaseConnect {

	public static Connection getConnection() {
		
		Connection connection = null;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";
			connection = DriverManager.getConnection(url, "root", "1036");
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
		
	}
	
	public ArrayList<User> showUsers() throws Exception {

		System.out.println("ShowUsersMethod started...");
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

				User user = new User();
				user.setUserid(set.getString(1));
				user.setEmail(set.getString(2));
				user.setName(set.getString(4));
				user.setPhone(set.getString(5));
				user.setGender(set.getString(6));
				user.setLatitude(set.getString(7));
				user.setLongitude(set.getString(8));
				user.setProfession(set.getString(9));
				user.setAddress(set.getString(10));
				list.add(user);
				
				System.out.println("Database: user id: " + set.getString(1) + " | Email: " + set.getString(2) + " | name: "
						+ set.getString(4));
				
			}

			con.close();
			System.out.println("showUsers method finished work...");
			return list;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("showUsers method stopped...");
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("showUsers method stopped...");
			return list;

		}

	}

}
