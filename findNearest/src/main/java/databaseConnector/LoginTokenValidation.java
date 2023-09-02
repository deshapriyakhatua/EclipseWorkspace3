package databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Cookie;

import objects.LoginTokenValidationResponse;

public class LoginTokenValidation {

	public Object isValidLoginToken(Cookie[] cookies) {

		String loginToken = null;
		LoginTokenValidationResponse tokenValidationResponse;

		for (Cookie c : cookies) {

			if (c.getName().equals("loginToken")) {
				loginToken = c.getValue();
				break;
			}

		}

		if (loginToken == null) {
			tokenValidationResponse = new LoginTokenValidationResponse();
			return tokenValidationResponse;
		}

		try {
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed isValidLoginToken");
			} else {
				System.out.println(" DB connection created isValidLoginToken");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from login_token");

			while (set.next()) {

				if(set.getString(1).equals(loginToken)) {
					String time = set.getString(2);
					if(true) {
						tokenValidationResponse = new LoginTokenValidationResponse();
						return tokenValidationResponse;
					}
				}
				System.out.println("user id: " + set.getString(1) + " | name: " + set.getString(2) + " | address: "
						+ set.getString(3));
			}

			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		
		return tokenValidationResponse;

	}

}
