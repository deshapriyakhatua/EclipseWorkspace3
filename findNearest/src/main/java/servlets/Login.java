package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.UUID;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.LoginResponse;

public class Login extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println(email + " " + password);
		DatabaseConnect dbConnect = new DatabaseConnect();
		
		LoginResponse loginResponse = dbConnect.logIn(email, password);
		
		if(loginResponse.getMessage().equals("Login Successful") && loginResponse.getUserid().length() > 0) {
			
			Cookie cookie = new Cookie("loginToken", loginResponse.getAccessToken());
			cookie.setMaxAge(24*60*60);
			resp.addCookie(cookie);
			
		}
		
		String registerJsonStatus = new Gson().toJson(loginResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();
		
	}
	

}
