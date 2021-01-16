<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Review</title>
<link rel="stylesheet" href="css/style.css">


<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<!-- rateyo jQuery plugin -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<!-- <link rel="stylesheet" href="jquery.rateyo.css"/> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<!-- rateyo done -->

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<!-- for jQuery validation -->
		<form id="reviewForm" action="submit_review" method="post">
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
						<!-- rateYo plugin - DONT PUT THE HIDDEN FILEDS IN THIS DIV! -->
						<div id="rateYo"></div>
							<input type="hidden"  name="rating" id="rating" />
							 <input type="hidden" name="bookId"  value="${book.bookId}" />
						 <br/> 
						<input type="text" name="headline" size="60"
						placeholder="Headline or summary for the review (required)" /> <br />
						<br /> <textarea name="comment" rows="10" cols="70"
							placeholder="Write your review details..."></textarea>
					</td>
				</tr>

				<tr>
					<td colspan="3" align="center">
						<button type="submit">Submit</button> &nbsp; &nbsp;
						<button id="buttonCancel">Cancel</button>
					</td>


				</tr>
			</table>

		</form>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>


<script type="text/javascript">
	$(document).ready(function() {

		$("#buttonCancel").click(function() {
			history.go(-1);
		});

		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required"
			},
			messages : {
				headline : "Please enter headline",
				comment : "Please enter  review details"
			}
		});

		$("#rateYo").rateYo({
			starWidth : "40px",
			fullStar : true,
			onSet : function(rating, ratYoInstance) {
				$("#rating").val(rating);
			}
		});
	});
</script>
</html>