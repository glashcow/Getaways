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
<title>Approve Travel Agent</title>
</head>
<body>
	<h1>Approved Company</h1>
	<table>
	<c:forEach items="${aprrovedAgents}" var="company">
		<tr>
			<b>Approved Company With Username</b> <c:out value="${company.userName}"></c:out>
		</tr>
	</c:forEach>
	</table>
	<a href="${pageContext.request.contextPath}/companiesForApproval">Approve More Companies</a>
	<br>
</body>
</html>