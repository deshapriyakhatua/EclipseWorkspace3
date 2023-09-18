package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginTokenValidator implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("-->>> Filter: Login token validator started running...");
		

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		System.out.println("URI:   " + req.getRequestURI());
		
		// validate access token from cookie

		Cookie[] cookies = req.getCookies();
		if (cookies == null) {

			// go for login
			System.out.println("No cookies available in your device, Login now");
			req.setAttribute("userid", null);
			
			if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				chain.doFilter(req, resp);
				return;
			}
			else {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				resp.sendRedirect("login.jsp");
				return;
			}
			

		}

		String loginToken = null;
		String userid = null;

		for (Cookie c : cookies) {

			if (c.getName().equals("loginToken")) {
				loginToken = c.getValue();
			} else if (c.getName().equals("userid")) {
				userid = c.getValue();
			}

		}

		if (loginToken == null || userid == null) {

			// go for login
			System.out.println("Token not available in your device, Login now");
			req.setAttribute("userid", null);

			if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				chain.doFilter(req, resp);
				return;
			}
			else {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				resp.sendRedirect("login.jsp");
				return;
			}

		}

		System.out.println("Cookie results: loginToken: " + loginToken + " userid: " + userid);

		try {

			// connecting to database
			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println("DB connection closed isValidLoginToken");
			} else {
				System.out.println("DB connection created isValidLoginToken");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(
					"select * from login_token where tokenid = '" + loginToken + "' and useruid = '" + userid + "'");

			if (set.next()) {

				System.out.println("Database Result: login token: " + set.getString(1) + " | userid: "
						+ set.getString(2) + " | time: " + set.getString(3));

				Timestamp timeStamp = set.getTimestamp(3);
				Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
				long tokenAge = (currTimestamp.getTime() - timeStamp.getTime()) / (1000 * 60); // in minute

				if (tokenAge < (60 * 24)) {

					
					System.out.println("user cookie is valid");
					req.setAttribute("userid", userid);
					con.close();
					
					if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
						System.out.println("<<<-- Filter: Login token validator redirected...");
						resp.sendRedirect("index.jsp");
						return;
					}
					else {
						System.out.println("<<<-- Filter: Login token validator redirected...");
						chain.doFilter(req, resp);
						return;
					}
					
					

				} else {

					req.setAttribute("userid", null);
					System.out.println("user cookie is expired, Login");
					con.close();

					if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
						System.out.println("<<<-- Filter: Login token validator redirected...");
						chain.doFilter(req, resp);
						return;
					}
					else {
						System.out.println("<<<-- Filter: Login token validator redirected...");
						resp.sendRedirect("login.jsp");
						return;
					}

				}

			}

			con.close();

			System.out.println("Token not available in database, Login now");
			req.setAttribute("userid", null);

			if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				chain.doFilter(req, resp);
				return;
			}
			else {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				resp.sendRedirect("login.jsp");
				return;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e);
			req.setAttribute("userid", null);

			if (req.getRequestURI().endsWith("/login.jsp") || req.getRequestURI().endsWith("/signup.jsp") || req.getRequestURI().endsWith("/login") ||  req.getRequestURI().endsWith("/signup")) {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				chain.doFilter(req, resp);
				return;
			}
			else {
				System.out.println("<<<-- Filter: Login token validator redirected...");
				resp.sendRedirect("login.jsp");
				return;
			}

		}

	}

}
