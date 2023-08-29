package findNearest;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalculateDistance extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		double lat1, lat2, lon1, lon2, el1, el2;

		lat1 = Double.parseDouble(req.getParameter("latFrom"));
		lat2 = Double.parseDouble(req.getParameter("latTo"));
		lon1 = Double.parseDouble(req.getParameter("lonFrom"));
		lon2 = Double.parseDouble(req.getParameter("lonTo"));

		el1 = 0.0;
		el2 = 0.0;

		double meter = getDistance(lat1, lat2, lon1, lon2, el1, el2); 
		double feet = 3.281 * meter;
		double miles = meter * 0.00062137119;
		
		Distances dist = new Distances(meter, feet, miles);

		String employeeJsonString = new Gson().toJson(dist);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(employeeJsonString);

		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	public static double getDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}

}
