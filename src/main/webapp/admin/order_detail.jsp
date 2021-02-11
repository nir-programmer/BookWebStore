<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<!-- <script type="text/javascript" src="../js/jquery.validate.min.js"></script> -->
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Details of Order ID ${order.orderId}</h2>
	</div>


	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<!-- Common section for back end and front end -->
	<jsp:directive.include file="../common/common_order_detail.jsp" />


	

	<!-- Section 3: hyper links : edit order , delete order -->
	<div align="center">
		<br /> <br /> <a href="edit_order?id=${order.orderId}">Edit this
			Order</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="">Delete
			this Order</a>
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