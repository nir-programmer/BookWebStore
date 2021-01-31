<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Book to Order</title>
</head>
<body>
	<div align="center"> 
		<h2>Add book to Order ID:${order.orderId}</h2>
		<form action="add_book_to_order" method="post">
			<table>
				<!-- list of all books in the store -->
				<tr> 
					<td>Select a book: </td>
					<td> 
						<select name="bookId" > 
							<c:forEach items="${books}" var ="book" varStatus="status">
								<option value="${book.bookId}" >${book.title} - ${book.author}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr> </tr>
				<!-- list of all books in the store -->
				<tr>  
				  	<td>Number of Copies:</td>
				  	<td> 
				  		<select name="quantity">
				  			<option value="1">1</option>
				  			<option value="2">2</option>
				  			<option value="3">3</option>
				  			<option value="4">4</option>
				  			<option value="5">5</option>
				  		
				  		</select>
				  	</td>
				 </tr>
				 
				 <tr> </tr>
				 
				 <tr> 
				 	<td align="center" colspan="2">
				 		<input type="submit" value="Add" > 
				 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 		<input type="button" value="Cancel" onclick="javascript: self.close();">
				 	</td>
				 </tr>
				  
				
				
			
			</table>
		</form>
	</div>

</body>
</html>