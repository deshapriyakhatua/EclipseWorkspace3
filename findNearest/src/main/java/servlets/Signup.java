package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("signup servlet started running...");
		String userUID = UUID.randomUUID().toString();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println( "input: userid: " + userUID + " email: " + email + " password: " + password );

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
			PreparedStatement ptst = con
					.prepareStatement("insert into users(useruid, email, password) values(?,?,?)");

			ptst.setString(1, userUID);
			ptst.setString(2, email);
			ptst.setString(3, password);
			ptst.executeUpdate();

			con.close();
			System.out.println("signup servlet redirected...");
			resp.sendRedirect("login.jsp");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("signup servlet redirected...");
			resp.sendRedirect("signup.jsp");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("signup servlet redirected...");
			resp.sendRedirect("signup.jsp");

		}

	}

}
