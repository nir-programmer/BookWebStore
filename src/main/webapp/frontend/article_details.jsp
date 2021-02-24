<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title}-OnlineBooks Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<div align="center"> 
		<p> 
			${article.content}
			
		</p>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonWriteReview").click(function() {
			//redirect the browser to the URL of the WriteReviewServlet(/write_review)
			window.location = 'write_review?book_id=' + ${book.bookId};
		});
		
		$("#buttonAddToCart").click(function() {
			//redirect the browser to the URL of the WriteReviewServlet(/write_review)
			window.location = 'add_to_cart?book_id=' + ${book.bookId};
		});
	});
	</script>
</body>
</html>