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
<title>Add Flight</title>
</head>
<body>
	<h1>Add a Flight</h1>
	<a href="http://localhost:8088/getaways/airlineHomePage">Home</a>
	<br>
	<br>
	<form:form action="${pageContext.request.contextPath}/flightSubmit"
		method="POST" modelAttribute="flight">
		<form:label path="flightName">Flight Name:</form:label>
		<form:input path="flightName" type="text"></form:input>
		<br>
		<br>Start Location:
		<form:select path="startLocation" id="startLocation">
			<form:options items="${airports}" itemLabel="fullName" />
		</form:select>
		<br>
		<br>End Location:
		<form:select path="endLocation" id="endLocation">
			<form:options items="${airports}" itemLabel="fullName" />
		</form:select>
		<br>
		<br>
		<form:label path="dateOfDeparture">Date of Departure:</form:label>
		<form:input path="dateOfDeparture" type="date" id="dateOfDeparture" name="dateOfDeparture"></form:input>
		<br>
		<br>
		
		<form:label path="departureTime">Departure Time:</form:label>
		<form:input path="departureTime" type="datetime-local" name="departureTime"></form:input>
		<br>
		<br>
		<form:label path="arrivalTime">Arrival time:</form:label>
		<form:input path="arrivalTime" type="datetime-local" name="arrivalTime"></form:input>
		<br>
		<br>
		<form:label path="flightPrice">Price:</form:label>
		<form:input path="flightPrice" type="number"></form:input>
		<br>
		<br>
		<form:label path="flightCapacity">Capacity:</form:label>
		<form:input path="flightCapacity" type="number"></form:input>
		<br>
		<br>
		<form:button type="submit" name="submit" value="submit">Submit</form:button>
	</form:form>
</body>
</html>