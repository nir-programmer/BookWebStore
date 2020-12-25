<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}- Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<c:if test="${message ne null}" >
		<h2 align="center"> ${message} </h2>
	</c:if >
	<c:if test= "${message eq null }"> 
	<div align="center">
		<table style="border: 0;width: 80%;">
			<tr>  
				<td colspan="3" align="left" > 
					<h2>${book.title}</h2> by ${book.author}
				</td>
			</tr>
			<tr> 
				<td rowspan="2">
					<img src="data:image/jpg;base64,${book.base64Image}" width="240" height="300" />
				</td>
				
				<td valign="top" align="left">
					Rating ***** 
				</td>
				
				<td valign="top" rowspan="2" width="20%"> 
					<h2>$${book.price}</h2>
					<br/><br/>
					<button type="submit" >Add to cart</button>
				</td>
			</tr>
			<tr> 
				<td valign="top" style="text-align: justify;">
					${book.description }
				</td>
			</tr>
			<tr><td>&nbsp;</td> </tr>
			<tr>  
				<td><h2>Customer Reviews </h2></td>
				<td align="center" colspan="2">
					<button>Write a customer review</button>
				</td>
			</tr>
		</table>
	</div>
</c:if>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>