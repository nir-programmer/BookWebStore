<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit My Profile</title>

<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/style.css">

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>

	<div align="center">
		<form action="update_profile" method="post" id="customerForm">
			<table class="form">
				<tr>
					<td align="right">E-mail Address:</td>
					<td align="left"><b>${loggedCustomer.email }</b>(cannot be change)</td>
				</tr>

				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName"
						id="fullName" size="45" value="${loggedCustomer.fullname}"></td>
				</tr>


				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						size="45" value="${loggedCustomer.phone}"></td>
				</tr>

				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" value="${loggedCustomer.address}" size="45"></td>
				</tr>

				<tr>
					<td align="right">City:</td>
					<td align="left"><input id="city" name="city" type="text"
						value="${loggedCustomer.city}" size="45"></td>
				</tr>

				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input id="zipCode" name="zipCode"
						type="text" value="${loggedCustomer.zipcode}" size="45"></td>
				</tr>

				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" value="${loggedCustomer.country}" size="45"></td>
				</tr>

				<tr><td colspan="2" align="center"><i>(Leave password fields blank if you 
				don't want to change password)</i></td>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45"></td>
				</tr>

				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirmPassword"
						name="confirmPassword" size="45"></td>
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
				fullName : "required",
				confirmPassword:{
					equalTo: "#password"
				},
				//password : "required",
				//confirmPassword : "required",
				phone : "required",
				address : "required",
				city : "required",
				country : "required",
				zipCode : "required",
		

			},
			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"
				},
				fullName : "Please enter full name",
				password : "Please enter password",
				confirmPassword : {
					equalTo: "Confirm password does not match password!"
				},
				phone : "Please enter a phone number",
				address : "Please enter an address",
				city : "Please enter a city",
				country : "Please enter a country",
				zipCode : "Please enter a zip code",
			/* city: "Please enter a city",
			country: "Please enter a coutnry", */
			//zipCode: "Please enter a zip code",
			/* phone: "Please enter a phone number", 
			address: "Please enter an address", 
			city: "Please enter a city",
			country: "Please enter a country",
			zipCode: "Please enter a zip code", */

			}
		});

		//# refer to an id attribute
		$("#cancelButton").click(function() {
			history.go(-1);
		});

	});
</script>






</html>