<%@page import="com.oop.model.FixedDeposit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Update Fixed Deposit</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">

	<h2>Update Fixed Deposit</h2>

	<br>
	<br>
	<%
		FixedDeposit fixeddeposit = (FixedDeposit) request.getAttribute("fixeddeposit");
	%>

	<form method="POST" action="UpdateFixedDeposit">
		<table>
			<tr>
				<td>DEPOSIT ID</td>
				<td><input type="text" name="depositID" disabled="disabled"
					value="<%=fixeddeposit.getDepositID()%>" /></td>
			</tr>
			<tr>
				<td>Account Number</td>
				<td><input type="text" name="accountNum"
					value="<%=fixeddeposit.getAccountNum()%>" readonly/></td>
			</tr>
			<tr>
				<td>Interest</td>
				<td><input type="text" name="interest"
					value="<%=fixeddeposit.getInterest()%>" required/></td>
			</tr>
			<tr>
				<td>Duration</td>
				<td><input type="text" name="duration"
					value="<%=fixeddeposit.getDuration()%>" required/></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount"
					value="<%=fixeddeposit.getAmount()%>" required/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="depositID"
					value="<%=fixeddeposit.getDepositID()%>" /> <input type="submit"
					value="Update FixedDeposit" class="update-button"/></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="RequestDeleteFixedDepositServlet">
					<input type="hidden" name="depositID" value="<%=fixeddeposit.getDepositID()%>" /> 
					<input type="hidden" name="accountNo" value="<%=fixeddeposit.getAccountNum()%>"/>
					<input type="submit" value="Delete Request" />
				</form>
			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>