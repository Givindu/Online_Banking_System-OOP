package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oop.model.Billpayment;
import com.oop.service.BillpaymentService;
import com.oop.service.IBillpaymentService;


/**
 * Servlet implementation class UpdateBillpaymentServlet
 */
@WebServlet("/UpdateBillpaymentServlet")
public class UpdateBillpaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBillpaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");

		Billpayment billpayment = new Billpayment();
		String billID = request.getParameter("billID");	
		billpayment.setBillID(billID);
		billpayment.setAccNum(request.getParameter("accountNumber"));
		billpayment.setName(request.getParameter("name"));
		billpayment.setAmount(Float.parseFloat(request.getParameter("amount")));
		billpayment.setBank(request.getParameter("bank"));
		
		IBillpaymentService iEmployeeService = new BillpaymentService();
		iEmployeeService.updateBillpayment(billID, billpayment);
		
		request.setAttribute("billPayment", billpayment);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListBillpayments.jsp");
		dispatcher.forward(request, response);
	
	}

}
