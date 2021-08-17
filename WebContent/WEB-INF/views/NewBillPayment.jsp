<%@page import="com.oop.model.Billpayment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Online Bill Payment</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<%Billpayment billPayment = (Billpayment) request.getAttribute("billPayment"); %>

	<h2 class="h2">Online Bill Payment</h2>
	<br>
	<br>
	<a href="HomePage.jsp">Home Page</a>

	<form method="POST" action="AddBillpaymentServlet">
		<table>

			<tr>
				<td>Account Number</td>
				<td><input type="text" name="accountNumber" value="<%=billPayment.getAccNum()%>" readonly/></td>
			</tr>
			<tr>
				<td>Bill Number</td>
				<td><input type="text" name="billID" required/></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" required/></td>
			</tr>
			<tr>
				<td>Amount</td>
				<td><input type="text" name="amount" required/></td>
			</tr>
			<tr>
				<td>Bank</td>
				<td><input type="text" name="bank" required/></td>
			</tr>
		
			<tr>
				<td colspan="2"><input type="submit" value="Add Billpayment" /> </td>
			</tr>
			<tr>	
				<td colspan="2"><input type="reset" value="Reset Details" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="ListBillpaymentServlet">
		<table>
			<tr>
				<td colspan="2">
				<input type="hidden" name="accountNo" value="<%=billPayment.getAccNum()%>"/>
				<input type="submit" value="List All Customers" />
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>