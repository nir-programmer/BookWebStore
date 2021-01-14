<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books in ${category.name} - Online Books Store</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>


	<!-- <div align="center"> -->
	<div class="center">
		<h2>${category.name}</h2>
	</div>

	<div align="center" style="width: 80%; margin: 0 auto;">
		<c:forEach var="book" items="${books}">
		<div style="float: left; display: inline-block; margin: 0 auto;" >	
			<div>
				<a href="view_book?id=${book.bookId}">
					<img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164" />
				</a>
			</div>
			<div>
				<a href="view_book?id=${book.bookId}">
					<b>${book.title}</b>
				</a>
			</div>
	
			<div> 
				
				<c:forTokens items="${book.ratingStars}" delims="," var="star">
					<c:if test="${star eq 'on'}"> 
						<img src="images/rating_on.png" />
					 </c:if>
					 
					 <c:if test="${star eq 'half'}"> 
						<img src="images/rating_half.png" />
					 </c:if>
					 
					 <c:if test="${star eq 'off'}"> 
						<img src="images/rating_off.png" />
					 </c:if>
					 
				</c:forTokens>
			</div>
			<div><b>${book.author} </b></div>
			
			<div><b>${book.price} </b></div>

		</div>
		</c:forEach>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>