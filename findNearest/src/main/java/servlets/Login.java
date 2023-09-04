package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginResponse;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String getDestinationURL = req.getParameter("getDestinationURL").substring(13);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("input email: " + email + " input password: " + password + " getDestinationURL: " + getDestinationURL);
		System.out.println(req.getAttribute("LoginTokenValidationResponse"));

		// connecting to Database

		LoginResponse loginResponse = new LoginResponse("", "", "");

		try {

			// connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/findnearest";

			Connection con = DriverManager.getConnection(url, "root", "1036");

			if (con.isClosed()) {
				System.out.println(" DB connection closed logIn");
			} else {
				System.out.println(" DB connection created logIn");
			}

			// Accessing Data from users table
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("select useruid, email, password from users");

			while (set.next()) {

				System.out.println("DBemail: " + set.getString(2) + " DBpassword: " + set.getString(3));

				if (set.getString(2).equals(email) && set.getString(3).equals(password)) {

					System.out.println("user is registered");
					System.out.println("updating/inserting access token to database");

					String loginToken = UUID.randomUUID().toString();
					String userid = set.getString(1);
					Timestamp currTime = new Timestamp(System.currentTimeMillis());

					// Inserting Data to login_token table
					PreparedStatement ptst = con
							.prepareStatement("replace into login_token(tokenid, useruid, validity) values(?,?,?)");

					ptst.setString(1, loginToken);
					ptst.setString(2, userid);
					ptst.setTimestamp(3, currTime);
					ptst.executeUpdate();

					System.out.println("updated/inserted access token to database");

					loginResponse.setMessage("Login Successful");
					loginResponse.setUserid(set.getString(1));
					loginResponse.setAccessToken(loginToken);
					
					Cookie cookie = new Cookie("loginToken", loginResponse.getAccessToken());
					cookie.setMaxAge(24 * 60 * 60);
					resp.addCookie(cookie);
					
					RequestDispatcher rDispatcher = req.getRequestDispatcher(getDestinationURL);
					rDispatcher.forward(req, resp);
					
					return;


				}

			}

			con.close();

			if(loginResponse.getUserid().length() == 0) { loginResponse.setMessage("Login Failed: User not found/registered in database"); }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResponse.setMessage("Login Failed: " + e);

		} catch (SQLException e) {
			e.printStackTrace();
			loginResponse.setMessage("Login Failed: " + e);

		}


		String registerJsonStatus = new Gson().toJson(loginResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();

	}

}
