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
<title>Travel Agents needing approval</title>
</head>
<body>
	<h1>Companies needing approval</h1>
	<br>
	<a href="http://localhost:8088/getaways/adminHomePage">Home</a>
	<table>
		<tr>
			<c:forEach items="${unapprovedaccounts}" var="company">
				<tr>
					<td><c:out value="${company.firstName}"></c:out></td>
					<td><a
						href="${pageContext.request.contextPath}/companiesApproved?id=${company.id}">Approve</a></td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>