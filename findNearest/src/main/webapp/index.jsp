<%@page import="java.util.UUID"%>
<%@page import="servlets.chat.StartChat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>find Distance</title>
	
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	
	<style>
		
		@import url('https://fonts.googleapis.com/css?family=Abel');
		
		
		body {
			background-color: rgb(255, 255, 255);
			font-family: Abel, Arial, Verdana, sans-serif;
		}

		#user-section {
			width: 100%;
			height: auto;
			display: flex;
			flex-wrap: wrap;
			justify-content: space-evenly;
			align-items: center;
		}

		#input-for-user {
			width: 750px;
			height: 220px;
			display: flex;
			flex-direction: column;
			justify-content: space-evenly;
			align-items: center;
		}

		#user-profile {
			width: 750px;
			height: 220px;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		#choose-distance,
		#select-profession,
		#user-input-button,
		#select-gender {
			box-sizing: border-box;
			margin: 0;
			padding: 0;
			padding-left: 10px;
			width: 300px;
			height: 30px;
			border-width: 1px;
		}

		#user-input-button {
			background-color: rgb(244, 235, 235);
			transition: all 0.3s;
		}

		#user-input-button:hover {
			background-color: rgb(244, 202, 202);
		}

		#user-input-button:focus {
			background-color: rgb(244, 148, 148);
			border-color: rgb(244, 148, 148);
		}

		.profile-division {
			margin: 10px 0px;
			width: 600px;
			height: 180px;
			box-sizing: border-box;
			display: flex;
			flex-wrap: wrap;
			justify-content: center;
			align-items: center;
			border: 1px solid rgb(0, 0, 0);
		}

		.profile-top,
		.profile-bottom {
			width: 100%;
			height: 50%;
		}

		.profile-top {
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.name-phone {
			height: 100%;
			width: 80%;
		}

		.profile-image {
			height: 100%;
			width: 20%;
			display: flex;
			justify-content: center;
			align-items: center;
		}

		.name-phone p {
			margin: 1px;
			padding-left: 10px;
			padding-top: 5px;
		}

		.image-icon {
			margin: 0;
			text-align: center;
			font-size: 60px;
			border: 0px solid red;
		}

		.address,
		.gender,
		.email {
			margin: 1px;
			padding-left: 10px;
			padding-top: 5px;
		}

		/* output-section */

		#output-section {
			margin-top: 50px;
			box-sizing: border-box;
			width: 100%;
			height: auto;
			display: flex;
			justify-content: space-evenly;
			flex-direction: row;
			flex-wrap: wrap;
		}
		
		.card {
			width: 450px;
			height: 250px;
			background-color: #fff;
			background: linear-gradient(#f8f8f8, #fff);
			box-shadow: 0 8px 16px -8px rgba(0, 0, 0, 0.4);
			border-radius: 6px;
			overflow: hidden;
			position: relative;
			margin: 1.5rem;
		}
		
		.center {
			position: absolute;
			top: 50%;
			left: 50%;
			-webkit-transform: translate(-50%, -50%);
			transform: translate(-50%, -50%);
		}
		
		.card h1 {
			text-align: center;
		}

		.card .additional {
			position: absolute;
			width: 150px;
			height: 100%;
			background: linear-gradient(#f53e78, #c24889);
			transition: width 0.4s;
			overflow: hidden;
			z-index: 2;
		}

		.card:hover .additional {
			width: 100%;
			border-radius: 0 5px 5px 0;
		}

		.card .additional .user-card {
			width: 150px;
			height: 100%;
			position: relative;
			float: left;
		}

		.card .additional .user-card::after {
			content: "";
			display: block;
			position: absolute;
			top: 10%;
			right: -2px;
			height: 80%;
			border-left: 2px solid rgba(0, 0, 0, 0.025);

		}

		.profile-img {
			width: 80%;
			border-radius: 50%;
			border: 2px solid white;
		}

		.card .additional .more-info {
			width: 300px;
			float: left;
			position: absolute;
			left: 150px;
			height: 100%;
		}

		.card .additional .more-info h1 {
			color: #fff;
			margin-bottom: 0;
		}

		.card .additional .more-info .chat {
			position: absolute;
			bottom: 2rem;
			right: 2rem;
			border-radius: 50%;
			border: 2px solid rgb(255, 255, 255);
			background-color: transparent;
			color: #ffffff;
			font-size: 1rem;
			padding: 0.5rem;
			cursor: pointer;
			transition: all 0.4s;
		}

		.card .additional .more-info .like {
			position: absolute;
			bottom: 2rem;
			right: 6rem;
			border-radius: 50%;
			border: 2px solid rgb(255, 255, 255);
			background-color: transparent;
			color: #ffffff;
			font-size: 1rem;
			padding: 0.5rem;
			cursor: pointer;
			transition: all 0.4s;
		}

		.card .additional .more-info i:hover {
			background-color: white;
			color: #020000;
		}
		
		.card .additional .more-info input:hover {
			background-color: white;
			color: #020000;
		}

		.card .general .coords {
			margin-left: 1rem;
			margin-top: 0.5rem;
			color: #020000;
			font-size: 1rem;
			text-align: left;
		}

		.card .general {
			width: 300px;
			height: 100%;
			position: absolute;
			top: 0;
			right: 0;
			z-index: 1;
			box-sizing: border-box;
			padding: 1rem;
			padding-top: 0;
		}

		.card .general .more {
			padding: 0 1rem;
			position: absolute;
			bottom: 1rem;
			right: 2rem;
			font-size: 0.9em;
			font-weight: bold;
			border-radius: 1rem;
			border: 1px solid #f53e78;
		}

		/* output-section */

		#register {
			display: flex;
			justify-content: center;
			align-items: center;
			width: 100%;
			height: 50px;
		}

		#registerButton {

			width: 100px;
			height: 40px;
		}
	</style>
</head>

<body>

	<% 
		String userid = (String) request.getAttribute("userid");
	%>

	<section id="user-section">
		<div id="input-for-user">
			<input type="number" min="0" step="0.1" id="choose-distance" placeholder="Distance (km)" value="10">
			<select name="profession" id="select-profession">
				<option value="all">All</option>
				<option value="teacher">Teacher</option>
				<option value="artist">Artist</option>
				<option value="engineer">Engineer</option>
				<option value="doctor">Doctor</option>
				<option value="student">Student</option>
			</select>
			<select name="gender" id="select-gender">
				<option value="all">All</option>
				<option value="male">Male</option>
				<option value="female">Female</option>
			</select>
			<button id="user-input-button">SUBMIT</button>

		</div>
		<div id="user-profile">
		</div>
	</section>

	<section id="output-section">

	</section>


	<section id="register">
		<a href="profile.jsp"><button id="registerButton" type="submit">profile</button></a>
		<a href="logout"><button id="logoutButton" type="submit">Logout</button></a>
	</section>
	

</body>

<script>

	let userid = "<%= request.getAttribute("userid") %>";
	let user = {};

	getUserDetails();

	async function getUserDetails() {

		console.log("getUserDetails called");

		try {

			console.log("Fetch Process Initiated...");

			let res = await fetch("http://localhost:8080/findNearest/getUserDetails", {
				"headers": {
					"content-type": "application/x-www-form-urlencoded"
				},
				"body": "userid="+userid,
				"method": "POST"
			});

			let data = await res.text();
			console.log("Users : " + data);
			user = JSON.parse(data);
			sessionStorage.setItem("user", user);

			populateUser();

		}
		catch (err) {
			console.log(err);
		}

	}


	function populateUser() {

		let profileDiv = document.getElementById("user-profile");
		profileDiv.innerHTML = "";

		// append in user profile

		if (user.userid != undefined) {
			let profileDivision = document.createElement("div");
			profileDivision.className = "profile-division";

			profileDivision.innerHTML = `<div class="profile-top">
											<div class="name-phone">
												<p class="name">Name: \${user.name}</p>
												<p class="phone">Phone: \${user.phone}</p>
												<p class="profession">Profession: \${user.profession}</p>
											</div>
											<div class="profile-image">
												<p class="image-icon"></p>
											</div>
										</div>
										<div class="profile-bottom">
											<p class="email"> Email: \${user.email} </p>
											<p class="gender"> Gender: \${user.gender} </p>
											<p class="address"> Address: \${user.address} </p>
											<input type="hidden" id="user-lat" name="userLat" value="\${user.latitude}">
											<input type="hidden" id="user-lon" name="userLon" value="\${user.longitude}">
											<input type="hidden" id="userid" name="userid" value="\${user.userid}">
										</div>`;

			profileDiv.append(profileDivision);


		}


	}


</script>
<script>

	document.getElementById("user-input-button").addEventListener("click", fetchAndPopulate);
	let userData = sessionStorage.getItem("user");

	function fetchAndPopulate() {
		console.log("button pressed");

		let userLat = document.getElementById("user-lat").value;

		let userLon = document.getElementById("user-lon").value;

		let distance = document.getElementById("choose-distance").value;

		let selectProfession = document.getElementById("select-profession");
		let profession = selectProfession.options[selectProfession.selectedIndex].value;

		let selectGender = document.getElementById("select-gender");
		let gender = selectGender.options[selectGender.selectedIndex].value;

		console.log(userLat, userLon, distance, profession, gender);

		getData();

		async function getData() {

			console.log("getData called");

			try {

				console.log("Fetch Process Initiated...");

				let res = await fetch("http://localhost:8080/findNearest/getOtherUsers", {
					"headers": {
						"content-type": "application/x-www-form-urlencoded"
					},
					"body": `userLat=\${userLat}&userLon=\${userLon}&distance=\${distance}&profession=\${profession}&gender=\${gender}`,
					"method": "POST"
				});

				let data = await res.text();
				console.log("Users : " + data);
				let outList = JSON.parse(data);

				let outputSection = document.getElementById("output-section");
				outputSection.innerHTML = "";
				
				let femaleImg = "https://cdn1.iconfinder.com/data/icons/avatars-1-5/136/87-512.png";
				let maleImg = "https://cdn-icons-png.flaticon.com/512/147/147133.png";

				for (let i = 0; i < outList.length; i++) {

					if (document.getElementById("userid").value === outList[i].userid) continue;

					let profileDivision = document.createElement("div");
					profileDivision.className = "card";

					profileDivision.innerHTML = `<div class="additional">
														<div class="user-card">
															<img class="profile-img center"
																src="\${outList[i].gender.toUpperCase() == "MALE"  ?maleImg :femaleImg}"
																alt="">
														</div>
														<div class="more-info">
															<h1>\${outList[i].name}</h1>
															<form id="startChat" action="chatUI.jsp" method="post">
																<input type="hidden" name="userid" value="\${ userid }">
																<input type="hidden" name="receiverid" value="\${outList[i].userid}">
																<input type="submit" class="material-icons chat" value="chat">
															</form>
															
															<a href="#"><i class="material-icons like">thumb_up</i></a>
														</div>
													</div>
													<div class="general">
														<h1>\${outList[i].name}</h1>
														<div class="coords">
															<span>\${outList[i].profession}</span>
														</div>
														<div class="coords">
															<span>\${outList[i].address}</span>
														</div>
														<div class="coords">
															<span>\${outList[i].phone}</span>
														</div>
														<div class="coords">
															<span>\${outList[i].email}</span>
														</div>
														<span class="more">\${outList[i].gender}</span>
													</div>`;


					outputSection.append(profileDivision);


				}

			}
			catch (err) {
				console.log(err);
			}

		}


	}


</script>


</html>