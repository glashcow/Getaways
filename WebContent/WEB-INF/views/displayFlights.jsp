<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display All Flights</title>
<style>
.slidecontainer {
	width: 20%;
}

.slider {
	-webkit-appearance: none;
	width: 100%;
	height: 15px;
	border-radius: 5px;
	background: purple;
	outline: none;
	opacity: 0.7;
	-webkit-transition: .2s;
	transition: opacity .2s;
}

.slider:hover {
	opacity: 1;
}

.slider::-webkit-slider-thumb {
	-webkit-appearance: none;
	appearance: none;
	width: 18px;
	height: 18px;
	border-radius: 50%;
	background: #e3e3e3;
	cursor: pointer;
}

.slider::-moz-range-thumb {
	width: 18px;
	height: 18px;
	border-radius: 50%;
	background: #e3e3e3;
	cursor: pointer;
}
</style>
</head>
<body>
	<h2>All Flights</h2>
	<a style="float:right" href="${pageContext.request.contextPath}/displayBasket">Check Basket</a>
	<br>
	<a style="float:right" href="${pageContext.request.contextPath}/customerHomePage">Home</a>


	<form:form action="${pageContext.request.contextPath}/displayAirports"
		method="POST" modelAttribute="flights">
		<form:select path="startLocation" id="airportName">
			<form:options items="${airports}" itemLabel="fullName" />
		</form:select>
		<form:select path="endLocation" id="airportName">
			<form:options items="${airports}" itemLabel="fullName" />
		</form:select>
		<form:button name="submit" value="submit">Submit</form:button>
	</form:form>

	<form action="${pageContext.request.contextPath}/displayFlights"
		method="POST">
		Select Date: <input type="date" name="datePicker"> <input
			type="submit">
	</form>
	<form
		action="${pageContext.request.contextPath}/displayFlightsFilteredByPrice"
		method="POST" oninput="selectedPrice.value=parseInt(price.value)">
		<div class="slidecontainer">
			<p>
				costs less than:
				<output name="selectedPrice" for="price"></output>
			</p>
			<input type="range" min="1" max="100" value="50" class="slider"
				id="price" name="selectPrice"> <input type="submit">
		</div>
	</form>

	<table>
		<c:forEach items="${allFlights}" var="flight">
			<tr>
				<td align="center"><c:out
						value="${flight.startLocation.abbreviation}"></c:out></td>
				<td align="center"><p>&rarr;</p></td>
				<td align="center"><c:out
						value="${flight.endLocation.abbreviation}"></c:out></td>
				<td align="center"><p>

						<c:out value="${flight.flightPrice}"></c:out>
					</p></td>
			</tr>
			<tr>
				<td align="center"></td>
				<td align="center"><p></p></td>
				<td align="center"><fmt:formatDate
						value="${flight.departureTime}" pattern="HH:mm" /></td>
				<td align="center"><fmt:formatDate
						value="${flight.arrivalTime}" pattern="HH:mm" /></td>
			</tr>
			<td><label>Flight Capacity</label></td>
			<td><c:out value="${flight.flightCapacity}"></c:out></td>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/addFlightToBasket?id=${flight.flightId}">Add
						to Basket</a></td>
			</tr>
			<input id="basketId" name="basketId" type="hidden" value="1">
		</c:forEach>
	</table>
</body>
</html>