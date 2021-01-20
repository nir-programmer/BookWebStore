<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">

		<h2>Your Shopping Cart</h2>

		<c:if test="${message != null }">
			<div align="center">
				<h4 class="message">${message }</h4>
			</div>
		</c:if>

		<!-- IMPORTANT! -->
		<c:set var="cart" value="${sessionScope['cart']}" />

		<c:if test="${cart.totalAmount ==  0}">
			<h2>Your Shopping cart is empty</h2>
		</c:if>

		<c:if test="${cart.totalItems > 0}">

			<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1">
						<tr>
							<th>NO</th>
							<th colspan="2">BOOK</th>
							<th>QUANTITY</th>
							<th>PRICE</th>
							<th align="center">SUBTOTAL</th>
							<th><a href=""><b>CLEAR CART</b></a></th>
						</tr>
						<c:forEach var="item" items="${cart.items}" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<!-- IMPORTANT map.. -->
								<td><img
									src="data:image/jpg;base64,${item.key.base64Image}" width="84"
									height="110" /></td>

								<%-- <td id="book-title"> ${item.key.title}</td> --%>
								<td><span id="book-title">${item.key.title} </span></td>

								<td>
									<input type="hidden" name="book_id" value="${item.key.bookId}" />
									<input type="text" name="quantity${status.index + 1}"
									size="5" value="${item.value}" />
								</td>
									<!-- IMPORTANT fmt JSTL -->
								<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${item.key.price}" type="currency" /></td>
								<td><fmt:formatNumber
										value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="remove_from_cart?book_id=${item.key.bookId}">Remove</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3"></td>
							<td>${cart.totalQuantity}&nbsp;<b>book(s)</b></td>
							<td><b>TOTAL:</b></td>
							<td colspan="2"><b><fmt:formatNumber
										value="${cart.totalAmount}" type="currency" /></b></td>

						</tr>

					</table>
				</div>
				<!-- Button and links-->
				<div>
					<table class="normal">
						<tr>
							<td></td>
							<td><button type="submit">Update</button></td>
							<td><a href="${pageContext.request.contextPath}/">Continue
									Shopping</a></td>
							<td><a href="">Checkout</a></td>
						</tr>

					</table>
				</div>


			</form>

		</c:if>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		$("#cartForm").validate({
			rules : {
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index + 1}: {
						required: true , 
						number: true, 
						min: 1
						},
				</c:forEach>
			},

			messages : {
				<c:forEach var="item" items="${cart.items}" varStatus="status">
					quantity${status.index + 1}: {
						required: "Please enter quantity" ,
						number: "Quantity must be a number", 
						min: "Quantity must be greater than 0"
						},
				</c:forEach>
			}
		});
	});
</script>
</html>