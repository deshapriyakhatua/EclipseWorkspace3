package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.google.gson.Gson;

import jakarta.servlet.http.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginTokenValidationResponse;

public class LoginTokenValidation extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		String loginToken = null;
		LoginTokenValidationResponse tokenValidationResponse = new LoginTokenValidationResponse("", "");

		for (Cookie c : cookies) {

			if (c.getName().equals("loginToken")) {
				loginToken = c.getValue();
				break;
			}

		}

		if (loginToken == null) {
			tokenValidationResponse = new LoginTokenValidationResponse("", "");
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
					
					Timestamp timeStamp = set.getTimestamp(2);
					Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
					long tokenAge = ( currTimestamp.getTime() - timeStamp.getTime() ) / ( 1000*60 ); //in minute
					
					if(tokenAge < (5 * 1)) {
						tokenValidationResponse = new LoginTokenValidationResponse("","");
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
		tokenValidationResponse = new LoginTokenValidationResponse("","");
		
		String tokenValidationResponseStatus = new Gson().toJson(tokenValidationResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(tokenValidationResponseStatus);

		out.close();
		
		
	}

	

}
