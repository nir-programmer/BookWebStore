<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>
			You have made payment successfully. Thank you for purchasing!
		</h2>
		
		<h3>
			Your Payment Receipt
		</h3>
		
		<h2>Seller Information</h2>
		<table>
			<tr> 
				<td><b>E-mail: </b></td>
				<td>sales@evergreenbooks.com</td>
			</tr>
			
			<tr> 
				<td><b>Phone Number: </b> </td>
				<td> +972 123 456 789 </td>
			</tr>
		</table>
		
		<!-- Section 2 : Buyer Information -->
		<h2>Buyer Information</h2>
		<table>
		
		<tr> 
				<td><b>First Name: </b></td>
				<td>${payer.firstName }</td>
			</tr>
			
			<tr> 
				<td><b>Last Name: </b></td>
				<td>${payer.lastName }</td>
			</tr>
			
			<tr> 
				<td><b>E-mail: </b></td>
				<td>${payer.email }</td>
			</tr>
			
		</table>
		
		
		<!-- Section 3 : Order Details information -->
		<h2>Order Details</h2>
		<table>
			<tr>
				<td><b>OrderId</b> </td>
				<!-- from the session which was set by the ExecutePaymentSErvlet-->
				<td>${orderId }</td>
			</tr>
			
			<tr>
				<td><b>Ordered By: </b> </td>
				<td>${loggedCustomer.fullname}</td>
			</tr>
			
			<tr>
				<td><b>Transaction Description </b> </td>
				<td>${transaction.description}</td>
			</tr>
			
			<tr>
				<td colspan="2"><b>Items</b></td>
				
			</tr>
			
			<tr> 
				<td colspan="2"> 
					<table >
			<tr>
				<td><b>Description: </b></td>
				<td>${transaction.description}</td>
			</tr>

			<tr>
				<td colspan="2"><b>Items List: </b></td>
			</tr>

			<tr>
				<td colspan="2">
					<table border="1">
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
						</tr>
						<c:forEach items="${transaction.itemList.items}" var="item"
							varStatus="status">
							<tr>
								<td>${status.index  + 1}</td>
								<td>${item.name }</td>
								<td>${item.quantity}</td>
								<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${item.price}" type="currency" /></td>
								<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${item.price * item.quantity}" type="currency" /></td>
							</tr>
						</c:forEach>

						<!--  3.2 Amount information -->
						<tr>
							<td colspan="5" align="right">
								<p>
									<b>Subtotal: </b>
									<fmt:setLocale value="en_US" />
									<fmt:formatNumber
										value="${transaction.amount.details.subtotal}" type="currency" />
								</p>

								<p>
									<b>Tax: </b>
									<fmt:setLocale value="en_US" />
									<fmt:formatNumber value="${transaction.amount.details.tax}"
										type="currency" />
								</p>

								<p>
									<b>Shipping Fee: </b>
									<fmt:setLocale value="en_US" />
									<fmt:formatNumber value="${transaction.amount.details.shipping}"
										type="currency" />
								</p>
								
								<p>
									<b>TOTAL: </b>
									<fmt:setLocale value="en_US" />
									<fmt:formatNumber value="${transaction.amount.total}"
										type="currency" />
								</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
				
				</td>
			</tr>
			
			
		</table>
		
	</div>
</body>



</html>