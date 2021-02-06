
<c:url value="view_book" var="viewBookLink">
	<c:param name="id" value="${book.bookId}"></c:param>
</c:url>

<div style="display: inline-block; margin: 0 auto;">
	<div>
		<%--  NO JSTL: <a href="view_book?id=${book.bookId}"> --%>
		<a href="${viewBookLink}"> <img
			src="data:image/jpg;base64,${book.base64Image}" width="128 height=" 164" />
		</a>
	</div>
	<div>
		<a href="${viewBookLink}"><b>${book.title} </b> </a>
	</div>
	<div>
		<%-- <jsp:include page="book_rating.jsp"></jsp:include> --%>
		<jsp:directive.include file="book_rating.jsp" />
	</div>


	<div>
		<b>${book.author} </b>
	</div>

	<div>
		<b>${book.price} </b>
	</div>

</div>