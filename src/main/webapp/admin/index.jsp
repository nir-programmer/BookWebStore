<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
	
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>

	<div align="center">
		<hr width="60%" />
		<h2 class="pageheading">Quick Actions:</h2>
		<br> 
			<a href="new_book">New Book</a> &nbsp;
			 <a href="users_form.jsp">New User</a> &nbsp; 
			 <a href="categories_form.jsp">NewCategory</a> &nbsp; 
			 <a href="customer_form.jsp">New Customer</a> &nbsp; <br>
	</div>
	
	<hr width="60%" />
	<div align="center">
		<h2 class="pageheading">Recent Sales:</h2>
	</div>
	<hr width="60%" />
	<div align="center">
		<h2 class="pageheading">Recent Reviews:</h2>
	</div>

	<hr width="60%" />
	<div align="center">
		<h2 class="pageheading">Statistics:</h2>
	</div>

	<hr width="60%" />
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>