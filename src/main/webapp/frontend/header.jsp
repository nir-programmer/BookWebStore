<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
	<div>
		<img src="images/logo.jpg">
	</div>
	<div>
		<input type="text" placeholder="Search" size="50" >
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Search" > |
		<a href="login">Sign in</a> |
		<a href="register">Register</a> |
		<a href="view_cart">Cart</a>
	</div>
	<div>
		&nbsp;
	</div>
	<div>
			<c:forEach items="${categories}" var="category" varStatus="status">
					<c:url value="view_category" var="categoryDetailsLink"> 
						<c:param name="id" value="${category.categoryId}"></c:param>
					</c:url>
					<a href="${categoryDetailsLink}">
						<font size="+1"><b><c:out value="${category.name}"/></b> </font>
					</a>
					<c:if test="${not status.last}">
					&nbsp; | &nbsp;
					</c:if>
			</c:forEach>
	</div>
	
	
	
</div>
