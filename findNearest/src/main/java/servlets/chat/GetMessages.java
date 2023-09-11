package servlets.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import databaseConnector.DatabaseConnect;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Messages;

public class GetMessages extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("-->>> Servlet: GetMessages started running...");

		ArrayList<Messages> list = new ArrayList<>();

		String userid = req.getParameter("userid");
		String receiverid = req.getParameter("receiverid");

		if (userid == null || receiverid == null) {

			System.out.println("input contains null value");
			String employeeJsonString = new Gson().toJson(list);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(employeeJsonString);
			out.close();
			return;

		}

		StartChat startChat = new StartChat();
		String groupid = startChat.getGroupId(userid, receiverid);

		if (groupid == null) {

			System.out.println("groupid is null");
			String employeeJsonString = new Gson().toJson(list);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(employeeJsonString);
			out.close();
			return;

		}

		System.out.println("userid: " + userid + " receiverid: " + receiverid + " groupid: " + groupid);

		// connecting to database

		try {

			Connection connection = DatabaseConnect.getConnection();

			if (connection.isClosed()) {
				System.out.println(" DB connection closed SendMessage");
			} else {
				System.out.println(" DB connection created SendMessage");
			}

			// Accessing Data from table
			PreparedStatement ptmt = connection.prepareStatement(
					"select * from chat_messages where groupid = '" + groupid + "'");

			ResultSet set = ptmt.executeQuery();

			while (set.next()) {

				list.add(new Messages(set.getString(1), set.getString(2), set.getString(3), set.getString(4)));
				System.out.println("Database: groupid: " + set.getString(1) + " | senderid: " + set.getString(2)
						+ " | messagebody: " + set.getString(3) + " | time: " + set.getString(4));
			}

			System.out.println("-->>> Servlet: GetMessages finished work...");
			connection.close();

		} catch (SQLException e) {

			System.out.println("-->>> Servlet: GetMessages stopped work...");
			e.printStackTrace();

		}

		String employeeJsonString = new Gson().toJson(list);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(employeeJsonString);
		out.close();

	}

}
