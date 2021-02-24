<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center" style="clear: both;">
	<h4>Copyright (C) 2018 by Evergreen Books.Co., ltd</h4>
	<div>
			<c:forEach items="${articles}" var="article" varStatus="status">
			<c:url value="view_article" var="articleDetailsLink">
				<c:param name="id" value="${article.articleId}"></c:param>
			</c:url>
			<a href="${articleDetailsLink}"> <font size="+1"><b>
			<c:out value="${article.title}" /></b> </font>
			</a>
			<c:if test="${not status.last}">
					&nbsp; | &nbsp;
					</c:if>
		</c:forEach>
	
	</div>
	<!-- <a href="about?title=about">About Us</a> |
	<a href="contact">Contact Us</a> |
	<a href="policy">Policy</a> |
	<a href="shipping">Shipping & Delivery</a> -->
</div>