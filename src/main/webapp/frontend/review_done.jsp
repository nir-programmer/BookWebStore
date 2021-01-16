<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Review Posted - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">


</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<table class="normal" width="60%">
			<tr>
				<td><h2>Your Reviews</h2></td>
				<td>&nbsp;</td>
				<td><h2 align="right">${loggedCustomer.fullname}</h2></td>
			</tr>

			<tr>
				<td colspan="3"><hr /></td>
			</tr>
			<tr>
				<td rowspan="2"><span id="book-title">${book.title}</span><br />
					<img src="data:image/jpg;base64,${book.base64Image}" width="240"
					height="300" /></td>

				<td>
				<td colspan="2"><h3>Your review has been posted.Thank you!</h3></td>
			</tr>
		</table>

		</form>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


</html>