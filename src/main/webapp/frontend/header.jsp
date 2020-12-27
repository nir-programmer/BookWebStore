<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}">
			<img src="images/logo.jpg">
		</a>
		
	</div>
	<div>
		<form action="search" method="get">
			<input type="text" placeholder="Search" size="50" name="keyword" />
			<input type="submit" value="Search"> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="login">Sign in</a> | 
			<a href="register">Register</a> | 
			<a href="view_cart">Cart</a>
		</form>


	</div>
	<div>&nbsp;</div>
	<div>
		<c:forEach items="${categories}" var="category" varStatus="status">
			<c:url value="view_category" var="categoryDetailsLink">
				<c:param name="id" value="${category.categoryId}"></c:param>
			</c:url>
			<a href="${categoryDetailsLink}"> <font size="+1"><b><c:out
							value="${category.name}" /></b> </font>
			</a>
			<c:if test="${not status.last}">
					&nbsp; | &nbsp;
					</c:if>
		</c:forEach>
	</div>



</div>
