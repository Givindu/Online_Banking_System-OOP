<%@page import="com.oop.model.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Transfer Money</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<%Transaction transfer = (Transaction) request.getAttribute("transfer"); %>
	<h2>Transfer Money</h2>

	<br>
	<a href="HomePage.jsp">Home Page</a>
	<form method="POST" action="FundTransfer2">
		<table>

			<tr>
				<td>AccountNo1</td>
				<td><input type="text" name="accountNo1" value="<%=transfer.getAccountNo1()%>" readonly /></td>
			</tr>
			<tr>
				<td>AccountNo2</td>
				<td><input type="text" name="accountNo2" required /></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Transaction" class="add-button" /> </td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListTransaction">
				<input type="hidden" name="accountNo" value="<%=transfer.getAccountNo1()%>"/>
				<input type="submit" value="List All Transactions"/>
	</form>
</div>
</div>
</body>
</html>