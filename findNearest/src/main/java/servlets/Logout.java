package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginTokenValidationResponse;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Logout servlet started running...");

		try {
			
			Cookie[] cookies = req.getCookies();
			if (cookies == null) {

				System.out.println("Logout servlet redirected...");
				resp.sendRedirect("login.jsp");
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

				System.out.println("Logout servlet redirected...");
				resp.sendRedirect("login.jsp");
				return;

			}

			System.out.println("Cookie results: loginToken: " + loginToken + " userid: " + userid);

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

			Cookie cookie = new Cookie("loginToken", "");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
