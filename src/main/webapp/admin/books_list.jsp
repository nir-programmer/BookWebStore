<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Book- Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Books Management</h2>
		<h3>
			<a href="new_book">Create new Book</a>
		</h3>

	</div>
	
	<c:if test="${message != null}"> 
		<div align="center" >
			<h4 class ="message">${message}</h4>
		</div>
	</c:if>	
	<div align="center">
		<table border="1">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${books}" var="book" varStatus="status">
			
			<c:url value="edit_book" var="editLink">
				<c:param name="id" value="${book.bookId}" />
			</c:url>
			<c:url value="delete_link" var="deleteLink"> 
				<c:param name="id" value="book.bookId"></c:param>
			</c:url>
				<tr> 
					<td>${status.index + 1} </td>
					<td>${book.bookId}</td>
					<!-- important -->
					<td>
						<img src="data:image/jpg;base64,${book.base64Image}"  width="84" height="110" />
					</td>
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name }</td>
					<td>${book.price }</td>
					<td>${book.lastUpdateTime}</td>
					<td>
						<a href="${editLink}">Edit</a>&nbsp;
						
						<a href="javascript:void(0)" class="deleteLink" id="${book.bookId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script>
	$(document).ready(function(){
		$(".deleteLink").each(function()
			{
			$(this).on("click", function()
				{
				userId = $(this).attr("id");
				if(confirm('Are you sure you want to delete user with ID '+ userId + '?'))
				{
					window.location = 'delete_user?id=' +  userId;
				} 
			});
		});
	}); 
	
</script>
<!-- <script type="text/javascript">
	function confirmDelete(userId) 
	{
		if(confirm('Are you sure you want to delete user with ID '+ userId + '?'))
		{
			window.location = 'delete_user?id='+userId; 
		}
		
	}

</script> -->



</html>