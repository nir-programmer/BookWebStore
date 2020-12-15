<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Category</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
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
			<form action="create_categories" method="post" id="categoryForm">
		</c:if>
		<c:if test="${category != null}"> 
			<form action="update_categories" method="post" id="categoryForm">
			<input type="hidden" name="categoryId" value="${category.categoryId}">
		</c:if>
			<table class="form">
				<tr>
					<td align="right">Category Name:</td>
					<td align="left"><input id="categoryName" type="text" name="categoryName"
						size="20" value="${category.name}"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit" value=Save">Save</button>&nbsp;&nbsp;&nbsp;
					<!-- <button onclick="javascript:history.go(-1);" value="Cancel">Cancel</button> -->
					<button id="cancelButton" value="Cancel">Cancel</button>
				</td>

				</tr>

			</table>
		</form>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">

$(document).ready(function()
	{
		$("#categoryForm").validate({
			rules: 
			{
				
				categoryName: "required"
				
			},
			messages: 
			{
				categoryName: "Please enter a category name"
			}
		}); 
		
	}); 
	
	$("#cancelButton").on("click", function()
			{
				history.go(-1); 
		
			}); 
</script>

<!-- <script type="text/javascript">
	function validateFormInput() {
		var fieldCategoryName = document.getElementById("categoryName");
		

		if (fieldCategoryName.value.length == 0) {
			alert("Category name is required!");
			fieldCategoryName.focus();
			return false;
		}

		return true;

	}
</script>
 -->


</html>