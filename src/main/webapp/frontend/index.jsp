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
				<%-- <c:url value="${view_book}" var="viewBookLink">
					<c:param name="${id}" var="id"></c:param>
				 </c:url> --%>
				<div style="display: inline-block; margin: 0 auto;">
					<div>
						<a href="bookDetailsLink"> <img
							src="data:image/jpg;base64,${book.base64Image}" width="128 height="164" />
						</a>
					</div>
					<div>
						<a href="bookDetailsLink"><b>${book.title} </b> </a>
					</div>
					<div>
						<b>***** </b>
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
		<div align="center" style="clear: both;">
		<h2>Best-selling Books:</h2>
		</div>
		
		<div align="center" style="clear: both;"> 
			<h2>Most-Popular Books</h2>
		</div>
		
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>