<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order History</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">My Order History</h2>
	</div>

	<c:if test="${fn:length(orders) == 0 }">
		<h3 align="center">You have not placed any orders</h3>
	</c:if>

	<c:if test="${fn:length(orders) >  0 }">
		<div align = "center">
			<table border="1">
				<tr>
					<th>Index</th>
					<th>Order ID</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${orders}" var="order" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${order.orderId }</td>
						<td>${order.bookCopies}</td>
						
						<td><fmt:setLocale value="en_US" /> <fmt:formatNumber
										value="${order.total}" type="currency" />
						</td>
						<td>${order.orderDate }</td>
						<td>${order.status}</td>
						<td><b><a href="">View Details</a></b></td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</c:if>
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>