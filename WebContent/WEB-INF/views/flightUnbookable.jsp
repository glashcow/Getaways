<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Unbookable</title>
</head>
<body>
	<H2>This flight can't be booked due to it leaving within 3 hours from now!</H2>
	<a href="${pageContext.request.contextPath}/displayFlights">Go back to available flights</a>
</body>
</html>