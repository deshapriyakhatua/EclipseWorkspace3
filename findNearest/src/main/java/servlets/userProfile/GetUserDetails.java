package servlets.userProfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class GetUserDetails extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("-->>> Servlet: get user details started running...");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		User user = new User();
		
		String userid = (String) req.getParameter("userid");

		if (userid == null) {
			
			System.out.println("userid is null");
			out.print(new Gson().toJson(user));
			out.close();
			System.out.println("-->>> Servlet: get user details stopped running...");
			return;
		}

		try {

			// connecting to database

			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed GetUserDetails");
			} else {
				System.out.println(" DB connection created GetUserDetails");
			}

			// Accessing Data from table
			PreparedStatement ptmt = con.prepareStatement("select * from users where useruid = ?");
			ptmt.setString(1, userid);
			ResultSet set = ptmt.executeQuery();

			if (set.next()) {

				user.setUserid(set.getString(1));
				user.setEmail(set.getString(2));
				user.setName(set.getString(4));
				user.setPhone(set.getString(5));
				user.setGender(set.getString(6));
				user.setLatitude(set.getString(7));
				user.setLongitude(set.getString(8));
				user.setProfession(set.getString(9));
				user.setAddress(set.getString(10));
				user.setProfile_pic(set.getString(11));
				user.setCover_pic(set.getString(12));

				System.out.println("Database: user ID: " + set.getString(1) + " | Email: " + set.getString(2)
						+ " | Name: " + set.getString(4));
			}

			con.close();
			System.out.println("-->>> Servlet: get user details finished work...");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			System.out.println("-->>> Servlet: get user details stopped running...");			
		}
		
		out.print(new Gson().toJson(user));
		out.close();

	}


}
