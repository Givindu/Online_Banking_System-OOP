<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<h2 >CREATE NEW ACCOUNT</h2>

	<br>
	<br>

	<form method="POST" action="CreateAccountServelet">
		<table>
		    
			<tr>
				<td>Name</td>
				<td><input type="text" name="ownername" required /></td>
			</tr>
			<tr>
				<td>National ID</td>
				<td><input type="text" name="NationalID" required /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="Age" required /></td>
			</tr>
			
				<tr>
				<td>Gender</td>
				<td><input type="radio" name="gender" value="male"
					tabindex="1" required/> Male</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="gender" value="female"
					tabindex="2" required/> Female</td>
					</tr>
					<tr>
				<td>AccountType</td>
				<td><input type="radio" name="accounttype" value="Credit"
					 tabindex="1" required/> Credit</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="radio" name="accounttype" value="Saving"
					tabindex="2" required/> Saving</td>
					</tr>
			
			<tr>
				<td>AccountBalance</td>
				<td><input type="text" name="accountbalance" required /></td>
			</tr>
			<tr>
				<td>Password</td>
			 	<td><input type="password" placeholder="Enter Password" name="password" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="ADD ACCOUNT"/> </td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
</html>