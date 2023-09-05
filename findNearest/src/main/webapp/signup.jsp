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
	System.out.println("sigin.jsp started running...");
	%>
	<form action="signup" method="post">
		<input name="email" id="email" type="email" placeholder="Email">
		<input name="password" id="password" type="password"
			placeholder="Password"> <input name="submit" id="submit"
			type="submit" placeholder="SUBMIT">
	</form>
	<a href="login.jsp"><button >Log in</button></a>
</body>
</html>