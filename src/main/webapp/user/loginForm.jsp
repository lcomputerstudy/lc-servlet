<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lc-servlet Login Form</title>
</head>
<body>
<p>login test <%=request.getAttribute("user") %></p>

<div>
	<p>dao test (insert, select)</p>
	<ul>
		<c:forEach var="user" items="${list}" varStatus="status">
			<li>${user}</li>
		</c:forEach>
	</ul>
</div>
</body>
</html>