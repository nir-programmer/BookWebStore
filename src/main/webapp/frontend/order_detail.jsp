<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order Details - Evergreen Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<!--  for security : the order is null if the orderDAO -->
	<c:if test="${order == null}">
		<h2 class="pageheading" align="center">Sorry , you are not
			authorized to view this order!</h2>
	</c:if>

	<c:if test="${order != null }">
		<div align="center">
			<h2 class="pageheading">Your Order ID: ${order.orderId}</h2>
		</div>

		<!-- Common section for back end and front end -->
		<jsp:directive.include file="../common/common_order_detail.jsp" />

	</c:if>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>