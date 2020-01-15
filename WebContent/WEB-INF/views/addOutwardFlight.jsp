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
<title>Add Package</title>
</head>
<body>
	<h1>Add Outward Flight</h1>
	<a href="http://localhost:8088/getaways/">Home</a>
	<br>
	<br>
	<table>
		<c:forEach items="${flightList}" var="flights">
			<tr>
				<c:out value="${flights.flightName}"></c:out>
			</tr>
			<tr>
				<c:out value="${flights.dateOfDeparture}"></c:out>
			</tr>
			<tr>
				<c:out value="${flights.arrivalTime}"></c:out>
			</tr>
			<a href="${pageContext.request.contextPath}/addReturnFlight?id=${flights.flightId}">Add to package</a><br>
		<!-- <a href="${pageContext.request.contextPath}/addReturnFlight?id=${flights.flightId}">Add to package</a><br>-->
	
			<br>
		</c:forEach>
	</table>
</body>
</html>