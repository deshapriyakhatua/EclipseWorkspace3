package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import objects.User;

public class CalculateDistance extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			System.out.println(req.getParameter("userLat"));
			System.out.println(req.getParameter("userLon"));
			System.out.println(req.getParameter("distance"));
			System.out.println(req.getParameter("profession"));
			System.out.println(req.getParameter("gender"));
			
			double userLat = Double.parseDouble(req.getParameter("userLat").trim());
			double userLon = Double.parseDouble(req.getParameter("userLon").trim());
			long distance = (long)( Double.parseDouble(req.getParameter("distance").trim())*1000 );
			String profession = req.getParameter("profession").trim();
			String gender = req.getParameter("gender").trim();
			
			ArrayList<User> list = new ArrayList<>();
			DatabaseConnect dbConnect = new DatabaseConnect();
			try {
				list = dbConnect.showUsers();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ArrayList<User> outList = new ArrayList<>();
			
			for(int i=0; i<list.size(); i++) {
				
				double lat1, lat2, lon1, lon2, el1, el2;

				lat1 = userLat;
				lat2 = Double.parseDouble(list.get(i).getLatitude());
				lon1 = userLon;
				lon2 = Double.parseDouble(list.get(i).getLongitude());

				el1 = 0.0;
				el2 = 0.0;
				
				double meter = getDistance(lat1, lat2, lon1, lon2, el1, el2);
				
				if( meter <= distance && ( profession.equals("all") || list.get(i).getProfession().equals(profession) ) && ( gender.equals("all") || list.get(i).getGender().equals(gender) ) ) {
					outList.add(list.get(i));
				}
				
			}

			String employeeJsonString = new Gson().toJson(outList);

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(employeeJsonString);

			out.close();
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
