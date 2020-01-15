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
<title>Registration</title>
</head>
<body>
	<h1>Create an Account</h1>
	<form:form action="${pageContext.request.contextPath}/signedUp"
		method="POST" modelAttribute="accountUser">
		<form:label path="firstName">First Name :</form:label>
		<form:input path="firstName" /><br><br>
		<form:label path="lastName">Last Name:</form:label>
		<form:input path="lastName"/><br><br>
		<form:label path="userName">Username:</form:label>
		<form:input path="userName" /><br><br>
		<form:label path="password">Password:</form:label>
		<form:input path="password" type="password" /><br><br>
		<form:button name="submit" value="submit" type="submit">Submit</form:button>
	</form:form>
	<br>
	<a href="http://localhost:8088/getaways/companyRegister">Register as a company</a>
</body>
</html>
