<%@page import="java.util.UUID"%>
<%@page import="dao.StartChat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="code.jquery.com_jquery-3.7.1.min.js"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<style type="text/css">
@import url('https://fonts.googleapis.com/css?family=Abel');

:root {
	--body-bg: #afedea;
	--msger-bg: #fff;
	--border: 2px solid #ddd;
	--left-msg-bg: #ececec;
	--right-msg-bg: #579ffb;
}

html {
	box-sizing: border-box;
}

*, *:before, *:after {
	margin: 0;
	padding: 0;
	box-sizing: inherit;
	font-family: Abel, Arial, Verdana, sans-serif;
}

body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: var(--body-bg);
}

.msger {
	display: flex;
	flex-flow: column wrap;
	justify-content: space-between;
	width: 100%;
	max-width: 867px;
	margin: 25px 10px;
	height: calc(100% - 50px);
	border: var(--border);
	border-radius: 5px;
	background: var(--msger-bg);
	box-shadow: 0 15px 15px -5px rgba(0, 0, 0, 0.2);
}

.msger-header {
	display: flex;
	justify-content: space-between;
	padding: 10px;
	border-bottom: var(--border);
	background: #fff;
	color: #000;
}

.msger-header-title {
	display: flex;
	justify-content: space-between;
	align-items: center;
	
}

#recerverImgHeader {
	box-sizing: border-box;
	width: 30px;
	height: auto;
}

#recerverNameHeader {
	margin-left: 10px;
	font-stretch:wider;
	font-size: 20px;
}

.msger-chat-messages-container {
	flex: 1;
	overflow-y: auto;
	padding: 10px;
}

.msger-chat-messages-container::-webkit-scrollbar {
	width: 6px;
}

.msger-chat-messages-container::-webkit-scrollbar-track {
	background: #ddd;
}

.msger-chat-messages-container::-webkit-scrollbar-thumb {
	background: #bdbdbd;
}

.msg {
	display: flex;
	align-items: flex-end;
	margin-bottom: 10px;
}

.msg:last-of-type {
	margin: 0;
}

.msg-img {
	width: 50px;
	height: 50px;
	margin-right: 10px;
	background: #ddd;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	border-radius: 50%;
}

.msg-bubble {
	max-width: 450px;
	padding: 15px;
	border-radius: 15px;
	background: var(--left-msg-bg);
}

.msg-info {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.msg-info-name {
	margin-right: 10px;
	font-weight: bold;
	letter-spacing: 0.5px;
}

.msg-info-time {
	font-size: 0.85em;
}

.left-msg .msg-bubble {
	border-bottom-left-radius: 0;
}

.right-msg {
	flex-direction: row-reverse;
}

.right-msg .msg-bubble {
	background: var(--right-msg-bg);
	color: #fff;
	border-bottom-right-radius: 0;
}

.right-msg .msg-img {
	margin: 0 0 0 10px;
}

.msger-form {
	display: flex;
	padding: 10px;
	border-top: var(--border);
	background: #eee;
}

.msger-form * {
	padding: 10px;
	border: none;
	border-radius: 3px;
	font-size: 1em;
}

.msger-input {
	flex: 1;
	background: #ddd;
}

.msger-input:focus {
	outline: none;
	color: #000000;
	border: 1px solid #f44177;
}

.msger-send-btn {
	box-sizing: border-box;
	padding: 0;
	margin-left: 10px;
	background-color: #f44177;
	color: #ffffff;
	font-weight: bold;
	cursor: pointer;
}



#send-icon {
	margin: 0;
	padding: 0;
	width: 40px;
	font-size: 18px;
	line-height: 40px;
	text-align: center;
	vertical-align: middle;
	border-radius: 50%;
	cursor: pointer;
	background-color: #f44177;
	color: #ffffff;
	transition: all 0.23s;
}

#send-icon:hover {
	rotate: -30deg;
}

.msger-chat-messages-container {
	background-color: #fcfcfe;
}
</style>
</head>

<body>

	<%
	System.out.println("-->>> JSP: chatUI started running...");

	String userid = request.getParameter("userid");
	String receiverid = request.getParameter("receiverid");

	if (userid == null || receiverid == null) {
		System.out.println("input contains null value");
		System.out.println("<<<-- JSP: chatUI servlet redirected...");
		return;
	}

	System.out.println("userid: " + userid + " receiverid: " + receiverid);
	
	StartChat startChat = new StartChat();
	
	String groupid = startChat.getGroupId(userid, receiverid);
	
	if(groupid == null){
		System.out.println("groupid is null creating new");
		groupid = UUID.randomUUID().toString();
	}
	
	System.out.println("groupid: " + groupid);
	
	%>

	<section class="msger">
		<header class="msger-header">
			<div class="msger-header-title">
				<img class="" id="recerverImgHeader"></img>
				<div id="recerverNameHeader"></div>
			</div>
			<div class="msger-header-options">
				<span><i class=""></i></span>
			</div>
		</header>

		<main class="msger-chat-messages-container" id="msger-chat-messages-container">
			
		</main>

		<form class="msger-form" >
			<input type="hidden" id="groupid" value="<%= groupid %>">
			<input type="hidden" id="userid" value="<%= userid %>">
			<input type="hidden" id="receiverid" value="<%= receiverid %>">
			<input type="text" class="msger-input" autocomplete="off" id="msger-input"
				placeholder="Enter your message...">
			<button type="submit" class="msger-send-btn" id="msger-send-btn"><span class="material-icons" id="send-icon">send</span></button>
		</form>
	</section>


</body>

<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>

<script type="text/javascript">



chatUI();
async function chatUI(){
	
	const msgerChat = document.querySelector(".msger-chat-messages-container");
	const msgerForm = document.querySelector(".msger-form");
	const msgerInput = document.querySelector(".msger-input");

	
	
	// Icons made by Freepik from www.flaticon.com
	const groupid = "<%= groupid %>";
	const userid = "<%= userid %>";
	const receiverid = "<%= receiverid %>";
	
	const user = await getUserDetails(userid);
	const receiver = await getUserDetails(receiverid);
	
	console.log("user: " + user," receiver: " + receiver);
	
	const receiverName = receiver.name;
	const userName = user.name;

	document.title = receiverName;
	document.getElementById("recerverNameHeader").innerText = receiverName;
	document.getElementById("recerverImgHeader").src = receiver.profile_pic;
	getMessages(populateMessages);
	
	//setInterval(() => {
	//	getMessages(populateMessages);
	//},2000);
	
	let socket = new WebSocket("ws://" + "localhost:8080/findNearest/" + "chat?userid=" + userid + "&recipientid=" + receiverid + "&groupid=" + groupid);
	
	socket.onmessage = (e)=>{ 
		console.log(e.data);
		appendMessage(receiver.name, receiver.profile_pic, "left", e.data, new Date().toLocaleString());
	};
	
	msgerForm.addEventListener("submit", event => {	
		
		event.preventDefault();
		
		const msgText = msgerInput.value.trim();
		if (msgText.length == 0) return;
		
		socket.send(msgText);
		appendMessage(user.name, user.profile_pic, "right", msgText, new Date().toLocaleString());
		msgerInput.value = "";
		
	});
	
	
	
	async function getUserDetails( userid ){
		
		let user = {};

		console.log("getUserDetails called");

		try {

			console.log("Fetch Process Initiated...");

			let res = await fetch("http://localhost:8080/findNearest/getUserDetails", {
				"headers": {
					"content-type": "application/x-www-form-urlencoded"
				},
				"body": "userid=" + userid,
				"method": "POST"
			});

			let data = await res.text();
			console.log(userid+ " : " + data);
			user = JSON.parse(data);
			

		}catch (err) {
			
			console.log(err);
			
		}
		
		return user;

	}
	
	
	async function getMessages(callback){
		
		console.log("getMessages method called");
		let messages = [];
		
		try {

			console.log("Fetch Process Initiated...");

			let res = await fetch("http://localhost:8080/findNearest/getMessages", {
				"headers": {
					"content-type": "application/x-www-form-urlencoded"
				},
				"body": `groupid=\${groupid}&userid=\${userid}&receiverid=\${receiverid}`,
				"method": "POST"
			});

			let data = await res.text();
			console.log("messages : " + data);
			messages = JSON.parse(data);
			
			if(callback) callback(messages);
			
		}catch (err) {
			console.log(err);
		}
				
	}
	
	function populateMessages(messages){
		
		if(messages.length == 0) return;
		msgerChat.innerHTML = "";
		messages.forEach((elem, index, array) => {
			
			let name, img, side,text, time;
			
			text = elem.message;
			time = elem.time;
			
			if(elem.senderid == userid){
				name = userName;
				side = "right";
				img = user.profile_pic;
			}
			else{
				name = receiverName;
				side = "left";
				img = receiver.profile_pic;
			}
			
			appendMessage(name, img, side, text, time);
			
		});
	}

	function appendMessage(name, img, side, text, time) {
		
		const msgHTML = `
					    <div class="msg \${side}-msg">
					      <div class="msg-img" style="background-image: url(\${img})"></div>
					
					      <div class="msg-bubble">
					        <div class="msg-info">
					          <div class="msg-info-name">\${name}</div>
					          <div class="msg-info-time">\${formatDate(time)}</div>
					        </div>
					
					        <div class="msg-text">\${text}</div>
					      </div>
					    </div>
					  `;

		msgerChat.insertAdjacentHTML("beforeend", msgHTML);
		msgerChat.scrollTop += 500;
	}


	// Utils

	function formatDate(time) {
		const day = time.slice(0,10);
		const hours = time.slice(11, 13);
		const minutes = time.slice(14, 16);

		return `\${hours}:\${minutes}`;
	}

	function random(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	
}
	
	
</script>

</html>