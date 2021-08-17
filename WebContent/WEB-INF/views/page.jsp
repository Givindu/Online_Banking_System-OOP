<%@page import="com.oop.model.Billpayment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oop.service.BillpaymentService"%>
<%@page import="com.oop.service.IBillpaymentService"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Bill Payments Delete Requests</title>
</head>
<body>
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
	<caption><h3>List of Bill Payments Delete Requests</h3></caption>
	<br>
	<br>
	  <div align="left">
		<table class="table table-bordered table-dark">

		 <a href="AdminPage.jsp">Go Back</a>
		  <tr>
                <th>Account number</th>
                <th>Bill Number</th>
                <th>Name</th>
                <th>Amount</th>
                <th>Bank</th>
            </tr>
            <%
            BillpaymentService iBillpaymentService = new BillpaymentService();
			ArrayList<Billpayment> arrayList = iBillpaymentService.listDeleteRequests();
			
			for(Billpayment billpayment : arrayList){
			%>
			 <tr>
				<td> <%=billpayment.getAccNum() %> </td>
				<td> <%=billpayment.getBillID() %> </td>
				<td> <%=billpayment.getName() %> </td>
				<td> <%=billpayment.getAmount() %> </td>
				<td> <%=billpayment.getBank() %> </td>
				<td> 
				<form method="POST" action="DeleteBillpaymentServlet">
				<input type="hidden" name="billID" value="<%=billpayment.getBillID()%>"/>
				 <input type="submit" value= "Delete Bill Payment"/> 
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