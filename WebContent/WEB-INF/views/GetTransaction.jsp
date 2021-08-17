<%@page import="com.oop.model.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Your Transaction</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<h2>Your Transaction</h2>

	Online Bank
	<br>
	<br>
	<%
		Transaction transaction = (Transaction) request.getAttribute("transaction");
	%>

	<form method="POST" action="UpdateTransaction">
		<table>
			<tr>
				<td>Transfer ID</td>
				<td><input type="text" name="transactionID" disabled="disabled"	value="<%=transaction.getTransactionId()%>" /></td>
			</tr>
			<tr>
				<td>Account No</td>
				<td><input type="text" name="accountNo1" value="<%=transaction.getAccountNo1()%>" readonly /></td>
			</tr>
			<tr>
				<td>Recipient Account No</td>
				<td><input type="text" name="accountNo2"
					value="<%=transaction.getAccountNo2()%>" required/></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount"
					value="<%=transaction.getAmount()%>" required /></td>
			</tr>
		</table>
			<input type="hidden" name="transactionID" value="<%= transaction.getTransactionId() %>" /> 
			<input type="submit" value="Update Transfer" />
	</form>
	<form method="POST" action="DeleteRequest">
					<input type="hidden" name="transactionID"value="<%= transaction.getTransactionId() %>" /> 
					<input type="hidden" name="accountNo" value="<%=transaction.getAccountNo1()%>"/>
					<input type="submit" value="Delete Request"/>
	</form>
	</div>
	</div>
</body>
</html>