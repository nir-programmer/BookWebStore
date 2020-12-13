<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Category</title>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>
		<c:if test="${category != null}">
			Edit Category
		</c:if>
		<c:if test= "${category == null}"> 
			Create Category
		</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${category == null }"> 
			<form action="create_categories" method="post" onsubmit="return validateFormInput()">
		</c:if>
		<c:if test="${category != null}"> 
			<form action="update_categories" method="post" onsubmit="return validateFormInput()">
			<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>
			<table>
				<tr>
					<td align="right">Category Name:</td>
					<td align="left"><input id="categoryName" type="text" name="categoryName"
						size="20" value="${user.email}"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"> <input type="button" value="Cancel">
					</td>

				</tr>

			</table>
		</form>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");

		if (fieldEmail.value.length == 0) {
			alert("Email is required!");
			fieldEmail.focus();
			return false;
		}

		if (fieldFullName.value.length == 0) {
			alert("full name is required!");
			fieldFullName.focus();
			return false;
		}
		if (fieldPassword.value.length == 0) {
			alert("password is required!");
			fieldPassword.focus();
			return false;
		}
		return true;

	}
</script>



</html>