<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details Form - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<!-- <script type="text/javascript" src="../js/jquery.validate.min.js"></script> -->
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>


	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<c:if test="${message == null}">


		<!-- Section 1: order overview form-->
		<form action="update_order" method="post" id="orderForm">
			<div align="center">
				<table border="1">
					<tr>
						<td><b>Ordered By:</b></td>
						<td>${order.customer.fullname}</td>
					</tr>

					<tr>
						<td><b>Order Date:</b></td>
						<td>${order.orderDate}</td>
					</tr>

					<tr>
						<td><b>Recipient Name:</b></td>
						<td><input type="text" size="45" name="recipientName"
							value="${order.recipientName}"></td>
					</tr>


					<tr>
						<td><b>Recipient Phone:</b></td>
						<td><input type="text" name="recipientPhone" size="45"
							value="${order.recipientPhone}"></td>
					</tr>

					<tr>
						<td><b>Ship to :</b></td>
						<td><input type="text" size="45" name="shippingAddress"
							value="${order.shippingAddress}"></td>
					</tr>


					<tr>
						<td><b>Payment Method:</b></td>
						<td><select name="paymentMethod">
								<option value="Cash on delivery">Cash on delivery</option>
						</select></td>
					</tr>

					<%--This will be recalculated...
					 <tr>
						<td><b>Total Amount:</b></td>
						<td>${order.total}</td>
					</tr> --%>

					<tr>
						<td><b>Order Status:</b></td>
						<td><select name="status">
								<option value="Processing">Processing</option>
								<option value="Shipping">Shipping</option>
								<option value="Delivered">Delivered</option>
								<option value="Completed">Completed</option>
								<option value="Canceled">Canceled</option>

						</select></td>
					</tr>

				</table>
			</div>


			<!-- Section 2: list of books in the order -->

			<div align="center">
				<h2>Ordered Books:</h2>
				<div>
					<table border="1">
						<tr>
							<th>Index</th>
							<th>Book Title</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Subtotal</th>
							<th></th>
						</tr>
						<c:forEach items="${order.orderDetails}" var="orderDetail"
							varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<!--  put both title and book image in this cell -->
								<td>${orderDetail.book.title}</td>
								<td>${orderDetail.book.author}</td>
								<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${orderDetail.book.price}" type="currency" /></td>
								<td><input type="text" size="5" name="quantity"
									value="${orderDetail.quantity}"></td>
								<td>${orderDetail.subtotal}</td>
								<td><b><a href="">Remove</a> </b></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="4" align="right"><b><i>TOTAL:</i></b></td>
							<td><b>${order.bookCopies}</b></td>

							<td><fmt:setLocale value="en_US" /> <b><fmt:formatNumber
										value="${order.total}" type="currency" /></b></td>

						</tr>
					</table>
				</div>
				
				<!-- Section 3: Buttons: Save, Cancel(in the form scope) -->
				<div align="center">
					<br /> <b><a href="">Add Books</a> </b>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="Save" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="Cancel" />
				</div>
			</div>
		</form>
	</c:if>

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