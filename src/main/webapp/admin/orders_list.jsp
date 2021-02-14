<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Orders- Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Orders Management</h2>
		<!-- <h3>
			<a href="new_book">Create new Book</a>
		</h3> -->

	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align="center">
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Book copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${orders}" var="order" varStatus="status">

				<c:url value="edit_order" var="editLink">
					<c:param name="id" value="${order.orderId}" />
				</c:url>
				<c:url value="delete_link" var="deleteLink">
					<c:param name="id" value="order.orderId"></c:param>
				</c:url>

				<c:url value="view_order" var="viewLink">
					<c:param name="id" value="${order.orderId}" />
				</c:url>
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<!-- important -->
					<td>${order.customer.fullname}</td>
					<!-- This value is returned from a transient getter in BookOrder entity -->
					<td>${order.bookCopies}</td>
					<td><fmt:setLocale value="en_US" />
							 <fmt:formatNumber
							value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td>
						<a href="${viewLink}">Details</a>&nbsp;
					 <a href="${editLink}">Edit</a>&nbsp; 
					<a href="javascript:void(0)"
						class="deleteLink" id="${order.orderId}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
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
																orderId = $(this)
																		.attr(
																				"id");
																if (confirm('Are you sure you want to delete order with ID '
																		+ orderId
																		+ '?')) {
																	window.location = 'delete_order?id='
																			+ orderId;
																}
															});
										});
					});
</script>






</html>