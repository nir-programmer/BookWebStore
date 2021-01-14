<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Review</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<form>
			<table class="normal">
			<tr> 
				<td><h2>Your Reviews</h2></td>
				<td><h2>${loggedCustomer.fullname}</h2></td>
			</tr>
				
			
			
			</table>
		
		</form>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},

				password : "required",
			},

			messages : {
				email : {
					required : "Please enter email",
					email : "Please enter a valid email address"

				},

				password : "Please enter a password"

			}
		});
	});
</script>
</html>