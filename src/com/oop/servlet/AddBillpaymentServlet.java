
package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.exception.BillNumberException;
import com.oop.model.Billpayment;
import com.oop.service.BillpaymentService;
import com.oop.service.IBillpaymentService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AddBillpaymentServlet")
public class AddBillpaymentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBillpaymentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Billpayment billpayment = new Billpayment();
		
		billpayment.setAccNum(request.getParameter("accountNumber"));
		billpayment.setBillID(request.getParameter("billID"));
		billpayment.setName(request.getParameter("name"));
		billpayment.setAmount(Float.parseFloat(request.getParameter("amount")));
		billpayment.setBank(request.getParameter("bank"));

		IBillpaymentService iBillpaymentService = new BillpaymentService();
		RequestDispatcher forwardUser = null;

		try {
			iBillpaymentService.addBillpayment(billpayment);		
			request.setAttribute("billPayment", billpayment);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListBillpayments.jsp");
			dispatcher.forward(request, response);
		}catch(BillNumberException e) {
			request.setAttribute("billPayment", billpayment);
			forwardUser = getServletContext().getRequestDispatcher("/WEB-INF/views/NewBillPayment.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Bill Number should be 4 characters!')</script>");
		}
	}
}
