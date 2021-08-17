<%@page import="com.oop.model.Billpayment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/
css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
crossorigin="anonymous">
<meta charset="UTF-8">
<title>SLIIT OOP Bank Management</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">


	DETAILS OF BILL PAYMENT
	<br>
	<br>
	<%
		Billpayment billpayment = (Billpayment) request.getAttribute("billpayment");
	%>

	<form method="POST" action="UpdateBillpaymentServlet">
		<table>
			<tr>
				<td>Account Number</td>
				<td><input type="text" name="accountNumber"
					value="<%=billpayment.getAccNum()%>" readonly/></td>
			</tr>
		
			<tr>
				<td>Bill Number</td>
				<td><input type="text" name="billID" 
					value="<%=billpayment.getBillID()%>" readonly/></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"
					value="<%=billpayment.getName()%>" /></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount"
					value="<%=billpayment.getAmount()%>" /></td>
			</tr>
			<tr>
				<td>Bank</td>
				<td><input type="text" name="bank"
					value="<%=billpayment.getBank()%>" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="billID"
					value="<%=billpayment.getBillID()%>" /> <input type="submit"
					value="Update Bill Details"/></td>
			</tr>

		</table>
	</form>

	<table>
		<tr>
			<td colspan="2">
				<form method="POST" action="RequestDeleteBillPaymentServlet">
					<input type="hidden" name="billID" value="<%=billpayment.getBillID()%>" /> 
					<input type="hidden" name="accountNumber" value="<%=billpayment.getAccNum()%>" />
					<input type="submit"
						value="Delete Bill payment" />
				</form>
			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>