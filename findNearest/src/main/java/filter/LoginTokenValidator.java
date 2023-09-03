package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginTokenValidationResponse;

public class LoginTokenValidator implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		LoginTokenValidationResponse tokenValidationResponse = new LoginTokenValidationResponse("", "");

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			
			// go for login
			tokenValidationResponse.setMessage("Token not available in your device, Login now");
			System.out.println(tokenValidationResponse.getMessage());
			req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
			RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
			rDispatcher.forward(req, resp);
			
		}

		String loginToken = null;

		for (Cookie c : cookies) {

			if (c.getName().equals("loginToken")) {
				loginToken = c.getValue();
				break;
			}

		}

		if (loginToken == null) {
			
			// go for login
			tokenValidationResponse.setMessage("Token not available in your device, Login now");
			System.out.println(tokenValidationResponse.getMessage());
			req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
			RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
			rDispatcher.forward(req, resp);
			
		} else {
			
			System.out.println(loginToken);
			try {
				// connecting to database
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/findnearest";

				Connection con = DriverManager.getConnection(url, "root", "1036");

				if (con.isClosed()) {
					System.out.println("DB connection closed isValidLoginToken");
				} else {
					System.out.println("DB connection created isValidLoginToken");
				}

				// Accessing Data from table
				Statement stmt = con.createStatement();
				ResultSet set = stmt.executeQuery("select * from login_token");

				while (set.next()) {

					System.out.println("login token: " + set.getString(1) + " | userid: " + set.getString(2)
							+ " | time: " + set.getString(3));

					if (set.getString(1).equals(loginToken)) {

						Timestamp timeStamp = set.getTimestamp(3);
						Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
						long tokenAge = (currTimestamp.getTime() - timeStamp.getTime()) / (1000 * 60); // in minute

						if (tokenAge < (5 * 1)) {
							
							tokenValidationResponse.setMessage("user cookie is correct");
							tokenValidationResponse.setUserid(set.getString(2));
							System.out.println(tokenValidationResponse.getMessage());
							req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
							chain.doFilter(req, resp);
							
						} else {
							
							tokenValidationResponse.setMessage("user cookie is expired, Login");
							req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
							System.out.println(tokenValidationResponse.getMessage());
							RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
							rDispatcher.forward(req, resp);
							
						}

						break;

					}

				}

				if (tokenValidationResponse.getMessage().length() == 0) {
					
					tokenValidationResponse.setMessage("Token not available in database, Login now");
					System.out.println(tokenValidationResponse.getMessage());
					req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
					RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
					rDispatcher.forward(req, resp);
					
				}
					

				con.close();

			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				tokenValidationResponse.setMessage(e + "");
				System.out.println(tokenValidationResponse.getMessage());
				req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
				RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
				rDispatcher.forward(req, resp);
				

			} catch (SQLException e) {
				
				e.printStackTrace();
				tokenValidationResponse.setMessage(e + "");
				System.out.println(tokenValidationResponse.getMessage());
				req.setAttribute("LoginTokenValidationResponse", tokenValidationResponse);
				RequestDispatcher rDispatcher = req.getRequestDispatcher("login.html");
				rDispatcher.forward(req, resp);
				
			}

		}

	}
	

}
