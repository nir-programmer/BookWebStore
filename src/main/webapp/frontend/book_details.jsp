<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title}- Online Books Store</title>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<c:if test="${message ne null}" >
		<h2 align="center"> ${message} </h2>
	</c:if >
	<c:if test= "${message eq null }"> 
	<div class="center">
		<table style="border: 0;width: 80%;text-align: center;display: inline-block;" >
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
					<div> 
					<%-- <jsp:include page="book_rating.jsp"></jsp:include> --%>
							<c:forTokens items="${book.ratingStars}" delims="," var="star">
								<c:if test="${star eq 'on'}">
									<img src="images/rating_on.png" />
								</c:if>

								<c:if test="${star eq 'half'}">
									<img src="images/rating_half.png" />
								</c:if>

								<c:if test="${star eq 'off'}">
									<img src="images/rating_off.png" />
								</c:if>

							</c:forTokens>
							&nbsp;&nbsp;
							<a href="#reviews">${fn:length(book.reviews)} Reviews</a>
						</div>
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
				<td><h2><a id="reviews">Customer Reviews</a></h2></td>
				<td align="center" colspan="2">
					<button>Write a customer review</button>
				</td>
			</tr>
		
			<!-- A row with one column that contains a table of rows of reviews -->
			<tr> 
				<td colspan="3" align="left"> 
					<table class="normal" >
						<c:forEach items="${book.reviews}" var="review">
							<tr> 
								<td align="left"> 
									<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating_on.png" />
										</c:if>
		
										<c:if test="${star eq 'off'}">
											<img src="images/rating_off.png" />
										</c:if>
									</c:forTokens>
									-<b>${review.headline}</b>
								</td>
							</tr>
							<tr>
								<td align="left">By ${review.customer.fullname} On ${review.reviewTime}</td>
							</tr>
							<tr> <td><i>${review.comment}</i></td></tr>
							<tr><td>&nbsp;</td></tr>
						</c:forEach>
					
					</table>
				</td>
			
			</tr>
		</table>
	</div>
</c:if>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>