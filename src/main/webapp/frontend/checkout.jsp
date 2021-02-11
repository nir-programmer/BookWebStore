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
				<h2>
					Review Your Order Details <a href="view_cart">Edit</a>
				</h2>
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
							<td><img src="data:image/jpg;base64,${item.key.base64Image}"
								width="84" height="110" /></td>

							<%-- <td id="book-title"> ${item.key.title}</td> --%>
							<td><span id="book-title">${item.key.title} </span></td>

							<td>${item.key.author}</td>
							<!-- IMPORTANT fmt JSTL -->
							<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
									value="${item.key.price}" type="currency" /></td>

							<td><input type="text" name="quantity${status.index + 1}"
								value="${item.value}" size="5" readonly="readonly" /></td>

							<td><fmt:formatNumber value="${item.value * item.key.price}"
									type="currency"></fmt:formatNumber></td>
						</tr>
					</c:forEach>
					<tr>

						<%-- <td><b>${cart.totalQuantity}&nbsp;book(s)</b></td>
							<td><b>TOTAL:</b></td> --%>

						<!-- FOR PAYPAL -->
						<td colspan="7" align="right">
							<p>
								Number of copies: <b>${cart.totalQuantity} </b>
							</p>
							<p>
								Subtotal: <b><fmt:formatNumber value="${cart.totalAmount}"
										type="currency" /></b>
							</p>

							<p>
								Tax: <b><fmt:formatNumber value="${tax}" type="currency" /></b>
							</p>
							<p>
								Shipping Fee: <b><fmt:formatNumber value="${shippingFee}"
										type="currency" /></b>
							</p>
							<p>
								TOTAL: <b><fmt:formatNumber value="${total}" type="currency" /></b>
							</p>

						</td>

					</tr>
				</table>

				<!-- section 2: Shipping details AFTER UPDATE WITH PAYPAL! -->

				<h2>Recipient Information</h2>
				<!-- relative URL of the Servlet that handles Submission of the this form  -->

				<form id="orderForm" action="place_order" method="post">
					<table class="normal">
						<tr>
							<td>First Name:</td>
							<!-- important : default value form the session -->
							<td><input type="text" name="firstname"
								value="${loggedCustomer.firstname}" /></td>
						</tr>

						<tr>
							<td>Last Name:</td>
							<!-- important : default value form the session -->
							<td><input type="text" name="lastname"
								value="${loggedCustomer.lastname}" /></td>
						</tr>

						<tr>
							<td>Phone:</td>
							<td><input type="text" name="phone"
								value="${loggedCustomer.phone}" /></td>
						</tr>

						<tr>
							<td>Address Line 1:</td>
							<td><input type="text" name="address1"
								value="${loggedCustomer.addressLine1}" /></td>
						</tr>

						<tr>
							<td>Address Line 2:</td>
							<td><input type="text" name="address2"
								value="${loggedCustomer.addressLine2}" /></td>
						</tr>

						<tr>
							<td>City:</td>
							<td><input type="text" name="city"
								value="${loggedCustomer.city}" /></td>
						</tr>

						<tr>
							<td>State:</td>
							<td><input type="text" name="state"
								value="${loggedCustomer.state}" /></td>
						</tr>

						<tr>
							<td>Zip Code:</td>
							<td><input type="text" name="zipcode"
								value="${loggedCustomer.zipcode}" /></td>
						</tr>


						<tr>
							<td align="right">Country:</td>
							<td align="left"><select id="country" name="country">
									<c:forEach items="${mapCountries}" var="country">
										<!-- country.key is the country name.country.value is the country code -->
										<option value="${country.value}"
											<c:if test = '${loggedCustomer.country eq country.value}'>selected='selected'</c:if>>${country.key}
										</option>

									</c:forEach>
							</select></td>
						</tr>
						
					</table>

					<!-- Section 3: PayPal include optoin in the drop down list of payments -->
					<div>
						<h2>Payment</h2>
						Choose your payment method: &nbsp; &nbsp; &nbsp; <select
							name="paymentMethod">
							<option value="Cash On Delievery">Cash On Delievery</option>
							<option value="paypal">PayPal or Credit card</option>
						</select>
					</div>

					<div>
						<table class="normal">
							<tr>
								<td></td>
								<td><button type="submit">Place Order</button></td>
								<td><a href="${pageContext.request.contextPath}/">Continue
										Shopping</a>
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
				rules : {
					firstname : "required",
					lastname : "required",
					phone : "required",
					address1 : "required",
					address2 : "required",
					city : "required",
					state : "required",
					zipcode : "required",
					country : "required",
				},
				messages : {
					firstname : "Please enter first name",
					lastname : "Please enter last name",
					phone : "Please enter phone number",
					address1 : "Please enter address line 1",
					address2 : "Please enter address line 2",
					city : "Please enter city",
					state: "Please enter state",
					zipcode : "Please enter zip code",
					country : "Please enter country",
				}
			});
		});
	</script>
</body>



</html>