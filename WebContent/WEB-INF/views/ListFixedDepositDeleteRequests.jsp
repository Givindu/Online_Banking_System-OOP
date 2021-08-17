<%@page import="com.oop.model.FixedDeposit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.FixedDepositImpl"%>
<%@page import="com.oop.service.IFixedDeposit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fixed Deposit Cancellations</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">	
	
	<h3>Fixed Deposit Cancellations</h3>
	<a href="AdminPage.jsp">Go back</a>
	<br>
	<br>
	  <div align="left">
		<table class="table table-bordered table-dark">
		  <tr>
		  		<th>AccountNum</th>
                <th>Deposit ID</th>
                <th>Interest</th>
                <th>Duration</th>
                <th>Amount</th>
              
            </tr>
            <%
            FixedDepositImpl iFixedDeposit = new FixedDepositImpl();
			ArrayList<FixedDeposit> arrayList = iFixedDeposit.listDeleteRequests();
			
			for(FixedDeposit fixeddeposit : arrayList){
			%>
			 <tr>			 	
				<td> <%=fixeddeposit.getAccountNum() %> </td>
				<td> <%=fixeddeposit.getDepositID() %> </td>
				<td> <%=fixeddeposit.getInterest() %> </td>
				<td> <%=fixeddeposit.getDuration() %> </td>
				<td> <%=fixeddeposit.getAmount() %> </td>	
				<td> 
				<form method="POST" action="DeleteFixedDeposit">
				<input type="hidden" name="depositID" value="<%=fixeddeposit.getDepositID()%>"/>
				 <input type="submit" value= "Delete Fixed Deposit" /> 
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