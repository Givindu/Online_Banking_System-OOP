package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.exception.AmountException;
import com.oop.exception.BillNumberException;
import com.oop.model.FixedDeposit;
import com.oop.service.FixedDepositImpl;
import com.oop.service.IFixedDeposit;

@WebServlet("/AddFixedDeposit")
public class AddFixedDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddFixedDeposit() {
        super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		FixedDeposit fixeddeposit = new FixedDeposit();
		
		fixeddeposit.setInterest(request.getParameter("Interest"));
		fixeddeposit.setDuration(request.getParameter("Duration"));
		fixeddeposit.setAccountNum(request.getParameter("AccountNum"));
		fixeddeposit.setAmount(request.getParameter("Amount"));

		IFixedDeposit iFixedDeposit = new FixedDepositImpl();
		RequestDispatcher forwardUser = null;
		
		try {
		iFixedDeposit.addFixedDeposit(fixeddeposit);
		request.setAttribute("fixeddeposit",fixeddeposit);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFixedDeposit.jsp");
		dispatcher.forward(request, response);
		}catch(AmountException e) {
			request.setAttribute("fd", fixeddeposit);
			forwardUser = getServletContext().getRequestDispatcher("/WEB-INF/views/NewFixedDeposit.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Amount should be above Rs.5000!')</script>");
		}
	}

}
