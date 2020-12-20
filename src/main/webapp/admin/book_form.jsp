<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Book</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" />

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">
			Edit Book
		</c:if>
			<c:if test="${book == null}"> 
			Create Book
		</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${book == null }">
			<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
		</c:if>
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="bookForm">
				<input type="hidden" name="bookId" value="${category.categoryId}">
		</c:if>

		<table class="form">

			<tr>
				<td>Category:</td>
				<td><select name="category">
						<c:forEach var="category" items="${categories}">
							<option value="${category.categoryId}">${category.name}
							</option>
						</c:forEach>
				</select></td>
			</tr>
			
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input id="title" type="text"
					name="title" size="20" value="${book.title}"></td>
			</tr>

			<tr>
				<td align="right">Author:</td>
				<td align="left">
				<input id="author" type="text"
					name="author" size="20" value="${book.author}">
				</td>
			</tr>
			
			<tr>
				<td align="right">ISBN:</td>
				<td align="left">
					<input id="isbn" type="text"
					name="isbn" size="20" value="${book.isbn}">
				</td>
			</tr>

			<tr>
				<td align="right">Publish Date:</td>
				<td align="left">
					<input id="publishDate" type="text"
					name="publishDate" size="20" value="${book.publishDate}">
				</td>
			</tr>
		
			<!-- 
				Use File Selector type of input 
				No Value for the input 
				 -->
			<tr>
				<td align="right">Book Image:</td>
				<td align="left">
					<input id="bookImage" type="file" name="bookImage" size="20" /><br/>
					<img id="thumbnail" alt="Image Preview" style="width: 20%; margin-top: 10px;"/>
				</td>
			</tr>
		
		<tr>
				<td align="right">Price:</td>
				<td align="left">
					<input id="price" type="text"
					name="price" size="20" value="${book.price}">
				</td>
		<tr>
		
		<tr>
				<td align="right">Description:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="description" id="description"></textarea>
				</td>
		</tr>
		
		
			<td>&nbsp;</td>
		
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
	
	$('#publishDate').datepicker();
	$('#bookImage').change(function(){
		showImageThumbnail(this);
	});
	
	$("#bookForm").validate({
		rules: 
		{
			category: "required", 
			title: "required",
			author: "required",
			isbn: "required",
			publishedDate: "required",
			bookImage: "required",
			price: "required",
			description: "required",
			
		},
		messages: 
		{
			category : "Please select a category for the book",
			title: "Please enter title of the book",
			author: "Please enter author of the book ",
			isbn: "Please enter isbn of the book",
			publishedDate: "Please enter publish date of the book",
			bookImage: "Please choose an image of the book",
			price: "Please enter price of the book",
			description: "Please enter description of the book",
		}
	}); 
	
	$("#cancelButton").click(function(){
		history.go(-1); 
	});
	
});

//fileInput is the fileInput element
function showImageThumbnail(fileInput)
{
	var file = fileInput.files[0]; 
	var reader = new FileReader();
	reader.onload = function(e){
		$('#thumbnail').attr("src", e.target.result);
	};
	
	reader.readAsDataURL(file);
}
</script>

</html>