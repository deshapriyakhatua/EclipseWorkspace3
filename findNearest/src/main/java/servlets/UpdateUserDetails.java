package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginTokenValidationResponse;

public class UpdateUserDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("UpdateUserDetails servlet running started...");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String profession = req.getParameter("profession");
		String address = req.getParameter("address");

		System.out.println("input: name: " + name + " phone: " + phone + " gender: " + gender + " latitude: " + latitude
				+ " longitude: " + longitude + " profession: " + profession + " address: " + address);
		
		if(name == null || phone == null || gender == null || latitude == null || longitude == null || profession == null || address == null) {
			System.out.println("null input found please refill");
			System.out.println("UpdateUserDetails redirected...");
			resp.sendRedirect("UpdateUserDetails.html");
		}

		try {
			
			LoginTokenValidationResponse loginTokenValidationResponse = (LoginTokenValidationResponse) req
					.getAttribute("LoginTokenValidationResponse");

			if (loginTokenValidationResponse == null) {
				System.out.println("LoginTokenValidationResponse is null");
				resp.sendRedirect("UpdateUserDetails.html");
			}

			String userid = loginTokenValidationResponse.getUserid();

			if (userid == null) {
				System.out.println("userid is null");
				resp.sendRedirect("UpdateUserDetails.html");
			}
			
			System.out.println("userid: " + userid);
			
			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed UpdateUserDetails");
			} else {
				System.out.println(" DB connection created UpdateUserDetails");
			}

			// Accessing Data from table
			PreparedStatement ptst = con.prepareStatement(
					"update users set name = ?, phone = ?, gender = ?, latitude = ?, longitude = ?, profession = ?, address = ? where useruid = \'" + userid + "\'");

			ptst.setString(1, name);
			ptst.setString(2, phone);
			ptst.setString(3, gender);
			ptst.setString(4, latitude);
			ptst.setString(5, longitude);
			ptst.setString(6, profession);
			ptst.setString(7, address);
			ptst.executeUpdate();

			con.close();
			System.out.println("Successfully data updated");
			System.out.println("UpdateUserDetails redirected...");
			resp.sendRedirect("profile.jsp");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("UpdateUserDetails redirected...");
			resp.sendRedirect("UpdateUserDetails.html");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("UpdateUserDetails redirected...");
			resp.sendRedirect("UpdateUserDetails.html");

		}

	}

}
