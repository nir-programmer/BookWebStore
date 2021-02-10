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
			<jsp:directive.include file="../common/customer_form.jsp" />
			
		</form>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


<script type="text/javascript" src="../js/customer-form.js"> </script>
<!-- <script type="text/javascript">
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
</script> -->






</html>