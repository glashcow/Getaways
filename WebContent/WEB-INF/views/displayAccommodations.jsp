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
<title>Display All Accommodations</title>
</head>
<body>
	<h2>All Accommodations</h2>
	<a href="${pageContext.request.contextPath}/customerHomePage">Home</a>
	<br>
	<br>
	<table>
		<tr>
			<form:form
				action="${pageContext.request.contextPath}/displayAccommodationsInLocation"
				method="POST" modelAttribute="accommodations">
				<form:select path="accommodationLocation" id="accommodationName">
					<form:options items="${locations}" itemLabel="locationName" />
				</form:select>
				<form:button name="submit" value="submit">Submit</form:button>
			</form:form>
		</tr>
	</table>
	<table>
		<c:forEach items="${allAccommodations}" var="accommodation">
			<tr>
				<td align="center"><c:out
						value="${accommodation.accommodationType}"></c:out></td>
				<td align="center"><c:out
						value="${accommodation.accommodationName}"></c:out></td>
			</tr>
			<tr>
				<td align="center"><c:out
						value="${accommodation.accommodationLocation}"></c:out></td>
				<td align="center"><p>
						Room capacity:
						<c:out value="${accommodation.roomCapacity}"></c:out>
					</p></td>
			</tr>
			<tr>
				<td align="center"><p>Available from:</p></td>
				<td align="center"><p>Until:</p></td>
			</tr>
			<tr>
				<td align="center"><c:out
						value="${accommodation.availableStartDate}"></c:out></td>
				<td align="center"><c:out
						value="${accommodation.availableEndDate}"></c:out></td>
			</tr>
			<tr>
				<td align="center"><p>
						£
						<c:out value="${accommodation.accommodationPrice}"></c:out>
					</p></td>
			</tr>

			<tr>

				<td><a href="${pageContext.request.contextPath}/addAccommodationToBasket?id=${accommodation.accommodationId}">Add To Basket</a></td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>