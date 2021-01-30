<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order Details - Evergreen Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<!--  for security : the order is null if the orderDAO -->
	<c:if test = "${order == null}" > 
		<h2 class="pageheading" align = "center">Sorry , you are not authorized to view this order!</h2>
	</c:if>
	
	<c:if test = "${order != null }" >  
	<div align="center">
		<h2 class="pageheading">Your Order ID: ${order.orderId}</h2>
	</div>

		<!-- order overview section 1 -->
		<div align="center">

			<table border="1">

				<tr>
					<td><b>Order Status:</b></td>
					<td>${order.status}</td>
				</tr>

				<tr>
					<td><b>Order Date:</b></td>
					<td>${order.orderDate}</td>
				</tr>

				<tr>
					<td><b>Quantity:</b></td>
					<td>${order.bookCopies}</td>
				</tr>

				<tr>
					<td><b>Total Amount:</b></td>
					<td>${order.total}</td>
				</tr>

				<tr>
					<td><b>Recipient Name:</b></td>
					<td>${order.recipientName}</td>
				</tr>

				<tr>
					<td><b>Recipient Phone:</b></td>
					<td>${order.recipientPhone}</td>
				</tr>

				<tr>
					<td><b>Ship to :</b></td>
					<td>${order.shippingAddress}</td>
				</tr>

				<tr>
					<td><b>Payment Method:</b></td>
					<td>${order.paymentMethod}</td>
				</tr>

			</table>
		</div>

		<!-- list of books in the order:  section 2 -->

		<div align="center">
			<h2>Ordered Books:</h2>
			<table border="1">
				<tr>
					<th>Index</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Subtotal</th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetail"
					varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td><img style="vertical-align: middle;"
							src="data:image/jpg;base64,${orderDetail.book.base64Image}"
							width="48" height="64" /> ${orderDetail.book.title}
						</td>
						<td>${orderDetail.book.author}</td>
						<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
								value="${orderDetail.book.price}" type="currency" /></td>
						<td>${orderDetail.quantity}</td>
						<td>${orderDetail.subtotal}</td>

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
		</c:if>
	<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>