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
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Abel&display=swap">
	
	<style>		
	
		* {
			padding: 0;
			margin: 0;
			box-sizing: border-box;
		}
		
		body {
			background-color: rgb(255, 255, 255);
			font-family: 'Abel', sans-serif;
			--theme-color: #f44177;
		}

		#user-section {
			margin: 0 auto;
			padding: 50px 0;
			width: 900px;
			height: auto;
			display: flex;
			flex-direction: column;
			justify-content: space-evenly;
			align-items: center;
		}
		
		#user-section-title {
			width: max-content;
			border-bottom: 1px solid red;
		}

		#input-for-user {
			margin-top: 50px;
			padding: 50px 0;
			width: 100%;
			display: flex;
			flex-direction: column;
			justify-content: space-evenly;
			align-items: center;
			gap: 40px;
			border: 1px solid rgb(200, 200, 200);
		}
		
		.input-span-container {
			width: 60%;
			height: 40px;
			display: flex;
			flex-direction: row;
			justify-content: flex-end;
			align-items: center;
			gap: 20px;
		}
		
		.input-span-container > p {
			font-size: 18px;
		}
		
		.input-span-container > input,
		.input-span-container > select {
			box-sizing: border-box;
			margin: 0;
			padding: 0;
			padding-left: 10px;
			width: 400px;
			height: 40px;
			border: 0;
			border-bottom: 1px solid rgb(200, 200, 200);
			outline: none;
			font-family: 'Abel', sans-serif;
			font-size: 18px;
		}


		#user-input-button {
			margin-top: 30px;
			width: 400px;
			padding: 5px 0;
			background-color: var(--theme-color);
			color: white;
			font-family: 'Abel', sans-serif;
			font-size: 17px;
			border: 1px solid rgb(200, 200, 200);
			border-radius: 5px;
			cursor: pointer;
			transition: all 0.3s;
		}

		#user-input-button:hover {
			background-color: rgb(244, 148, 148);
		}


		

		/* output-section */

		#output-section {
			margin: 0 auto 50px auto;
			margin-top: 50px;
			box-sizing: border-box;
			width: 900px;
			height: auto;
			display: flex;
			justify-content: center;
			align-items: flex-start;
			flex-direction: row;
			flex-wrap: wrap;
			gap: 50px;
		}
		
		
		/* output-section */

	</style>
	
	<style>
		
		/* output card css */
		
		.card-output-profiles * {
			box-sizing: border-box;
			margin: 0;
			padding: 0;
		}

		.card-output-profiles {
			user-select: none;
			margin: 0;
			font-family: Abel, Arial, Verdana, sans-serif;
			width: 400px;
			height: 700px;
			background-color: #fff;
			background: linear-gradient(#f8f8f8, #fff);
			box-shadow: 0 8px 16px -8px rgba(0, 0, 0, 0.4);
			border-radius: 6px;
			overflow: hidden;
			position: relative;
		}

		.card-output-profiles .general {
			width: 100%;
			height: 100%;
			position: relative;
			top: 0;
			right: 0;
			z-index: 1;
			padding: 1rem;
			padding-top: 0;
		}

		.card-output-profiles .general .card-general-image {
			margin: 0;
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			border-radius: 6px;
			object-fit: cover;
		}
		
		.card-output-profiles .general .additional {
			margin: 0;
			position: absolute;
			top: 0;
			left: 0;
			width: 0px;
			height: 100%;
			background-color: rgb(0, 0, 0, 0.4);
			border-radius: 6px;
			transition: width 0.6s;
		}

		.card-output-profiles:hover .general .additional {
			width: 100%;
		}
		
		.card-output-profiles .general .card-general-name {
			margin: 0;
			position: absolute;
			bottom: 10px;
			left: 0;
			max-width: 400px;
			height: 40px;
			padding: 0 10px;
			font-size: 28px;
			color: white;
			background-color: rgb(0, 0, 0, 0.6);
			cursor: pointer;
			transition: all 0.6s;
		}
		
		.card-output-profiles:hover .general .card-general-name {
			left: 10px;
			bottom: 210px;
			background-color: transparent;
			font-size: 32px;
		}
		
		.card-output-profiles .general .message-icon {
			-webkit-appearance: none;
			position: absolute;
			right: 10px;
			bottom: 210px;
			font-size: 22px;
			border: none;
			color: rgb(255, 255, 255, 0);
			background-color: transparent;
			outline: none;
			cursor: pointer;
			transition: all 0.6s;
		}
		
		.card-output-profiles:hover .general .message-icon {
			right: 20px;
			color: white;
		}
		
		.card-output-profiles .general .card-general-user-details {
			
			margin: 0;
			position: absolute;
			bottom: 10px;
			left: -600px;
			max-width: 400px;
			height: 200px;
			padding: 20px;
			display: flex;
			flex-direction: column;
			justify-content: center;
			gap: 20px;
			color: rgb(255, 255, 255, 0);
			background-color: transparent;
			transition: all 0.6s;
		}
		
		.card-output-profiles:hover .general .card-general-user-details {
			left: 0;
			color: white;
			background-color: transparent;
		}
		
		.card-output-profiles .general .card-general-profession {
			font-size: 18px;
		}
		
		.card-output-profiles .general .card-general-bio {
			font-size: 22px;
		}
		
		/* output card css */

	</style>
</head>

<body>

	<%@ include file="navbar.html" %>

	<section id="user-section">
		<h1 id="user-section-title">Search For Friends</h1>
		<div id="input-for-user">
			<div class="input-span-container">
				<p class="input-healper-text">Distance(k.m.) : </p>
				<input type="number" min="0" step="0.1" id="choose-distance" placeholder="Distance (km)" value="10">
			</div>
			
			<div class="input-span-container">
				<p class="input-healper-text">Gender : </p>
				<select name="gender" id="select-gender">
					<option value="all">All</option>
					<option value="male">Male</option>
					<option value="female">Female</option>
				</select>
			</div>
			
			<div class="input-span-container">
				<p class="input-healper-text">Profession : </p>
				<select name="profession" id="select-profession">
					<option value="all">All</option>
					<option value="teacher">Teacher</option>
					<option value="artist">Artist</option>
					<option value="engineer">Engineer</option>
					<option value="doctor">Doctor</option>
					<option value="student">Student</option>
				</select>
			</div>
			
			<button id="user-input-button">SUBMIT</button>

		</div>
	</section>

	<section id="output-section">

	</section>
	

</body>

<script>

	document.getElementById("user-input-button").addEventListener("click", fetchAndPopulate);
	

	function fetchAndPopulate() {
		
		console.log("button pressed");

		let userid = "<%= (String)request.getAttribute("userid") %>";

		let distance = document.getElementById("choose-distance").value;

		let selectProfession = document.getElementById("select-profession");
		let profession = selectProfession.options[selectProfession.selectedIndex].value;

		let selectGender = document.getElementById("select-gender");
		let gender = selectGender.options[selectGender.selectedIndex].value;

		console.log(userid, distance, profession, gender);

		getData();

		async function getData() {

			console.log("getData called");

			try {

				console.log("Fetch Process Initiated...");

				let res = await fetch("http://localhost:8080/findNearest/getOtherUsers", {
					"headers": {
						"content-type": "application/x-www-form-urlencoded"
					},
					"body": `userid=\${userid}&distance=\${distance}&profession=\${profession}&gender=\${gender}`,
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

					if (userid === outList[i].userid) continue;

					let profileDivision = document.createElement("div");
					profileDivision.className = "card";

					profileDivision.innerHTML = `<div class="card-output-profiles">
													<div class="general">
														<img class="card-general-image" src="https://images.pexels.com/photos/3754200/pexels-photo-3754200.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
														<div class="additional"></div>
														<p class="card-general-name">\${outList[i].name}</p>
														<form id="startChat" action="chatUI.jsp" method="post">
															<input type="hidden" name="userid" value="\${ userid }">
															<input type="hidden" name="receiverid" value="\${outList[i].userid}">
															<input type="submit" class="material-icons message-icon" value="chat">
														</form>
														<div class="card-general-user-details">
															<p class="card-general-profession">\${outList[i].profession}</p>
															<p class="card-general-bio">This enables him to accompany an Padma Vibhushan Dr. M. Balamuralikrishna with the same verve and e.</p>
														</div>
													</div>
												</div>`;
						
						
						
						
						
						
						`<div class="additional">
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