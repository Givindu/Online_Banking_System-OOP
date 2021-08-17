<%@page import="com.oop.model.FixedDeposit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.FixedDepositImpl"%>
<%@page import="com.oop.service.IFixedDeposit"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Fixed Deposits</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<%FixedDeposit fd = (FixedDeposit) request.getAttribute("fixeddeposit"); %>
	<h3>List of Fixed Deposits</h3>
	<a href="HomePage.jsp">Home Page</a>
	<br>
	<br>
	  <div align="left">
		<table class="table table-bordered table-dark">
		 <form method="POST" action="NewFixedDepositServlet">
		<input type="hidden" name="accountNo" value="<%=fd.getAccountNum()%>"/>
		<input type="submit" value="Fixed Deposits"/><br>
		</form>
		  <tr>
		  		<th>AccountNum</th>
                <th>Deposit ID</th>
                <th>Interest</th>
                <th>Duration</th>
                <th>Amount</th>
              
            </tr>
            <%
            String accountNo= fd.getAccountNum();
            IFixedDeposit iFixedDeposit = new FixedDepositImpl();
			ArrayList<FixedDeposit> arrayList = iFixedDeposit.getFixedDeposit(accountNo);
			
			for(FixedDeposit fixeddeposit : arrayList){
			%>
			 <tr>			 	
				<td> <%=fixeddeposit.getAccountNum() %> </td>
				<td> <%=fixeddeposit.getDepositID() %> </td>
				<td> <%=fixeddeposit.getInterest() %> </td>
				<td> <%=fixeddeposit.getDuration() %> </td>
				<td> <%=fixeddeposit.getAmount() %> </td>	
				<td> 
				<form method="POST" action="GetFixedDeposit">
				<input type="hidden" name="depositID" value="<%=fixeddeposit.getDepositID()%>"/>
				 <input type="submit" value= "Select FixedDeposit" /> 
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