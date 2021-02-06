<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<br> <br>
		<h2>New Books:</h2>
		<div align="center" style="width: 80%; margin: 0 auto;">
			<c:forEach var="book" items="${newBooks}">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<!--  list of best selling books -->

		<br> <br>
		<div align="center" style="clear: both;">
			<h2>Best-selling Books:</h2>
			<div align="center" style="width: 80%; margin: 0 auto;">
				<c:forEach var="book" items="${bestSellingBooks}">
					<jsp:directive.include file="book_group.jsp" />
				</c:forEach>
			</div>
		</div>

		<!--  list of most fovored books -->
		<br> <br>
		<div align="center" style="clear: both;">
			<h2>Most-Popular Books</h2>
			<div align="center" style="width: 80%; margin: 0 auto;">
				<c:forEach var="book" items="${mostFavoredBooks}">
					<jsp:directive.include file="book_group.jsp" />
				</c:forEach>
			</div>
		</div>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>