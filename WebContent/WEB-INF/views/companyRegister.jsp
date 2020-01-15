<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	font-family: roboto
}
</style>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<h1>Create an Account</h1>
	<form:form action="${pageContext.request.contextPath}/pendingRegestration"
		method="POST" modelAttribute="accountUser">
		<form:label path="firstName">Company Name :</form:label>
		<form:input path="firstName" />
		<br>
		<br>
		<form:label path="userName">Username:</form:label>
		<form:input path="userName" />
		<br>
		<br>
		<form:label path="password">Password:</form:label>
		<form:input path="password" type="password" />
		<br>
		<br>
		<form:select path="companyType" id="companyType">
			<form:options items="${usertypes}" itemLabel="companyType" />
		</form:select>
		<form:button name="submit" value="submit">Submit</form:button>
	</form:form>
</body>
</html>
