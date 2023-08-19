package mavenProject1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//@WebServlet(urlPatterns = {"/servlet/Home"}, name = "Home")
public class Home extends HttpServlet{

	private static final long serialVersionUID = 102831973239L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Students students = new Students("Sankalp", 14, "Medical", 10025);
		
		Gson gson = new Gson();
		String studentsJSON = gson.toJson(students);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.write(studentsJSON);
		out.close();
		
	}
}
