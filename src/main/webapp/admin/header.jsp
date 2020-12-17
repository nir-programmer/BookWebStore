<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div align="center">
	<div>
		<img src="../images/logo.jpg">
	</div>
	<div>
		Welcome,<c:out value="${sessionScope.userEmail}" />  <a href="logout">logout</a>
	</div>
	
	<!--  for putting the items in the same line -->
	<div id="headermenu">
		<div>
			<a href="list_users"> <img src="../images/users.png"><br>Users
			</a>
		</div>
		
		<div >
			<a href="list_categories"> <img src="../images/categories.png"><br>Categories
			</a>
		</div>
		
		<div >
			<a href="list_books"> <img alt="" src="../images/books.png"><br>Books
			</a>
		</div>
		
		<div >
		<a href="customers"> 
			<img alt="" src="../images/customers.png"><br>Customers
		</a> 
		</div>
		
		<div>
			<a href="reviews">
				<img alt="" src="../images/reviews.png"><br>Reviews
				</a> 
		</div>
		
		<div>
			<a href="orders">
				<img alt="" src="../images/orders.png"><br>Orders
				</a>
		</div>
		

</div>