<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Basket</title>
</head>
<body>
	<h2>Inside Your Basket</h2>
	<table>
		<c:forEach items="${basketFlights}" var="basket">
			 <tr>
				<td><p>Flight:<p></td>
				<td><c:out value="${basket.flightName}"></c:out></td>
				<td><c:out value="${basket.startLocation}"></c:out></td>
				<td><p>&rarr;</p></td>
				<td><c:out value="${basket.endLocation}"></c:out></td>
				<td><c:out value="${basket.departureTime}"></c:out></td>
				
			</tr>	 	
		
		</c:forEach>
		<c:forEach items="${basketAccom}" var="basket">
		<tr>
			<td>Accommodation: </td>
		<tr>	
			<td><p>Accommodation:</p></td>
			<td><c:out value="${basket.accommodationName}"></c:out></td>
			<td><c:out value="${basket.accommodationLocation}"></c:out></td>
		</tr>
		</c:forEach>
		
		<tr>
			<td><label>Total Price:</label></td>
		<td><c:out value="${totalPrice}"></c:out></td>
		</tr>
	</table>
	<form action="${pageContext.request.contextPath}/orderReview">
		<input type="submit" value="Checkout">
	</form>
	<form action="${pageContext.request.contextPath}/customerHomePage">
		<input type="submit" value="Back To Home">
	</form>



</body>
</html>