package servlets.loginSignup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("-->>> Servlet: Signup servlet started running...");
		String userUID = UUID.randomUUID().toString();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println( "input: userid: " + userUID + " email: " + email + " password: " + password );

		if(email == null || password == null) {
			System.out.println("email and password should not be null");
			System.out.println("<<<-- Servlet: signup servlet redirected...");
			resp.sendRedirect("signup.jsp");
			return;
		}
		
		if(isValidEmail(email)) {
			System.out.println("email pattern not valid");
			System.out.println("<<<-- Servlet: signup servlet redirected...");
			resp.sendRedirect("signup.jsp");
			return;
		}
		
		if(password.length() > 16 || password.length() < 8) {
			System.out.println("password length should between 8-16");
			System.out.println("<<<-- Servlet: signup servlet redirected...");
			resp.sendRedirect("signup.jsp");
			return;
		}
		try {
			// connecting to database
			
			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed signUp");
			} else {
				System.out.println(" DB connection created signUp");
			}

			// Accessing Data from table
			PreparedStatement ptst = con
					.prepareStatement("insert into users(useruid, email, password) values(?,?,?)");

			ptst.setString(1, userUID);
			ptst.setString(2, email);
			ptst.setString(3, password);
			ptst.executeUpdate();

			con.close();
			System.out.println("<<<-- Servlet: signup servlet redirected...");
			resp.sendRedirect("login.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("<<<-- Servlet: signup servlet redirected...");
			resp.sendRedirect("signup.jsp");

		}

	}
	
	public boolean isValidEmail(String email) {
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        
	}

}
