package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginTokenValidationResponse;
import objects.User;

public class GetUserDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("get user details started running...");

		try {

			LoginTokenValidationResponse loginTokenValidationResponse = (LoginTokenValidationResponse) req
					.getAttribute("LoginTokenValidationResponse");

			if (loginTokenValidationResponse == null) {
				System.out.println("LoginTokenValidationResponse is null");
				resp.sendRedirect("login.jsp");
			}

			String userid = loginTokenValidationResponse.getUserid();

			if (userid == null) {
				System.out.println("userid is null");
				resp.sendRedirect("login.jsp");
			}

			User user = new User();

			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed GetUserDetails");
			} else {
				System.out.println(" DB connection created GetUserDetails");
			}

			// Accessing Data from table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select * from users");

			while (set.next()) {

				if(set.getString(1).equals(userid)) {
					
					user.setUserid(set.getString(1));
					user.setEmail(set.getString(2));
					user.setName(set.getString(4));
					user.setPhone(set.getString(5));
					user.setGender(set.getString(6));
					user.setLatitude(set.getString(7));
					user.setLongitude(set.getString(8));
					user.setProfession(set.getString(9));
					user.setAddress(set.getString(10));
					
				}
				
				System.out.println("Database: user id: " + set.getString(1) + " | Email: " + set.getString(2) + " | name: "
						+ set.getString(4));
			}

			con.close();

			String employeeJsonString = new Gson().toJson(user);

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(employeeJsonString);
			System.out.println("get user details stopped running...");
			out.close();

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("get user details stopped running...");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("get user details stopped running...");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
