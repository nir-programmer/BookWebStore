<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Reviews- Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<!-- <script type="text/javascript" src="../js/jquery.validate.min.js"></script>  -->

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Reviews Management</h2>
		<c:url value="create_customer" var="createLink">
			<c:param name="id" value="${customer.customerId}" />
		</c:url>

		<c:url value="edit_customer" var="editLink">
			<c:param name="id" value="${customer.customerId}" />
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
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${reviews }" var="review" varStatus="status">
				<c:url value="edit_review" var="editLink">
					<c:param name="id" value="${review.reviewId}"></c:param>
				</c:url>
				
				<tr> 
					<td>${status.index + 1  } </td>
					<td>${review.reviewId }</td>
					<td>${review.book.title}</td>
					<td>${review.rating } </td>
					<td>${review.headline} </td>
					<td>${review.customer.fullname }</td>
					<td>${review.reviewTime }</td>
					<td>
						<%-- <a href="${editLink}">Edit</a>&nbsp;  --%>
						<a href="edit_review?id=${review.reviewId}">Edit</a>&nbsp; 
						<a href="javascript:void(0);" class="deleteLink" id="${review.reviewId}">Delete</a> 
					<%-- <a href="javascript:confirmDelete(${review.reviewId})" >Delete</a> --%>
					<%-- td colspan="2" align="center">
					<b><a href="edit_profile?email=${loggedCustomer.email}">Edit My Profile</a></b></td> --%>
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