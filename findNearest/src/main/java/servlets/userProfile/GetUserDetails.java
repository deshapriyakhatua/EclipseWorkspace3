package servlets.userProfile;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import dao.GetUser;
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
		
		GetUser getUser = new GetUser();
		try {
			user = getUser.getUserDetails(userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.print(new Gson().toJson(user));
		out.close();

	}


}
