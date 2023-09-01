package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUser extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String pin = req.getParameter("pin");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String profession = req.getParameter("profession");
		
		DatabaseConnect db = new DatabaseConnect();
		boolean bool = db.updateUserDetails(id, name, address, pin, phone, gender, latitude, longitude, profession);
		
		String registerJsonStatus = new Gson().toJson(bool);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(registerJsonStatus);

		out.close();
		
	}
	
	

}
