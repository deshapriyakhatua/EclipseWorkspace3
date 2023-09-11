<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
body {
	background-color: rgb(255, 255, 255);
}

#user-section {
	width: 100%;
	height: auto;
	display: flex;
	flex-wrap: wrap;
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

#profile-division {
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

#profile-top, #profile-bottom {
	width: 100%;
	height: 50%;
}

#profile-top {
	display: flex;
	justify-content: center;
	align-items: center;
}

#name-phone {
	height: 100%;
	width: 80%;
}

#profile-image {
	height: 100%;
	width: 20%;
	display: flex;
	justify-content: center;
	align-items: center;
}

#name-phone p {
	margin: 1px;
	padding-left: 10px;
	padding-top: 5px;
}

#image-icon {
	margin: 0;
	text-align: center;
	font-size: 60px;
	border: 0px solid red;
}

#profile-bottom p {
	margin: 1px;
	padding-left: 10px;
	padding-top: 5px;
}


</style>

</head>

<body>
	<%@ include file="card.html" %>
	<section id="user-section">
		<div id="user-profile">
			<div id="profile-division">
				<div id="profile-top">
					<div id="name-phone">
						<p id="name">Name: -</p>
						<p id="phone">Phone: -</p>
						<p id="profession">Profession: -</p>
					</div>
					<div id="profile-image">
						<p id="image-icon"></p>
						<!-- 👩‍💼👨‍💼 -->
					</div>
				</div>
				<div id="profile-bottom">
					<p id="gender">Gender: -</p>
					<p id="address">Address: -</p>
					<p id="email">Email: -</p>
				</div>
			</div>
		</div>
	</section>

	<section id="register">
		<a href="UpdateUserDetails.jsp"><button id="registerButton"
				type="submit">Update profile</button></a> <a href="logout"><button
				id="logoutButton" type="submit">Logout</button></a>
	</section>


</body>


<script>
	let user = {};

	getUserDetails();

	async function getUserDetails() {

		console.log("getUserDetails called");

		try {

			console.log("Fetch Process Initiated...");

			let res = await
			fetch("http://localhost:8080/findNearest/getUserDetails", {
				"headers" : {
					"content-type" : "application/x-www-form-urlencoded"
				},
				"body" : "userid=<%= (String)request.getAttribute("userid") %>",
				"method" : "POST"
			});

			let data = await
			res.text();
			console.log(typeof data);
			user = JSON.parse(data);
			console.log("Users : " + user.name);

			populateUser();

		} catch (err) {
			console.log(err);
		}

	}

	function populateUser() {

		document.getElementById("name").innerText = "Name: " + user.name;
		document.getElementById("phone").innerText = "Phone: " + user.phone;
		document.getElementById("profession").innerText = "Profession: "
				+ user.profession;
		document.getElementById("gender").innerText = "Gender: " + user.gender;
		document.getElementById("address").innerText = "Adderss: "
				+ user.address;
		document.getElementById("email").innerText = "Email: " + user.email;

	}
</script>

</html>
