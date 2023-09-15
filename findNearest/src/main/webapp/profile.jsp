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

		.profile-main-child {
			padding: 50px 0;
			display: flex;
			justify-content: space-evenly;
			align-items: center;
			gap: 50px;
			flex-direction: column;
		}

		.profile-main-child:nth-child(1) {
			width: 900px;
			border-bottom: 1px solid rgb(0, 0, 0, 0.4);
		}

		.profile-main-child:nth-child(2) {
			width: 450px;
		}

		.profile-main-child:nth-child(3) {
			width: 442px;
		}

		.profile-main-child > * {
			width: 80%;
		}
		
		.profile-main-child > a {
			width: 10px;
			align-self: center;
			cursor: pointer;
		}
		
		.devider {
			width: 70%;
			border-bottom: 1px solid rgb(148, 148, 148);
		}
		
		#profile-images-div {
			position: relative;
			width: calc(16*50px);
			height: calc(9*50px);
		}
		
		#profile-cover {
			width: 100%;
			height: 100%;
			object-fit: cover;
		}
		
		#profile-picture {
			position: absolute;
			right: 10px;
			bottom: -100px;
			border-radius: 50%;
			border: 5px solid white;
			width: 200px;
			height: 200px;
			object-fit: contain;
		}
		
		#person-name-desc {
			position: absolute;
			left: 10px;
			bottom: -100px;
			height: 100px;
			display: flex;
			justify-content: center;
			align-items: flex-start;
			flex-direction: column;
		}

		#person-name {
		}

		#person-profession {
			font-size: 18px;
		}

		/* input type range */
		
			/*********** Baseline, reset styles ***********/
		.profile-main-child:nth-child(2) input[type="range"] {
			-webkit-appearance: none;
			appearance: none;
			background: transparent;
			cursor: pointer;
			width: 320px;
			outline: none;
		}

		/* Removes default focus */
		.profile-main-child:nth-child(2) input[type="range"]:focus {
			outline: none;
		}

		/******** Chrome, Safari, Opera and Edge Chromium styles ********/
		/* slider track */
		.profile-main-child:nth-child(2) input[type="range"]::-webkit-slider-runnable-track {
			background-color: rgb(192, 192, 192);
			border-radius: 0rem;
			height: 0.5rem;
		}

		/* slider thumb */
		.profile-main-child:nth-child(2) input[type="range"]::-webkit-slider-thumb {
			-webkit-appearance: none;
			/* Override default look */
			appearance: none;
			margin-top: 0px;
			/* Centers thumb on the track */
			background-color: var(--theme-color);
			border-radius: 0rem;
			height: 0.5rem;
			width: 1rem;
		}

		/*********** Baseline, reset styles ***********/
		.profile-main-child:nth-child(3) input[type="range"] {
			-webkit-appearance: none;
			appearance: none;
			background: transparent;
			cursor: pointer;
			width: 320px;
			outline: none;
			overflow: hidden;
			height: 1rem;
		}

		/* Removes default focus */
		.profile-main-child:nth-child(3) input[type="range"]:focus {
			outline: none;
		}

		/******** Chrome, Safari, Opera and Edge Chromium styles ********/
		/* slider track */
		.profile-main-child:nth-child(3) input[type="range"]::-webkit-slider-runnable-track {
			background-color: rgb(192, 192, 192);
			border-radius: 0rem;
		}

		/* slider thumb */
		.profile-main-child:nth-child(3) input[type="range"]::-webkit-slider-thumb {
			-webkit-appearance: none;
			/* Override default look */
			appearance: none;
			margin-top: 0px;
			/* Centers thumb on the track */
			background-color: #808080;
			box-shadow: -160px 160px 0px 160px var(--theme-color);
			border-radius: 0rem;
			height: 0.5rem;
			width: 0rem;
		}


		datalist {
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			width: 320px;
			color: rgb(93, 93, 93);
		}

		option {
			padding: 0;
		}

		/* input type range */
		
		#favourite-brands-child {
			padding: 10px;
			width: 350px;
			display: flex;
			justify-content: start;
			align-content: flex-start;
			flex-wrap: wrap;
			gap: 10px;
		}
		
		#favourite-brands-child > img {
			width: 50px;
			height: 50px;
			object-fit: contain;
		}
	</style>

</head>

<body>

	<%@ include file="navbar.html" %>
	
	<section class="profile-main-section">
		<div class="profile-main-child">
			<div id="profile-images-div">
				<img src="https://as2.ftcdn.net/v2/jpg/06/35/55/01/1000_F_635550122_dAcUqkOyrUVkURNB5iFwVFJ7fYjD66SC.jpg"
				alt="profile-cover" id="profile-cover">
				<img src="https://qph.cf2.quoracdn.net/main-thumb-1746939484-200-midqdhghptoxpixijadhvkpopddvdedi.jpeg"
				alt="profile-picture" id="profile-picture">
				<div id="person-name-desc">
					<h1 id="person-name">John Sinha</h1>
					<p id="person-profession">Softwear Designer</p>
				</div>
			</div>
			
			<a href="UpdateUserDetails.jsp"><i class="material-icons edit-icon" >edit</i></a>
			<div class="devider"></div>
			<p>Here we have two images and we want them to fill the width of 50% of the browser window and 100% of the
				height.</p>
			<div class="devider"></div>
			<div id="personal-details">
				<p id="person-age"><b>Age:</b> 32</p>
				<p id="person-status"><b>Status:</b> Single</p>
				<p id="person-archetype"><b>Archetype:</b> Frequent Flyer</p>
				<p id="person-location"><b>Location:</b> London</p>
			</div>
		</div>
		<div class="profile-main-child">
			<div class="profile-section-card">
				<h1>Personality</h1>
				<div>
					<datalist id="markers">
						<option value="0" label="Introvert"></option>
						<option value="100" label="Extrovert"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="20">
					
				</div>
				<div>
					<datalist id="markers">
						<option value="0" label="Active"></option>
						<option value="100" label="Passive"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="40">
					
				</div>
				<div>
					<datalist id="markers">
						<option value="0" label="Analytical"></option>
						<option value="100" label="Creative"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="28">
					
				</div>
			</div>
			<div class="devider"></div>
			<div class="profile-section-card">
				<h1>Political Interest</h1>
				<div>
					<datalist id="markers">
						<option value="0" label="Left"></option>
						<option value="50" label="None"></option>
						<option value="100" label="Right"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="40">
					
				</div>
			</div>
		</div>
		<div class="profile-main-child">
			<div class="profile-section-card">
				<h1>Personality</h1>
				<div>
					<datalist id="markers">
						<option value="0" label="Introvert"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="28">
				</div>
				<div>
					<datalist id="markers">
						<option value="0" label="Active"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="38">
				</div>
				<div>
					<datalist id="markers">
						<option value="0" label="Analytical"></option>
					</datalist>
					<input type="range" id="vol" list="markers" name="vol" min="0" max="50" value="10">
				</div>
			</div>
			<div class="devider"></div>
			<div class="profile-section-card" id="favourite-brands-card">
				<h1>Favourite Brands</h1>
				<div id="favourite-brands-child">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
					<img src="https://seeklogo.com/images/N/nike-logo-47A65A59D5-seeklogo.com.png">
				</div>
			</div>
		</div>
	</section>
</body>

<script type="text/javascript" src="code.jquery.com_jquery-3.7.1.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
	//getting user details
	 $.post("http://localhost:8080/findNearest/getUserDetails",{userid:"<%= (String)request.getAttribute("userid") %>"}, function(res){
		 
		 console.log(res);
		 $("#person-name").text(res.name);
		 $("#person-profession").text(res.profession);
		 $("#person-location").html("<b>Location:</b> " + res.address);
		 
	 });
	
	});

</script>

</html>