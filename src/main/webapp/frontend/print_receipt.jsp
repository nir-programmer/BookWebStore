<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt - Online Book Store</title>
<link rel="stylesheet" href="../css/style.css">

</head>
<body onload="window.print();">
	<div align="center">
		<jsp:directive.include file="receipt.jsp" />
	</div>
</body>
</html>