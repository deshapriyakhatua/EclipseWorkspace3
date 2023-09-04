<%@page import="objects.LoginTokenValidationResponse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	LoginTokenValidationResponse loginTokenValidationResponse = (LoginTokenValidationResponse) request.getAttribute("LoginTokenValidationResponse");
	out.println(loginTokenValidationResponse.getMessage());
	out.print(loginTokenValidationResponse.getDestinationURL());
	%>
	<form action="login" method="post">
		<input type="hidden" name="getDestinationURL" value="<%= loginTokenValidationResponse.getDestinationURL() %>" />
		<input name="email" id="email" type="email" placeholder="Email" >
		<input name="password" id="password" type="password" placeholder="Password" >
		<input name="submit" id="submit" type="submit" placeholder="SUBMIT" >
	</form>
</body>
</html>