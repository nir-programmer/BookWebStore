<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin/">
			<img src="../images/logo.jpg">
		</a>
	</div>
	<div>
		Welcome,<c:out value="${sessionScope.userEmail}" />  <a href="logout">logout</a>
	</div>
	
	<!--  for putting the items in the same line -->
	<div id="headermenu">
		<div>
			<a href="list_users"> <img class="option-box-img" src="../images/users.png"><br>Users
			</a>
		</div>
		
		<div >
			<a href="list_categories"> <img class="option-box-img" src="../images/categories.png"><br>Categories
			</a>
		</div>
		
		<div >
			<a href="list_books"> <img alt="" class="option-box-img" src="../images/books.png"><br>Books
			</a>
		</div>
		
		<div >
		<a href="list_customers"> 
			<img alt=""  class="option-box-img" src="../images/customers.png"><br>Customers
		</a> 
		</div>
		
		<div>
			<a href="list_reviews">
				<img alt="" class="option-box-img" src="../images/reviews.png"><br>Reviews
				</a> 
		</div>
		
		<div>
			<a href="list_orders">
				<img alt="" class="option-box-img" src="../images/orders.png"><br>Orders
				</a>
		</div>
		
		<div>
			<a href="list_articles">
				<img alt="" class="option-box-img" src="../images/articles.png"><br>Articles
				</a>
		</div>
</div>
</div>