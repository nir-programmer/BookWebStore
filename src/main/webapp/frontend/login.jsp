<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	
	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2>Please Login</h2>
		<form >
			Email:<input type="text" size="10"> <br/>
			Password:<input type="password" size="10"> <br/>
			<input type="submit" value="login" >
		</form>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	
</body>
</html>