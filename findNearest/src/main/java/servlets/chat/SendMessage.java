package servlets.chat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;
import databaseConnector.DatabaseConnect;

public class SendMessage {

	public boolean send(String userid, String receiverid, String msgText) throws IOException {

		System.out.println("-->>>  SendMessage started running...");

		boolean is_success = false;


		if (userid == null || receiverid == null) {

			System.out.println("input contains null value");
			return is_success;
		}

		StartChat startChat = new StartChat();
		String groupid = startChat.getGroupId(userid, receiverid);

		if (groupid == null) {
			
			System.out.println("groupid is null creating new");
			
			groupid = UUID.randomUUID().toString();
			boolean isGroupCreated = createGroup(groupid, userid, msgText);
			
			if(isGroupCreated) {
				
				boolean isUserAssigned = assignGroupToUser(groupid, userid);
				boolean isReceiverAssigned = assignGroupToUser(groupid, receiverid);
				
				if(isUserAssigned && isReceiverAssigned) {
					
					boolean isMessageSaved = saveMessages(groupid, userid, msgText);
					
					if(isMessageSaved) is_success = true;
				}
				
			}
			
		}
		else {
			
			boolean isLastMessageUpdatedToGroup = updateLastMessageToGroup(groupid, userid, msgText);
			
			if(isLastMessageUpdatedToGroup) {
				
				boolean isMessageSaved = saveMessages(groupid, userid, msgText);
				
				if(isMessageSaved) is_success = true;
				
			}
			
		}

		System.out.println("groupid: " + groupid);


		

		return is_success;

	}
	


	public boolean createGroup(String groupid, String userid, String msgText) {

		System.out.println("createGroup method started running...");

		boolean is_group_created = false;

		try {

			// connecting to database

			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed createGroup");
			} else {
				System.out.println(" DB connection created createGroup");
			}

			// Accessing Data from table
			PreparedStatement ptst = con
					.prepareStatement("insert into chat_group(groupid, senderid, last_message, time) values('" + groupid + "','" + userid + "','" + msgText + "',now())");

			ptst.executeUpdate();

			con.close();
			is_group_created = true;

		} catch (Exception e) {
			System.out.println("createGroup: " + e);
		}

		return is_group_created;

	}
	
	private boolean updateLastMessageToGroup(String groupid, String userid, String msgText) {
		
		System.out.println("updateLastMessageToGroup method started running...");

		boolean is_last_message_inserted = false;

		try {

			// connecting to database

			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed updateLastMessageToGroup");
			} else {
				System.out.println(" DB connection created updateLastMessageToGroup");
			}

			// Accessing Data from table
			PreparedStatement ptst = con
					.prepareStatement("update chat_group set senderid = '" + userid + "', last_message = '" + msgText + "', time = now() where groupid = '" + groupid + "'");

			ptst.executeUpdate();

			con.close();
			is_last_message_inserted = true;

		} catch (Exception e) {
			System.out.println("updateLastMessageToGroup: " + e);
		}

		return is_last_message_inserted;
		
	}
	
	private boolean assignGroupToUser(String groupid, String userid) {
		
		System.out.println("assignGroupToUser method started running...");

		boolean is_group_assigned = false;

		try {

			// connecting to database

			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed assignGroupToUser");
			} else {
				System.out.println(" DB connection created assignGroupToUser");
			}

			// Accessing Data from table
			PreparedStatement ptst = con
					.prepareStatement("insert into chat_inbox(userid, groupid) values('" + userid + "','" + groupid + "')");

			ptst.executeUpdate();

			con.close();
			is_group_assigned = true;

		} catch (Exception e) {
			System.out.println("assignGroupToUser: " +e);
		}

		return is_group_assigned;
		
	}
	
	public boolean saveMessages(String groupid, String userid, String msgText) {
		
		System.out.println("saveMessages method started...");
		
		boolean is_message_saved = false;
		
		try {

			// connecting to database
			
			Connection con = DatabaseConnect.getConnection();

			if (con.isClosed()) {
				System.out.println(" DB connection closed saveMessages");
			} else {
				System.out.println(" DB connection created saveMessages");
			}

			// Accessing Data from table
			PreparedStatement ptst = con
					.prepareStatement("insert into chat_messages(groupid, senderid, message_body, time) values('" + groupid + "','" + userid + "','" + msgText + "',now())");

			ptst.executeUpdate();

			con.close();
			is_message_saved = true;
			
		} catch (Exception e) {
			System.out.println("saveMessages: " + e);
		}
		
		return is_message_saved;
	}

}
