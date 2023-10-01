package servlets.loginSignup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("-->>> Servlet: Logout servlet started running...");

		try {
			
			Cookie[] cookies = req.getCookies();
			if (cookies == null) {

				System.out.println("<<<-- Servlet: Logout servlet redirected...");
				resp.sendRedirect("profile.jsp");
				return;

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

				System.out.println("<<<-- Servlet: Logout servlet redirected...");
				resp.sendRedirect("profile.jsp");
				return;

			}

			System.out.println("Cookie results: loginToken: " + loginToken + " userid: " + userid);

			// connecting to database
			
			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println("DB connection closed logout");
			} else {
				System.out.println("DB connection created logout");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			stmt.executeUpdate("delete from login_token where tokenid = '" + loginToken + "'");

			Cookie cookie = new Cookie("loginToken", "");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			Cookie cookie1 = new Cookie("userid", "");
			cookie1.setMaxAge(0);
			resp.addCookie(cookie1);

			System.out.println("logged out successfully");
			System.out.println("<<<-- Servlet: Logout servlet redirected...");
			resp.sendRedirect("login.jsp");
			return;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("<<<-- Servlet: Logout servlet redirected...");
			resp.sendRedirect("profile.jsp");
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
