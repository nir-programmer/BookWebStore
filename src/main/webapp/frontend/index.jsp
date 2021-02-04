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

				<c:url value="view_book" var="viewBookLink">
					<c:param name="id" value="${book.bookId}"></c:param>
				</c:url>

				<div style="display: inline-block; margin: 0 auto;">
					<div>
						<%--  NO JSTL: <a href="view_book?id=${book.bookId}"> --%>
						<a href="${viewBookLink}"> <img
							src="data:image/jpg;base64,${book.base64Image}"
							width="128 height=" 164" />
						</a>
					</div>
					<div>
						<a href="${viewBookLink}"><b>${book.title} </b> </a>
					</div>
					<div>
						<%-- <jsp:include page="book_rating.jsp"></jsp:include> --%>
						<c:forTokens items="${book.ratingStars}" delims="," var="star">
							<c:if test="${star eq 'on'}">
								<img src="images/rating_on.png" />
							</c:if>
							<c:if test="${star eq 'half' }">
								<img src="images/rating_half.png" />
							</c:if>
							<c:if test="${star eq 'off' }">
								<img src="images/rating_off.png" />
							</c:if>
						</c:forTokens>
					</div>


					<div>
						<b>${book.author} </b>
					</div>

					<div>
						<b>${book.price} </b>
					</div>

				</div>
			</c:forEach>
		</div>
		
		<!--  list of best selling books -->
		
		<br> <br>
		<div align="center" style="clear: both;">
			<h2>Best-selling Books:</h2>
			<div align="center" style="width: 80%; margin: 0 auto;">
			<c:forEach var="book" items="${bestSellingBooks}">

				<c:url value="view_book" var="viewBookLink">
					<c:param name="id" value="${book.bookId}"></c:param>
				</c:url>

				<div style="display: inline-block; margin: 0 auto;">
					<div>
						<%--  NO JSTL: <a href="view_book?id=${book.bookId}"> --%>
						<a href="${viewBookLink}"> <img
							src="data:image/jpg;base64,${book.base64Image}"
							width="128 height=" 164" />
						</a>
					</div>
					<div>
						<a href="${viewBookLink}"><b>${book.title} </b> </a>
					</div>
					<div>
						<%-- <jsp:include page="book_rating.jsp"></jsp:include> --%>
						<c:forTokens items="${book.ratingStars}" delims="," var="star">
							<c:if test="${star eq 'on'}">
								<img src="images/rating_on.png" />
							</c:if>
							<c:if test="${star eq 'half' }">
								<img src="images/rating_half.png" />
							</c:if>
							<c:if test="${star eq 'off' }">
								<img src="images/rating_off.png" />
							</c:if>
						</c:forTokens>
					</div>


					<div>
						<b>${book.author} </b>
					</div>

					<div>
						<b>${book.price} </b>
					</div>

				</div>
			</c:forEach>
		</div>
		</div>

		<div align="center" style="clear: both;">
			<h2>Most-Popular Books</h2>
		</div>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>