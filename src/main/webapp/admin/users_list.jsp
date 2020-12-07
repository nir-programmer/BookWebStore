<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage User- Evergreen Bookstore Administration</title>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Users Management</h2>
		<h3>
			<a href="users_form.jsp">Create new User</a>
		</h3>

	</div>

	<div align="center">
		<table border="1" cellpadding="5" >
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Action</th>
			</tr>

			<c:forEach items="${users}" var="user" varStatus="status">
				<tr>
					<td>${status.index}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="">Edit</a> &nbsp;
						<a href="">Delete</a> &nbsp;
					</td>
				</tr>
			</c:forEach>


		</table>



	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>