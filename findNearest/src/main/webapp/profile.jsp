<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Abel&display=swap" rel="stylesheet">
	
	<style>
		
		* {
			padding:0;
			margin: 0;
			box-sizing: border-box;
		}
		
		body {
			font-family: 'Abel', sans-serif;
		}

		.profile-main-section {
			--theme-color: #f44177;
			margin: auto;
			width: 900px;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			gap: 5px;
			border: 0px solid red;
		}

		.profile-main-child-images {
			width: 900px;
			padding: 50px 0;
			display: flex;
			justify-content: space-evenly;
			align-items: center;
			gap: 50px;
			flex-direction: column;
		}
		
		.devider {
			width: 30%;
			border-bottom: 1px solid rgb(148, 148, 148);
		}
		
		#profile-images-div {
			position: relative;
			width: calc(16*56px);
			height: 600px;
			border: 0px solid red;
		}
		
		#profile-cover {
			width: calc(16*56px);;
			height: calc(9*56px);
			object-fit: cover;
			border: 0px solid red;
		}
		
		#profile-picture {
			position: absolute;
			right: 10px;
			bottom: 0;
			border-radius: 50%;
			border: 5px solid white;
			width: 200px;
			height: 200px;
			object-fit: cover;
		}
		
		#person-name-desc {
			position: absolute;
			left: 10px;
			bottom: 0;
			height: 100px;
			display: flex;
			justify-content: center;
			align-items: flex-start;
			flex-direction: column;
		}

		.profile-main-child-posts {
			width: 850px;
			border: 0px solid red;
		}
		
		#profile-main-child-posts-title {
			font-size: 18px;
		}
		
		#profile-main-child-posts-devider {
			margin-top: 10px;
			border-bottom: 1px solid rgb(148, 148, 148);
		}
		
		#profile-main-child-posts-container {
		
			max-width: 850px;
			margin: 50px 0;
			display: grid;
			grid-gap: 50px;
			/* fit as many columns as possible, 180px wide each: */
			grid-template-columns: repeat(auto-fill, 400px);
			/* each row is 20px high -- we always span 2+ */
			grid-auto-rows: minmax(0, auto);
			justify-content: center;
		}
		
		.profile-main-child-posts-container-child-0 { grid-row-end: span 2; }
		.profile-main-child-posts-container-child-1 { grid-row-end: span 3; }
		.profile-main-child-posts-container-child-2 { grid-row-end: span 4; }
		.profile-main-child-posts-container-child-3 { grid-row-end: span 5; }
		.profile-main-child-posts-container-child-4 { grid-row-end: span 6; }
		.profile-main-child-posts-container-child-5 { grid-row-end: span 7; }
		.profile-main-child-posts-container-child-6 { grid-row-end: span 8; }
		.profile-main-child-posts-container-child-7 { grid-row-end: span 9; }
		
		#profile-main-child-posts-container > div {
			width: 400px;
			height: auto;
			background-color: rgb(200, 200, 200);
		}
		
		.profile-main-child-posts-container-child-img {
			width: 400px;
			height: 100%;
			object-fit: cover;
		}
		
	</style>

</head>

<body>

	<%@ include file="navbar.html" %>
	
	<section class="profile-main-section">
		<div class="profile-main-child-images">
			<div id="profile-images-div">
				<img src="https://images.unsplash.com/photo-1568585105565-e372998a195d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
				alt="profile-cover" id="profile-cover">
				<img src="https://media.istockphoto.com/id/1219848059/photo/stylishly-dressed-indian-bearded-man-walks-down-the-street.jpg?s=2048x2048&w=is&k=20&c=G2M0-2stQDMD3kHvvmn0ZuDwvGRGnIys2Pzr-zLE3hQ="
				alt="profile-picture" id="profile-picture">
				<div id="person-name-desc">
					<h1 id="person-name">John Sinha</h1>
					<p id="person-profession">Softwear Designer</p>
				</div>
			</div>
			<div class="devider"></div>
			<p>Here we have two images and we want them to fill the width of 50% of the browser window and 100% of the
				height.</p>
			<div class="devider"></div>
		</div>
		
		<div class="profile-main-child-posts">
			<p id="profile-main-child-posts-title">Photos </p>
			<div id="profile-main-child-posts-devider"></div>
			<div id="profile-main-child-posts-container">
				
			</div>
		</div>
	</section>
</body>

<script type="text/javascript" src="code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	
	// appending photos
	let images = ["https://images.pexels.com/photos/2379005/pexels-photo-2379005.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/2865210/pexels-photo-2865210.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3754159/pexels-photo-3754159.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3754200/pexels-photo-3754200.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/4664514/pexels-photo-4664514.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3512336/pexels-photo-3512336.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3055773/pexels-photo-3055773.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3051652/pexels-photo-3051652.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/3214776/pexels-photo-3214776.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/2221879/pexels-photo-2221879.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
		"https://images.pexels.com/photos/1633578/pexels-photo-1633578.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.unsplash.com/photo-1568585105565-e372998a195d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
		"https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1480&q=80",
		"https://images.pexels.com/photos/1633559/pexels-photo-1633559.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://media.istockphoto.com/id/1219848059/photo/stylishly-dressed-indian-bearded-man-walks-down-the-street.jpg?s=2048x2048&w=is&k=20&c=G2M0-2stQDMD3kHvvmn0ZuDwvGRGnIys2Pzr-zLE3hQ=",
		"https://images.pexels.com/photos/1893556/pexels-photo-1893556.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/1199957/pexels-photo-1199957.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/1199958/pexels-photo-1199958.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/1633495/pexels-photo-1633495.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/327158/pexels-photo-327158.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/2983099/pexels-photo-2983099.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/1633526/pexels-photo-1633526.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/2983103/pexels-photo-2983103.jpeg?auto=compress&cs=tinysrgb&w=1600",
		"https://images.pexels.com/photos/1199959/pexels-photo-1199959.jpeg?auto=compress&cs=tinysrgb&w=1600"];
	
	const divContainer = document.getElementById("profile-main-child-posts-container");
	
	for(let i of images){
		
		const divContainerChild = document.createElement("div");
		const img = new Image();
		img.className = "profile-main-child-posts-container-child-img";
		img.src = i;
		
		img.onload = function() {
			
			console.log(this.width + 'x' + this.height);
			console.log(400 + 'x' + Math.floor((this.height*400)/this.width));
			divContainerChild.className = "profile-main-child-posts-container-child-" + Math.floor(this.height/100);
		  
		}
		
		
		
		divContainerChild.append(img);
		divContainer.append(divContainerChild);
		
	}
	
	
	// gettig user data
	$(document).ready(function(){
		
	//getting user details
	 $.post("http://localhost:8080/findNearest/getUserDetails",{userid:"<%= (String)request.getAttribute("userid") %>"}, function(res){
		 
		 console.log(res);
		 $("#person-name").text(res.name);
		 $("#person-profession").text(res.profession);
		 
	 });
	
	});

</script>

</html>