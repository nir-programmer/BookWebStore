<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title}-OnlineBooksStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<div align="center" class='box'>
		<p>${article.content}</p>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
	<!-- <script type="text/javascript">
		//create the div element 
		const message = document.createElement('div');
		
		//Add html content to the element
		message.innerHTML = `We use cooked for improved functionallity nd analytics.
		 <button class="btn-close-cookie">Got it!</button>`;
		
		//add the cookie-message class to the classList
		message.classList.add('cookie-message')
		message.innerHTML = `We are using cookies to support better service 
		<button>Got it</button>`;
		
		//insert to the box
		const box = document.querySelector('.box');
		box.prepend(message);
		
		//remove the box when click on the buttons
		const btnCloseCookie = document.querySelector('.btn btn-close-cookie').addEventListener('click', function(){
			message.remove(message); 
		})
		
	</script> -->
</body>
</html>