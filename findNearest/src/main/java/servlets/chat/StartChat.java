package servlets.chat;

//import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnector.DatabaseConnect;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

public class StartChat {


	
//	protected void getMessages(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		System.out.println("-->>> Servlet: StartChat started running...");
//
//		String userid = req.getParameter("userid");
//		String receiverid = req.getParameter("receiverid");
//
//		if (userid == null || receiverid == null) {
//			System.out.println("input contains null value");
//			System.out.println("<<<-- Servlet: StartChat servlet redirected...");
//			resp.sendRedirect("badrequest.jsp");
//			return;
//		}
//
//		System.out.println("userid: " + userid + " receiverid: " + receiverid);
//
//		// connecting to database
//
//		try {
//
//			Connection connection = DatabaseConnect.getConnection();
//
//			if (connection.isClosed()) {
//				System.out.println(" DB connection closed StartChat");
//			} else {
//				System.out.println(" DB connection created StartChat");
//			}
//
//			// Accessing Data from table
//			PreparedStatement ptmt = connection.prepareStatement(
//					"select * from chat_messages where groupid in (select a.groupid from (select groupid from chat_inbox where userid = '"
//							+ userid + "') a , (select groupid from chat_inbox where userid = '" + receiverid
//							+ "') b where a.groupid = b.groupid) ");
//
//			ResultSet set = ptmt.executeQuery();
//
//			while (set.next()) {
//
//				System.out.println("Database: groupid: " + set.getString(1) + " | senderid: " + set.getString(2)
//						+ " | messagebody: " + set.getString(3) + " | time: " + set.getString(4));
//			}
//
//			connection.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public String getGroupId(String userid, String receiverid) {
		
		System.out.println("getGroupId method started running...");
		String groupid = null;
		
		try {

			Connection connection = DatabaseConnect.getConnection();

			if (connection.isClosed()) {
				System.out.println(" DB connection closed getGroupId");
			} else {
				System.out.println(" DB connection created getGroupId");
			}

			// Accessing Data from table
			PreparedStatement ptmt = connection.prepareStatement(
					"select a.groupid from (select groupid from chat_inbox where userid = '"
							+ userid + "') a , (select groupid from chat_inbox where userid = '" + receiverid
							+ "') b where a.groupid = b.groupid");

			ResultSet set = ptmt.executeQuery();

			if (set.next()) {
				
				groupid = set.getString(1);
				System.out.println("Database: groupid: " + set.getString(1));
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return groupid;
		
	}

	
}
