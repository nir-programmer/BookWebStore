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
	<h4><i>${message }</i></h4>
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
			
			<!--  setup a link to update a user -->
			<c:url value="edit_user" var="tempLink">
				<c:param name="id" value="${user.userId}"></c:param>
			</c:url>
			
			<!--  setup a link to delete a user -->
			<c:url value="delete_user" var="deleteLink">
				<c:param name="id" value="${user.userId}"></c:param>
			</c:url>
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
					<%-- Replace this <a href="edit_user?id=${user.userId}">Edit</a> 
						by url rewriting and encoding: --%>
						<a href="${tempLink}">Edit</a>&nbsp;
						
						<a href="javascript:confirmDelete(${user.userId})">Delete</a>
						
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function confirmDelete(userId) 
	{
		if(confirm('Are you sure you want to delete user with ID '+ userId + '?'))
		{
			window.location = 'delete_user?id='+userId; 
		}
		
	}

</script>



</html>