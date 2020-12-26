<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results for ${keyword} - Online Book Store</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<h1 align="center">Results for: ${keyword}</h1>


	<div align="center">
		<c:forEach var="book" items="${result}">
			<div>${book.title}</div>
		</c:forEach>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script>
	$(document)
			.ready(
					function() {
						$(".deleteLink")
								.each(
										function() {
											$(this)
													.on(
															"click",
															function() {
																bookId = $(this)
																		.attr(
																				"id");
																if (confirm('Are you sure you want to delete book with ID '
																		+ bookId
																		+ '?')) {
																	window.location = 'delete_book?id='
																			+ bookId;
																}
															});
										});
					});
</script>






</html>