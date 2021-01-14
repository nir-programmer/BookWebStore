<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results for ${keyword} - Online Book Store</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>


	<div align="center">
		<c:if test="${fn:length(result) == 0 }">
			<h2>No Results for "${keyword }"</h2>
		</c:if>

		<c:if test="${fn:length(result) >  0 }">
			<center><h2>Results for "${keyword}":</h2> </center>
			<!--  algin = left will set each sub div on the left  -->
			<div align="left" style="width: 80%; margin: 0 auto;">
				<c:forEach var="book" items="${result}">
					<div>
					<div style="display: inline-block; margin: 20px; width: 10%;" >
						<div align="left">
							<a href="view_book?id=${book.bookId}"> 
							<img src="data:image/jpg;base64,${book.base64Image}"
								width="128" height=" 164" />
							</a>
						</div>
					</div>
					<div style="display: inline-block; margin: 20px; vertical-align: top ; width: 60%;" align="left">
						<div>
							<h2><a href="view_book?id=${book.bookId }"><b>${fn:substring(book.title, 0 , 50)} </b> </a></h2>
						</div>
						<!-- Assignment 19
						<div>Rating ***** </div> 
						-->
						<div>
							<c:forTokens items="${book.ratingStars }" delims="," var="star">
								<c:if test="${star eq 'on' }"> 
									<img src="images/rating_on.png">
								 </c:if>
								 
								 <c:if test="${star eq 'half' }"> 
									<img src="images/rating_half.png">
								 </c:if>
								 
								 <c:if test="${star eq 'off' }"> 
									<img src="images/rating_off.png">
								 </c:if>
							
							</c:forTokens>
						</div>
						
						
						<div>
							<i>by ${book.author}  </i>
						</div>
						<div>
							<p>${fn:substring(book.description, 0 , 100)}... </p>
						</div>
					</div>
					<div style="display: inline-block; margin: 20px; vertical-align: top ;" >
							<h3><b>$${book.price }</b></h3>
							<h3><a href="">Add To Cart </a> </h3>
					</div>
				</div>
				</c:forEach>
			</div>
		</c:if>

	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>