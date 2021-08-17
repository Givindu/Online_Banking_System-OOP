<%@page import="com.oop.model.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.AccountServicelmpl"%>
<%@page import="com.oop.service.NAccountService"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Delete Requests</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<h3>Account Delete Requests</h3>
	<br>
	<br>
	  <div align="left">
		<table class="table table-bordered table-dark">
		 <a href="AdminPage.jsp">Go Back</a>
		  <tr>
                <th>Account Number</th>
                <th>Owner Name</th>
                <th>National ID</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Account Type</th>
                <th>Account Balance</th>
                
            </tr>
            <%
            AccountServicelmpl nAccountService = new AccountServicelmpl();
			ArrayList<Account> arrayList = nAccountService.listDeleteRequests();
			
			for(Account account : arrayList){
			%>
			 <tr>
				<td> <%=account.getAccount_number() %> </td>
				<td> <%=account.getOwner_name() %> </td>
				<td> <%=account.getNic() %> </td>
				<td> <%=account.getAge() %> </td>
				<td> <%=account.getGender() %> </td>
				<td> <%=account.getAcc_type() %> </td>
				<td> <%=account.getAcc_balance() %> </td>
				
				<td> 
				<form method="POST" action="requestToDeleteAccountServelet">
				<input type="hidden" name="Accountnumber" value="<%=account.getAccount_number()%>"/>
				 <input type="submit" value= "Delete Account" /> 
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
		</table>
		</div>
</div>
</div>		
</body>
</html>