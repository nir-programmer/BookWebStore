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
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

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
						<td><b>Payment Method:</b></td>
						<td>
							<select name="paymentMethod">
								<option value="Cash on delivery"
									 <c:if test="${order.paymentMethod eq 'Cash On Delivery'}">
									 	selected ='selected'
									</c:if>
								>
								Cash on delivery
								</option>
								
								<option value="paypal"
									 <c:if test="${order.paymentMethod eq 'paypal'}">
									 	selected ='selected'
									</c:if>
								>
								PayPal or Credit card
								</option>

						</select></td>
					</tr>

				
					<tr>
						<td><b>Order Status:</b></td>
						<td><select name="status">
							<option value="Processing" 
								  <c:if test="${order.status eq 'Processing' }" >  
								   selected='selected' </c:if> >Processing
							</option>
							
							<option value="Shipping" 
							        <c:if test="${order.status eq 'Shipping' }" >  
									 selected='selected' </c:if>>Shipping
							</option>
								<option value="Delivered" 
								<c:if test="${order.status eq 'Delivered' }" >  
								selected='selected' </c:if>>Delivered
							</option>
							
								<option value="Completed"
										 <c:if test="${order.status eq 'Completed' }" >  
									     selected='selected' </c:if>>Completed
								</option>
								
								<option value="Canceled"
										 <c:if test="${order.status eq 'Canceled' }" >  
									     selected='selected' </c:if>>Canceled
								</option>

						</select></td>
					</tr>

					</table>
					
					<!-- Section 2 : Recipient Information -->
					<h2>Recipient Information</h2>
					<table>
					<tr>
						<td><b>First Name:</b></td>
						<td><input type="text" size="45" name="firstname" id="firstname"
							value="${order.firstname}"></td>
					</tr>
					
					<tr>
						<td><b>Last Name:</b></td>
						<td><input type="text" size="45" name="lastname" id="lastname"
							value="${order.lastname}"></td>
					</tr>

					<tr>
						<td><b>Phone:</b></td>
						<td><input type="text" name="phone" id ="phone" size="45"
							value="${order.phone}"></td>
					</tr>

					<tr>
						<td><b>Address Line 1:</b></td>
						<td><input type="text" size="45" name="address1" id="address1"
							value="${order.addressLine1}"></td>
					</tr>
					
					<tr>
						<td><b>Address Line 2:</b></td>
						<td><input type="text" size="45" name="address2" id="address2"
							value="${order.addressLine2}"></td>
					</tr>
					
					<tr>
						<td><b>City:</b></td>
						<td><input type="text" size="45" name="city" id="city"
							value="${order.city}"></td>
					</tr>
					
					<tr>
						<td><b>State:</b></td>
						<td><input type="text" size="45" name="state" id="state"
							value="${order.state}"></td>
					</tr>
					
					<tr>
						<td><b>Zipcode:</b></td>
						<td><input type="text" size="45" name="zipcode" id="zipcode"
							value="${order.zipcode}"></td>
					</tr>
					
					
					<tr>
					<td align="right">Country:</td>
					<td align="left">
						<select  id="country" name="country" >
						<c:forEach items="${mapCountries}" var="country">
						<!-- country.key is the country name.country.value is the country code -->
						<option value="${country.value}"
							<c:if test = '${order.country eq country.value}'>
							selected='selected'</c:if>
						 >
							 ${country.key}
						</option>
						
						</c:forEach>
						</select>
					</td>
				</tr>s
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
								<td>
									<input type="hidden" name="price" value="${orderDetail.book.price}" />
									<fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${orderDetail.book.price}" type="currency" />
								</td>
								<td>
<%--IMPORTANT(form the shoppingcart): <input type="hidden" name="book_id" value="${item.key.bookId}" /> --%>
									<input type="hidden" name="bookId" value="${orderDetail.book.bookId}">
									<input type="text" size="5" name="quantity${status.index + 1 }" 
									    value="${orderDetail.quantity}">
								</td>
								<td>${orderDetail.subtotal}</td>
								<td><b><a href="remove_book_from_order?id=${orderDetail.book.bookId }">Remove</a> </b></td>
							</tr>
						</c:forEach>

				<!-- TRANSACTION INFORMATION FOR PAYPAL -->
						<tr>
						<td colspan="7" align="right">
						
						<fmt:setLocale value="en_US" /> 
							<p>
								<b>Subtotal: <fmt:formatNumber
									value="${order.subtotal}" type="currency" />
								</b>
							</p>
							<p>
								<b>Tax: 
									<input type="text" size="5" id ="tax" name="tax" value="${order.tax}"  />
								</b>
							</p>
							
							<p>
								<b>Shipping Fee: 
									<input type="text"  size = "5" id="shippingfee" 
									name="shippingfee" value="${order.shippingFee}" />
								</b>
							</p>
							
							<p>
								<b>Total: <fmt:formatNumber
									value="${order.total}" type="currency" />
								</b>
							</p>
					</td>
						
						</tr>
					</table>
				</div>

				<!-- Section 3: Buttons: Save, Cancel(in the form scope) -->
				<div align="center">
					<br />
					<a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
						value="Save" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<!--  I need to forward to the list_order.jsp !! So the history didnt work! -->
						<!-- <input	type="button" value="Cancel" onclick="javascript:history.go(-1);" /> -->
						<input	type="button" value="Cancel" 
						onclick="javascript:window.location.href='list_orders';" />
				</div>
			</div>
		</form>
	

	<jsp:include page="footer.jsp"></jsp:include>
	
	<!-- JS popup script to add book to the order -->
	<script> 
		function showAddBookPopup() 
		{
			var width = 600;
			var height = 250; 
			var left = (screen.width - width) / 2 ; 
			var top = (screen.height - height) / 2; 
			
			window.open('add_book_form', '_blank', 'width=' + width +
					', height=' + height + ', top=' + top + ', left=' + left);
		
		}
	
	//jQuery function to validate form fields
	$(document).ready(function()
	{
		$("#orderForm").validate({
			rules: 
			{
				firstname: "required",
				lastname: "required",
				phone: "required",
				address1: "required",
				address2: "required",
				city: "required",
				state: "required",
				zipcode: "required",
				country: "required",
				
			
				<c:forEach var="book" items="${order.orderDetails}" varStatus="status">
				 quantity${status.index + 1}: {
					required: true , number: true, 	min: 1
				},
				
				tax: {required: true, number: true , min: 0},
				shippingfee: {required: true, number: true , min: 0},
				
				
		</c:forEach>
				
			},
			
			messages: 
			{
				firstname: "Please enter a first name",
				lastname: "Please enter a last name",
				phone: "Please enter a phone",
				address1: "Please enter address line 1",
				address2: "Please enter address line 2",
				city: "Please enter a city",
				state: "Please enter a state",
				zipcode: "Please enter a zipcode",
				country: "Please enter a country",
				
				<c:forEach var="book" items="${order.orderDetails}" varStatus="status">
				quantity${status.index + 1}: {
					required: "Please enter quantity" ,
					number: "Quantity must be a number", 
					min: "Quantity must be equal or greater than 0"
					},
			</c:forEach>
					  
			
			shippingfee:{
				required: "Please enter shipping fee", 
				number: "Shipping fee must be a number", 
				min: "Shipping fee must be equal or greater than 0"
			},
			tax:{
				required: "Please enter tax", 
				number: "Tax must be a number", 
				min: "Tax must be greater than 0"
			} 
			
			
				
			}
		}); 
		
	}); 
	</script>
</body>
</html>