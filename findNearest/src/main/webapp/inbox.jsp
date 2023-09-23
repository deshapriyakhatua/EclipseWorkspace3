<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="dao.Chat"%>
<%@page import="dao.GetUser"%>
<%@page import="model.LastMessage"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link href="https://fonts.googleapis.com/css2?family=Abel&display=swap" rel="stylesheet">

<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	
	body{
		font-family: 'Abel', sans-serif;
	}
	.inbox_container_section {
		padding: 10px 0;
		margin: 10px auto;
		width: 900px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		gap: 30px;
	}
	.inbox_main {
		padding: 0 20px;
		width: 100%;
		height: 50px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
	}
	
	.title {
		font-size: 20px;
		font-weight: bold;
	}
	
	.user_inbox {
		width: 100%;
		height: 80px;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		gap: 20px;
		cursor: pointer;
		transition: all 0.4s;
	}
	
	.user_inbox:hover {
		background-color: #eee;
	}
	
	.user_inbox > img {
		width: 50px;
		height: 50px;
		border-radius: 50%;
	}
	
	.message_container {
		
		width: 60%;
		height: 95%;
		padding: 0 20px;
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		align-items: flex-start;
	}
	
	.recipient_name {
		font-size: 18px;
		font-weight: bold;
	}
	
	.last_message_time {
		font-size: 16px;
	}
</style>
</head>
<body>
	
	<%@ include file="navbar.html" %>
	
	<section class="inbox_container_section">
		<div class="inbox_main">
			<p class="title">Inbox</p>
			<i class="material-icons">more_vert</i>
		</div>
		
		
		<%
		
		String userid = (String) request.getAttribute("userid");
		
		if (userid == null) {
			System.out.println("input contains null value");
			return;
		}

		System.out.println("userid: " + userid);
		
		Chat chat = new Chat();
		
		ArrayList<String> groupIDs = chat.getGroupId(userid);
		
		System.out.println( "Group ids: " + groupIDs);
		
		for(String groupid : groupIDs){
			LastMessage lastMessage = chat.getLastMessage(groupid);
			String messageContent = lastMessage.getMessageContent();
			String time = lastMessage.getTime();
			ArrayList<String> usersInGroup = chat.getUsersInGroup(groupid);
			System.out.println(usersInGroup);
			String recipientid = usersInGroup.get(0).equals( userid ) ?usersInGroup.get(1) :usersInGroup.get(0);
			GetUser getUser = new GetUser();
			User recipient = getUser.getUserDetails(recipientid);
			String recipientName = recipient.getName();
			String recipientImgURL = recipient.getProfile_pic();
			System.out.println();
			
			%>
				<div class="user_inbox">
					<img src="<%= recipientImgURL %>">
					<div class="message_container">
						<p class="recipient_name"><%= recipientName %></p>
						<p class="message_content"> <%= messageContent %> </p>
					</div>
					<p class="last_message_time"> <%= time %> </p>
				</div>
			<%
			
		}
		
		%>
	</section>
</body>
<script type="text/javascript" >
	
</script>
</html>