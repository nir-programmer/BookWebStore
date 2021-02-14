<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Reciept - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<!-- <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script> -->
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h3>
			<i>Please carefully review the following information before
				making payment</i>
		</h3>

		<!-- First Section: Payer Information -->
		<h2>Payer Information:</h2>
		<table>
			<tr>
				<td><b>First Name: </b>${payer.firstName}</td>
			</tr>

			<tr>
				<td><b>Last Name: </b>${payer.lastName}</td>
			</tr>

			<tr>
				<td><b>E-mail: </b>${payer.email}</td>
			</tr>
		</table>

		<!-- Second Section: Recipient Information -->

		<h2>Recipient Information:</h2>
		<table>
			<tr>
				<td><b>Recipient Name: </b>${recipient.recipientName}</td>
			</tr>

			<tr>
				<td><b>Address Line 1: </b>${recipient.line1}</td>
			</tr>

			<tr>
				<td><b>Address Line 2: </b>${recipient.line2}</td>
			</tr>

			<tr>
				<td><b>City: </b>${recipient.city}</td>
			</tr>

			<tr>
				<td><b>State: </b>${recipient.state}</td>
			</tr>

			<tr>
				<td><b>County: </b>${recipient.countryCode}</td>
			</tr>

			<tr>
				<td><b>Postal Code: </b>${recipient.postalCode}</td>
			</tr>
		</table>


		<!--3 Second Section: Recipient Information -->
		<h2>Transaction Details:</h2>

		<!-- 3.1 List of items(table) -->
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
		
		<br/>
		<div>  
			<form action="execute_payment" method="post">
				<input type="hidden" name="paymentId" value="${param.paymentId}" />
				<input type="hidden" name="PayerID" value="${param.PayerID}" />
				<input type="submit" value="Pay Now" />
			</form>
		</div>
	</div>
</body>



</html>