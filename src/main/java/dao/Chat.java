package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseConnector.DatabaseConnect;
import model.LastMessage;

public class Chat {

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
			PreparedStatement ptmt = connection
					.prepareStatement("select a.groupid from (select groupid from chat_inbox where userid = '" + userid
							+ "') a , (select groupid from chat_inbox where userid = '" + receiverid
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

	public ArrayList<String> getGroupId(String userid) {

		System.out.println("getGroupId method started running...");
		ArrayList<String> list = new ArrayList<>();

		try {

			Connection connection = DatabaseConnect.getConnection();

			if (connection.isClosed()) {
				System.out.println(" DB connection closed getGroupId");
			} else {
				System.out.println(" DB connection created getGroupId");
			}

			// Accessing Data from table
			PreparedStatement ptmt = connection
					.prepareStatement("select groupid from chat_inbox where userid = '" + userid + "'");

			ResultSet set = ptmt.executeQuery();

			while (set.next()) {

				list.add(set.getString(1));

			}

			System.out.println("Database: groupids: " + list);

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public LastMessage getLastMessage(String groupid) {

		System.out.println("getGroupId method started running...");
		LastMessage lastMessage = new LastMessage();

		try {

			Connection connection = DatabaseConnect.getConnection();

			if (connection.isClosed()) {
				System.out.println(" DB connection closed getLastMessage");
			} else {
				System.out.println(" DB connection created getLastMessage");
			}

			// Accessing Data from table
			PreparedStatement ptmt = connection
					.prepareStatement("select * from chat_group where groupid = '" + groupid + "'");

			ResultSet set = ptmt.executeQuery();

			if (set.next()) {

				lastMessage.setGroupid(set.getString(1));
				lastMessage.setSenderid(set.getString(2));
				lastMessage.setMessageContent(set.getString(3));
				lastMessage.setTime(set.getString(4));
				System.out.println("Database: last message: " + lastMessage.getGroupid() +" "+ lastMessage.getSenderid() +" "+ lastMessage.getMessageContent() +" "+ lastMessage.getTime() );
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastMessage;

	}
	
	public ArrayList<String> getUsersInGroup(String groupid) {

		System.out.println("getGroupId method started running...");
		ArrayList<String> list = new ArrayList<>();

		try {

			Connection connection = DatabaseConnect.getConnection();

			if (connection.isClosed()) {
				System.out.println(" DB connection closed getUsersInGroup");
			} else {
				System.out.println(" DB connection created getUsersInGroup");
			}

			// Accessing Data from table
			PreparedStatement ptmt = connection
					.prepareStatement("select userid from chat_inbox where groupid = '" + groupid + "'");

			ResultSet set = ptmt.executeQuery();

			while (set.next()) {

				list.add(set.getString(1));
				
			}

			System.out.println("Database: users in group: " + list);
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

}
