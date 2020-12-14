<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Categories- Evergreen Bookstore Administration</title>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Users Management</h2>
		<h3>
			<a href="categories_form.jsp">Create new Category</a>
		</h3>

	</div>

	<div align="center">
		<h4>
			<i>${message}</i>
		</h4>
	</div>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Category Name</th>
				<th>Action</th>
			</tr>

			<c:forEach items="${categories}" var="category" varStatus="status">

				<!--  setup a link to update a user -->
				<c:url value="edit_category" var="update_Link">
					<c:param name="id" value="${category.categoryId}"></c:param>
				</c:url>

				<!--  setup a link to delete a user -->
				<c:url value="delete_category" var="deleteLink">
					<c:param name="id" value="${category.categoryId}"></c:param>
				</c:url>
				<tr>
					<td>${status.index + 1}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td><a href="${update_Link}">Edit</a> &nbsp; <a
						href="javascript:confirmDelete(${category.categoryId})">Delete</a>
					</td>

				</tr>
			</c:forEach>

		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function confirmDelete(categoryId) {
		if (confirm('Are you sure you want to delete category with ID '
				+ categoryId + '?')) {
			window.location = 'delete_category?id=' + categoryId;
		}

	}
</script>



</html>