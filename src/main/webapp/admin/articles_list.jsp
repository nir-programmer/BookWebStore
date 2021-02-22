<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Articles- Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<!-- <script type="text/javascript" src="../js/jquery.validate.min.js"></script>  -->

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Articles Management</h2>
		<c:url value="create_article" var="createLink">
			<c:param name="id" value="${article.articleId}" />
		</c:url>

		<c:url value="edit_article" var="editLink">
			<c:param name="id" value="${article.articleId}" />
		</c:url>


		
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
				<th>Title</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${articles}" var="article" varStatus="status">
				<c:url value="edit_article" var="editLink">
					<c:param name="id" value="${article.articleId}"></c:param>
				</c:url>
				
				<tr> 
					<td>${status.index + 1  } </td>
					<td>${article.articleId}</td>
					<td>${article.title}</td>
					
					<td>
						<%-- <a href="${editLink}">Edit</a>&nbsp;  --%>
						<a href="edit_article?id=${article.articleId}">Edit</a>&nbsp; 
						<a href="javascript:void(0);" class="deleteLink" id="${article.articleId}">Delete</a> 
				
					</td>
				</tr>

			</c:forEach>
		

		</table>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
	
	
<script>
	$(document).ready(function(){
		$(".deleteLink").each(function()
			{
			$(this).on("click", function()
				{
				reviewId = $(this).attr("id");
				if(confirm('Are you sure you want to delete review with ID '+reviewId + '?'))
				{
					window.location = 'delete_review?id=' +  reviewId;
				} 
			});
		});
	}); 
	
</script>
</body>

<!-- <script type="text/javascript">
	function confirmDelete(reviewId) {
		if (confirm(`Are you sure you want to delete review with id = `
				+ reviewId + ` ?`)) {
			window.location = 'delete_reivew?id=' + reivewId;
		}

	}
</script>
 -->






</html>