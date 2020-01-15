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
<title>Add Accommodation</title>
</head>
<body>
	<h1>Add Accommodation</h1>
    <a href="http://localhost:8088/getaways/accommodationHomePage">Home</a>
    <br>
    <br>
	<form:form
		action="${pageContext.request.contextPath}/accommodationSubmit"
		method="POST" modelAttribute="accommodations">
		Location:
		<form:select path="accommodationLocation" id="accommodationLocation">
			<form:options items="${locations}" itemLabel="locationName" />
		</form:select>
		<br>
		<br>
	Type of Accommodation:
		<form:select path="accommodationType" id="accommodationType">
			<form:options items="${accommodation}" itemLabel="accommodationType" />
		</form:select>
		<br>
		<br>
		<form:label path="accommodationName">Accommodation Name:</form:label>
		<form:input path="accommodationName" type="text"
			name="accomodationName" />
		<br>
		<br>
		<form:label path="availableStartDate">Start Date:</form:label>
		<form:input path="availableStartDate" name="availableStartDate" type="date" />
		<br>
		<br>
		<form:label path="availableEndDate">End Date:</form:label>
		<form:input path="availableEndDate" name="availableEndDate" type="date" />
		<br>
		<br>
		<form:label path="roomCapacity">Room Capacity:</form:label>
		<form:input path="roomCapacity" type="number" />
		<br>
		<br>
		<form:label path="accommodationPrice">Price £:</form:label>
		<form:input path="accommodationPrice" min="0" step="0.01" type="number" />
		<br>
		<br>
		<form:button type="submit" name="submit" value="submit">Submit</form:button>
	</form:form>
</body>
</html>