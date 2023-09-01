package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userUID = UUID.randomUUID().toString();
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		System.out.println(userUID);
		DatabaseConnect db = new DatabaseConnect();
		boolean bool = db.signUp(userUID, password, name, phone, email);
		
		String registerJsonStatus = new Gson().toJson(bool);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();
		
	}
	
	

}
