package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import databaseConnector.DatabaseConnect;


public class StartChat {

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
