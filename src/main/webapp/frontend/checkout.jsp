<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
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
		
		
		<!--  first section: Order information(similar to shopping cart) -->
		<c:if test="${cart.totalItems > 0}">
				<div>
					<h2>Review Your Order Details <a href="view_cart">Edit</a></h2>
					<table border="1">
						<tr>
							<th>NO</th>
							<th colspan="2">BOOK</th>
							<th>Author</th>
							<th>PRICE</th>
							<th>Quantity</th>
							<th align="center">SUBTOTAL</th>
							<th></th>
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

								<td>${item.key.author}</td>
									<!-- IMPORTANT fmt JSTL -->
								<td><fmt:setLocale value="en_US" /> 
								<fmt:formatNumber value="${item.key.price}" type="currency" />
								</td>
								
								<td><input type="text" name="quantity${status.index + 1}"
									value="${item.value}"  size="5" readonly="readonly"/> 
								</td>
								
								<td>
								  <fmt:formatNumber value="${item.value * item.key.price}"
								     type="currency"></fmt:formatNumber> 
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3"></td>
							<td><b>${cart.totalQuantity}&nbsp;book(s)</b></td>
							<td><b>TOTAL:</b></td>
							<td colspan="2"><b><fmt:formatNumber
										value="${cart.totalAmount}" type="currency" /></b></td>

						</tr>
					</table>
					
					<!-- section 2: Shipping details -->
					
					<h2>Your Shipping Information</h2>
					<!-- relative URL of the Servlet that handles Submission of the this form  -->
					<form id ="orderForm" action = "place_order" method="post">
						<table class="normal">
							<tr>
								<td>Recipient Name:</td>
								<!-- important : default value form the session -->
								<td><input type="text"  name="recipientName" value="${loggedCustomer.fullname}" /></td>
							</tr>
							<tr> 
								<td>Recipient Phone:</td>
								<td><input type="text"  name="recipientPhone" value="${loggedCustomer.phone}" /></td>
							</tr>
							
							<tr> 
								<td>Street Address:</td>
								<td><input type="text"  name="address" value="${loggedCustomer.address}" /></td>
							</tr>
							
							<tr> 
								<td>City:</td>
								<td><input type="text"  name="city" value="${loggedCustomer.city}" /></td>
							</tr>
							
							<tr> 
								<td>Zip Code:</td>
								<td><input type="text"  name="zipcode" value="${loggedCustomer.zipcode}" /></td>
							</tr>
							
							<tr> 
								<td>Country:</td>
								<td><input type="text"  name="country" value="${loggedCustomer.country}" /></td>
							</tr>
						</table>
						
						<!--  drop down list of payments -->
						<div>
							<h2>Payment</h2>
							Choose your payment method: 
							&nbsp; &nbsp; &nbsp; 
							<select name="paymentMethod">
								<option value="Cash On Delievery">Cash On Delievery </option>
							</select>
						</div>
						
						<div> 
							<table class="normal">
								<tr>
									<td></td>
									<td><button type="submit" >Place Order</button></td>
									<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a>
								 </tr>
							
							
							</table>
						</div>
					</form>
				</div>
			
		</c:if>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$("#orderForm").validate({
			rules: {
				recipientName : "required",
				recipientPhone : "required",
				address : "required",
				city : "required",
				zipcode : "required",
				country :"required",
			}, 
			messages:{
				recipientName : "Please enter recipient name",
				recipientPhone : "Please enter phone number",
				address : "Please enter address",
				city :"Please enter city",
				zipcode : "Please enter zip code",
				country : "Please enter country",
			}
		});
	});
</script>
</body>



</html>