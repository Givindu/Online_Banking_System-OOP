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
<title>List of Transactions</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<%Transaction transfer = (Transaction) request.getAttribute("transfer"); %>
	<h3>List of Transactions</h3>
	<a href="HomePage.jsp">Home Page</a>
	<br>
	<br>
	  <div align="left">
		<table class="table table-bordered table-dark">
		<form method="POST" action="NewTransfer">
		<input type="hidden" name="accountNo" value="<%=transfer.getAccountNo1()%>"/>
		<input type="submit" value="Transfer Money"/><br>
		</form>
		  <tr>
                <th>Transaction ID</th>
                <th>Account Number</th>
                <th>Recipient Account Number</th>
                <th>Amount</th>
            </tr>
            <%
    		String accountNo= transfer.getAccountNo1();
            ITransactionService iTransactionService = new TransactionService();
			ArrayList<Transaction> arrayList = iTransactionService.getTransactions(accountNo);
			
			for(Transaction transaction : arrayList){
			%>
			 <tr>
				<td> <%=transaction.getTransactionId() %> </td>
				<td> <%=transaction.getAccountNo1() %> </td>
				<td> <%=transaction.getAccountNo2() %> </td>
				<td> <%=transaction.getAmount() %> </td>	
				<td> 
				<form method="POST" action="GetTransaction">
				<input type="hidden" name="transactionID" value="<%=transaction.getTransactionId()%>"/>
				 <input type="submit" value= "Select Transaction"/> 
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