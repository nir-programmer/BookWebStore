<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Receipt - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>You have made payment successfully. Thank you for purchasing!
		</h2>
	
	<jsp:directive.include file="receipt.jsp" />
		
		</table>

		<!-- Section 4: The Print button -->
		<div>
			<br /> 
			<input type="button" value="Print Receipt"
				onclick="javascript:showPrintReceiptPopup();" />
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	<script>
		function showPrintReceiptPopup()
		{
			var width = 600; 
			var height =  250;
			var left = (screen.width - width) / 2;
			//Check!!not height?
			var top = (screen.height  -height) /2;
			window.open('frontend/print_receipt.jsp', '_blank',
					'width=' + width +', height=' + height +
					', top=' + top + ', left=' + left);
		}
	
	</script>
</body>



</html>