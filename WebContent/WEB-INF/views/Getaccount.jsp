<%@page import="com.oop.model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<meta charset="UTF-8">
<title>ONLINE BANK</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">


	<h2 class="h2">Get Account Page</h2>

	<a href="HomePage.jsp">Home Page</a>
	<br>
	<br>
	<%
		Account account = (Account) request.getAttribute("Account");
	%>

	<form method="POST" action="UpdateAccountServelet">
		<table>
			<tr>
				<td>AccountNumber</td>
				<td><input type="text" name="Account number" disabled="disabled"
					value="<%=account.getAccount_number()%>" /></td>
			</tr>
			<tr>
				<td> OwnerName</td>
				<td><input type="text" name="Owner Name"
					value="<%=account.getOwner_name()%>" required/></td>
			</tr>
			<tr>
				<td>NationalId</td>
				<td><input type="text" name="National ID"
					value="<%=account.getNic()%>" required/></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="Age"
					value="<%=account.getAge()%>" required/></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td><input type="radio" name="Gender" value="male"
					value="<%=account.getGender()%>" checked="checked" tabindex="1" required/> Male</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="Gender" value="female"
					value="<%=account.getGender()%>" tabindex="2" required/> Female</td>
			</tr>
			<tr>
				<td>AccountType</td>
				<td><input type="radio" name="Account Type" value="Credit"
					value="<%=account.getAcc_type()%>"checked="checked" tabindex="1" required/>Credit</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="Account Type" value="Saving"
					value="<%=account.getAcc_type()%>" tabindex="2" required/>Saving</td>
			</tr>			
			<tr>
				<td>AccountBalance</td>
				<td><input type="text" name="Account balance"
					value="<%=account.getAcc_balance()%>" required/></td>
			</tr>
			  <tr>
				<td colspan="2"><input type="hidden" name="Account number"
					value="<%=account.getAccount_number()%>" /> <input type="submit"
					value="Update ACCOUNT" class="update-button"/>
			</tr>
		</table>
	</form>

	<form method="POST" action="Request">
		<input type="hidden" name="Accountnumber" value="<%=account.getAccount_number()%>" /> 
		<input type="submit" value="REQUEST TO DELETE" class="delete-button"/>
	</form>
	</div>
	</div>
</body>
</html>