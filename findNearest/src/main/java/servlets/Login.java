package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		System.out.println(userId+" "+password);
		DatabaseConnect dbConnect = new DatabaseConnect();
		
		boolean loginStatus = dbConnect.logIn(userId, password);
		LoginResponse loginResponse;
		
		if(loginStatus) {
			
			String loginToken = UUID.randomUUID().toString();
			Cookie cookie = new Cookie("loginToken", loginToken);
			cookie.setMaxAge(24*60*60);
			resp.addCookie(cookie);
			loginResponse = new LoginResponse("Login Successful", userId, loginToken);
			
		}
		else {
			loginResponse = new LoginResponse("Login Failed", "", "");
		}
		
		String registerJsonStatus = new Gson().toJson(loginResponse);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();
		
	}
	

}
