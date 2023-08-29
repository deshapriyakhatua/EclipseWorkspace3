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
