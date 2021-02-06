<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	
	
	<div align="center">
		<h2 class="pageheading">Recent Sales:</h2>
		<table border="1">
			<tr>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			
			<c:forEach items="${listRecentSales}" var="order" >

				<c:url value="view_order" var="viewLink">
					<c:param name="id" value="${order.orderId}" />
				</c:url>
				
				<tr>
					
					<td><a href="${viewLink}">${order.orderId}</a></td>
					<!-- important -->
					<td>${order.customer.fullname}</td>
					<!-- This value is returned from a transient getter in BookOrder entity -->
					<td>${order.bookCopies}</td>
					<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
							value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					
				</tr>
			</c:forEach>
		
		
		</table>
		
	</div>
	
	<div align="center">
		<h2 class="pageheading">Recent Reviews:</h2>
		<table border="1">
			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
				
			</tr>

			<c:forEach items="${listMostRecent}" var="review" >
				<c:url value="edit_review" var="viewReview">
					<c:param name="id" value="${order.orderId}" />
				</c:url>
				
				
				
				<tr> 
					<td>${review.book.title}</td>
					<td>${review.rating } </td>
					<td>
						<a href="edit_review?id=${review.reviewId}">${review.headline }</a>
					</td> 
					<td>${review.customer.fullname }</td>
					<td>${review.reviewTime }</td>
					
				</tr>




			</c:forEach>
		

		</table>
	
	</div>

	<hr width="60%" />
	<div align="center">
		<h2 class="pageheading">Statistics:</h2>
		
	</div>

	<hr width="60%" />
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>