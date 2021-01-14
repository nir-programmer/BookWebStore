<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Customers- Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<!-- 
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script> 
-->
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Customers Management</h2>
		<c:url value="create_customer" var="createLink">
			<c:param name="id" value="${customer.customerId}" />
		</c:url>

		<c:url value="edit_customer" var="editLink">
			<c:param name="id" value="${customer.customerId}" />
		</c:url>


		<h3>
			<a href="customer_form.jsp">Create new Customer</a>
		</h3>

	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div align="center">
		<table border="1">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${customers}" var="customer" varStatus="status">


				<c:url value="edit_customer" var="editLink">
					<c:param name="id" value="${customer.customerId}" />
				</c:url>
				<c:url value="delete_link" var="deleteLink">
					<c:param name="id" value="customer.customerId"></c:param>
				</c:url>
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
					<td><a href="${editLink}">Edit</a>&nbsp; 
					<a href="javascript:confirmDelete(${customer.customerId})" >Delete</a>
				</tr>
			</c:forEach>

		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
					function confirmDelete(customerId)
					{
						if(confirm(`Are you sure you want to delete customer with id = ` + customerId + ` ?`))
						{
							window.location = 'delete_customer?id=' + customerId;
						}
						
					}
				
</script>
<!-- jQuery 
<script>
	$(document).ready(function(){
		$(".deleteLink").each(function()
			{
			$(this).on("click", function()
				{
				bookId = $(this).attr("id");
				if(confirm('Are you sure you want to delete book with ID '+ bookId + '?'))
				{
					window.location = 'delete_book?id=' +  bookId;
				} 
			});
		});
	}); 
	
</script> -->






</html>