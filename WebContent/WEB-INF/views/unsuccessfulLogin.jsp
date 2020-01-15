<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {font-family: roboto}
</style>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<form:form action="${pageContext.request.contextPath}/homePage"
		method="POST" modelAttribute="accountUser">
		<form:label path="userName">Username:</form:label>
		<form:input path="userName" /><br><br>
		<form:label path="password">Password:</form:label>
		<form:input path="password" type="password" /><br><br>
		<form:button name="submit" value="submit" type="submit">Submit</form:button><br>
		<form:label path="">Incorrect username/or password, please try again</form:label>
	</form:form>
</body>
</html>
