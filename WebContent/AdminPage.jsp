<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">

	<div>
		<a href="HomePage.jsp">Home Page</a>
	</div>

	<form method="POST" action="ListDeleteRequests">
		<input type="submit" value="Fund Transfer Cancellation Requests"/>
	</form>
	<form method="POST" action="ListRequest">
		<input type="submit" value="Account Cancellation Requests"/>
	</form>
	<form method="POST" action="ListBillPaymentRequestServlet">
		<input type="submit" value="Bill Payment Cancellation Requests"/>
	</form>
	<form method="POST" action="ListFixedDepositRequestServlet">
		<input type="submit" value="Fixed Deposit Cancellation Requests"/>
	</form>
</div>
</div>
</body>
</html>