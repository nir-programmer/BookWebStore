<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Review</title>

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Edit Review</h2>
	</div>

	<div align="center">
		<form  method="POST" id="reviewForm" action="update-review"	>
			<input type="hidden" name="reviewId" value="${review.reviewId }">
			<table class="form">
				<tr>
					<td align="right">Book:</td>
					<td align="left"><b>${review.book.title }</b></td>
				</tr>

				<tr>
					<td align="right">Rating:</td>
					<td align="left">${review.rating}</td>
				</tr>


				<tr>
					<td align="right">Customer:</td>
					<td align="left">${review.customer.fullname}</td>
				</tr>
				
				<tr>
					<td align="right">Headline:</td>
					<td align="left"><input type="text" value="${review.headline}"
					id = "headline" class="headline" name="headline"></td>
				</tr>

				<tr>
					<td align="right">Comment:</td>
					<td align="left"><textarea rows="5" cols="70" name="comment"
							id="comment">${review.comment}
					</textarea></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<button type="submit" value="Save">Save</button>&nbsp;&nbsp;&nbsp;
						<input	type="button" value="Cancel" 
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/admin/';" />
						<!-- <button onclick="javascript:history.go(-1);" value="Cancel">Cancel</button> -->
						<!-- <input type="submit" id="cancelButton" value="Cancel" /> -->
						<!-- <button id="cancelButton" value="Cancel">Cancel</button> -->
					</td>

				</tr>
			</table>
		</form>


	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>



<script type="text/javascript">
	$(document).ready(function() {
		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required",
			},
			
			messages : {
				headline : "Please enter headline",
				comment : "Please enter comment",
			}
		});

		//# refer to an id attribute
		$("#cancelButton").click(function() {
			window.location.href='index.jsp';

	});
</script>

<!-- <script type="text/javascript">

const inputHeadline = document.querySelector('.headline');


document.addEventListener(`keydown`, function(e){
	console.log(`A key was pressed`);
		if(e.key === 'Enter')
		alert('The key is : ' + e.key); 
		alert(inputHeadline);
})
	
</script> -->





</html>