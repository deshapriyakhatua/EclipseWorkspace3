package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		System.out.println(userId+" "+password);
		DatabaseConnect dbConnect = new DatabaseConnect();
		
		Object loginStatus = dbConnect.logIn(userId, password);
		String registerJsonStatus = new Gson().toJson(loginStatus);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();
		
	}
	

}
