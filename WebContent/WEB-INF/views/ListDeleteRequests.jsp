<%@ page import="com.oop.model.Transaction"%>
<%@ page import="com.oop.service.TransactionService" %>
<%@ page import="com.oop.service.ITransactionService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Fund Transfer Cancellations</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
<h3>Fund Transfer Cancellations</h3>
	
	<br>
	<br>
	 <a href="AdminPage.jsp">Go back</a>
	  <div align="left">
		<table class="table table-bordered table-dark">
		  <tr>
                <th>Transaction ID</th>
                <th>Account No 1</th>
                <th>Account No 2</th>
                <th>Amount</th>
            </tr>
            <%
            ITransactionService iTransactionService = new TransactionService();
			ArrayList<Transaction> arrayList = iTransactionService.listDeleteRequests();
			
			for(Transaction transaction : arrayList){
			%>
			 <tr>
				<td> <%=transaction.getTransactionId() %> </td>
				<td> <%=transaction.getAccountNo1() %> </td>
				<td> <%=transaction.getAccountNo2() %> </td>
				<td> <%=transaction.getAmount() %> </td>	
				<td> 
				<form method="POST" action="DeleteTransaction">
				<input type="hidden" name="transactionID" value="<%=transaction.getTransactionId()%>"/>
				 <input type="submit" value= "Delete Transaction" class="select-button" /> 
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