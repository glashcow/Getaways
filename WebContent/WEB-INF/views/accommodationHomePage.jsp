<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	font-family: roboto
}
</style>
<meta charset="ISO-8859-1">
<title>Accommodation Home Page</title>
</head>
<body>
	<h1>Accommodation Home Page</h1>
	<h3>
		HELLO
		<c:out value="${accountUser.userName}">
		</c:out>
	</h3>
	<a href="http://localhost:8088/getaways/addAccommodation">Add Accommodation</a>
	<br>
	<br>
	<a href="http://localhost:8088/getaways/">Logout</a>
</body>
</html>