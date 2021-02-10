<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register as a Customer</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2 class="pageheading">Register as a Customer</h2>
	</div>

	<div align="center">
		<form action="register_customer" method="post">
		<!-- INCLUDE OK -->
		<jsp:directive.include file="../common/customer_form.jsp" />
		</form>


	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>

<!-- DOES NOT WORK!!! -->
<script type="text/javascript" src="js/customer-form.js"> </script>

</html>