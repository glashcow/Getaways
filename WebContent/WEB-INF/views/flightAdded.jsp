<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Added</title>
</head>
<body>
<H2>${message}</H2>
<a href="${pageContext.request.contextPath}/displayFlights">Go back to available flights</a>
<br>
<a href="${pageContext.request.contextPath}/displayBasket">Go to your basket</a>
</body>
</html>