<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Welcome, ${loggedCustomer.fullname}</h2>
		<table class="customer">
			<tr>
				<td><b>E-mail Address:</b></td>
				<td>${loggedCustomer.email}</td>
			</tr>

			<tr>
				<td><b>Full Name:</b></td>
				<td>${loggedCustomer.fullname}</td>
			</tr>
			
			
			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.phone}</td>
			</tr>
			
			<tr>
				<td><b>Address:</b></td>
				<td>${loggedCustomer.address}</td>
			</tr>
			
			<tr>
				<td><b>City:</b></td>
				<td>${loggedCustomer.city}</td>
			</tr>
			
			<tr>
				<td><b>Zip Code: </b></td>
				<td>${loggedCustomer.zipcode}</td>
			</tr>
			
			<tr>
				<td><b>country</b></td>
				<td>${loggedCustomer.country}</td>
			</tr>
			
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center"><b><a href="edit_profile?email=${loggedCustomer.email}">Edit My Profile</a></b></td>
				
			</tr>

		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>