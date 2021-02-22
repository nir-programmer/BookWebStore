<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Article</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" />
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${article != null}">
				Edit Article
			</c:if>
			<c:if test="${article == null}"> 
				Create New Article
			</c:if>
		</h2>
	</div>

	<div align="center">

		<c:if test="${article != null}">
			<form action="update_article" method="post" id="articleForm">
				<input type="hidden" name="articleId" value="${article.articleId}">
		</c:if>

		<c:if test="${article == null }">
			<form action="create_article" method="post" id="articleForm">
		</c:if>


		<table class="form">



			<tr>
				<td align="right">Title:</td>
				<td align="left"><input id="title" type="text" name="title"
					size="20" value="${book.title}"></td>
			</tr>


			<tr>
				<td align="right">Content:</td>
				<td align="left"><textarea rows="5" cols="50"
						name="content" id="content">${article.content}
					</textarea></td>
			</tr>


			<td>&nbsp;</td>

			<tr>
				<td colspan="2" align="center">
					<button type="submit" value=Save">Save</button> &nbsp;&nbsp;&nbsp;
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
	$(document).ready(function() {

		

		$("#articleForm").validate({
			rules : {
				title : "required",
				content : "required",
			},
			messages : {
				title: "Please enter a title of the article",
				content : "Please enter description of the book",
			}
		});

		$("#cancelButton").click(function() {
			history.go(-1);
		});

	});

</script>

</html>