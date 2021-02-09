<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Customer</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="../css/style.css">

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null}">
			Edit Customer
		</c:if>
			<c:if test="${customer == null}"> 
			Create New Customer
		</c:if>
		</h2>
	</div>

	<div align="center">

		<c:if test="${customer != null}">
			<form action="update_customer" method="post" id="customerForm">
				<input type="hidden" name="customerId"
					value="${customer.customerId}">
		</c:if>

		<c:if test="${customer == null }">
			<form action="create_customer" method="post" id="customerForm">
		</c:if>

		<form action="create_customer" method="post">
			<table class="form">
				<tr>
					<td align="right">E-mail Address:</td>
					<td align="left"><input type="text" id="email" name="email"
						value="${customer.email}" size="45"></td>
				</tr>

				<%-- CHANGE FOR PAYPAL
					<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName"
						id="fullName" size="45" value="${customer.fullname}"></td>
				</tr> --%>
				
				<tr>
					<td align="right">First Name:</td>
					<td align="left">
						<input type="text" name="firstname"
							id="firstname" size="45" value="${customer.firstname}">
					</td>
				</tr>
				
				<tr>
					<td align="right">Last Name:</td>
					<td align="left">
						<input type="text" name="lastname"
							id="lastname" size="45" value="${customer.last}">
					</td>
				</tr>
				
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45" value="${customer.password}"></td>
				</tr>

				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirmPassword"
						name="confirmPassword" value="${customer.password}" size="45"></td>
				</tr>

				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						size="45" value="${customer.phone}"></td>
				</tr>

				<%-- CAHNGED FOR PAYPAY
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" value="${customer.address}" size="45"></td>
				</tr> --%>
				
				<tr>
					<td align="right">Address Line 1:</td>
					<td align="left">
					<input type="text" id="address1"
						name="address1" value="${customer.addressLine1}" size="45">
					</td>
				</tr>
				
				<tr>
					<td align="right">Address Line 2:</td>
					<td align="left">
					<input type="text" id="address2"
						name="address2" value="${customer.addressLine2}" size="45">
					</td>
				</tr>

				<tr>
					<td align="right">City:</td>
					<td align="left"><input id="city" name="city" type="text"
						value="${customer.city}" size="45"></td>
				</tr>
				
				<tr>
					<td align="right">State:</td>
					<td align="left">
					<input id="state" name="state" type="text"
						value="${customer.state}" size="45"></td>
				</tr>

				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input id="zipCode" name="zipCode"
						type="text" value="${customer.zipcode}" size="45"></td>
				</tr>

				<%--Updated for PayPal!
				 <tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" value="${customer.country}" size="45"></td>
				</tr> --%>

				<tr>
					<td align="right">Country:</td>
					<td align="left">
						<select  id="country" name="country" >
						<c:forEach items="${mapCountries}" var="country">
							<!-- country.key is the country code.country.value is the country name  -->
							<option value="${country.value}">${country.key}</option>
						
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>


					<td colspan="2" align="center">
						<button type="submit" value=Save">Save</button>&nbsp;&nbsp;&nbsp;
						<button onclick="javascript:history.go(-1);" value="Cancel">Cancel</button>
						<!-- <button id="cancelButton" value="Cancel">Cancel</button> -->
					</td>

				</tr>
			</table>
		</form>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#customerForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				firstname : "required",
				lastname : "required",
				password : "required",
				confirmPassword : "required",
				phone : "required",
				address1 : "required",
				address2 : "required",
				city : "required",
				state : "required",
				country : "required",
				zipCode : "required",
			/* city: "required",
			country: "requried",
			zipCode: "required", */
			/* phone: "required",
			address: "required",
			city: "required",
			country: "required", 
			zipCode: "reuired", */

			},
			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"
				},
				firstname : "Please enter first name",
				lastname : "Please enter last name",
				password : "Please enter password",
				confirmPassword : "Please Confirm password",
				phone : "Please enter a phone number",
				address1 : "Please enter an address line1",
				address2 : "Please enter an address line2",
				city : "Please enter a city",
				state : "Please enter a state",
				country : "Please enter a country",
				zipCode : "Please enter a zip code",
			

			}
		});

		//# refer to an id attribute
		$("#cancelButton").click(function() {
			history.go(-1);
		});

	});
</script>






</html>