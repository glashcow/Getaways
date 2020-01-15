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
	
	<div class="form-error">${message}</div>
	
	<form:form action="${pageContext.request.contextPath}/LoginSubmit"
		method="POST" modelAttribute="accountUser">
		<form:label path="userName">Username:</form:label>
		<form:input path="userName" name="userName" /><br><br>
		<form:label path="password">Password:</form:label>
		<form:input path="password" name="password" type="password" /><br><br>
		<form:button name="submit" value="submit" type="submit">Submit</form:button>
	</form:form>
</body>
</html>
