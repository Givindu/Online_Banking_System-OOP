<%@ page import="com.oop.model.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
<%Transaction transfer = (Transaction) request.getAttribute("transaction"); %>

<form method="POST" action="NewTransfer">
<input type="hidden" name="accountNo" value="<%=transfer.getAccountNo1()%>"/>
<input type="submit" value="Transfer Money"/><br>
</form>

<form method="POST" action="GetAccountServelet">
<input type="hidden" name="Accountnumber" value="<%=transfer.getAccountNo1()%>"/>
<input type="submit" value="Update Account"/><br>
</form>

<form method="POST" action="NewBillPaymentServlet">
<input type="hidden" name="accountNo" value="<%=transfer.getAccountNo1()%>"/>
<input type="submit" value="Pay Bills"/><br>
</form>

<form method="POST" action="NewFixedDepositServlet">
<input type="hidden" name="accountNo" value="<%=transfer.getAccountNo1()%>"/>
<input type="submit" value="Fixed Deposits"/><br>
</form>

</div>
</div>
</body>
</html>