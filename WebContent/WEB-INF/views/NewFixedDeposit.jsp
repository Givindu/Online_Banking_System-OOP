<%@page import="com.oop.model.FixedDeposit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>New Fixed Deposit</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<%FixedDeposit fd = (FixedDeposit) request.getAttribute("fd"); %>

	<h2 class="h2">Add FixedDeposit Page</h2>

	   SLIIT Employee Management App for Object Orineted Progrmming
	<br>
	<br>
	<a href="HomePage.jsp">Home Page</a>

	<form method="POST" action="AddFixedDeposit">
		<table>
			<tr>
				<td>Account Number</td>
				<td><input type="text" name="AccountNum" value="<%=fd.getAccountNum()%>" readonly /></td>
			</tr>
			<tr>
				<td>Interest</td>
				<td><input type="text" name="Interest" required/></td>
			</tr>
			<tr>
				<td>Duration</td>
				<td><input type="text" name="Duration" required/></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="Amount" required/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add FixedDeposit" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset"/></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListFixedDeposit">
		<table>
			<tr>
				<td colspan="2">
				<input type="hidden" name="accountNo" value="<%=fd.getAccountNum()%>"/>
				<input type="submit" value="List All Deposits" />
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>