package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.FixedDeposit;
import com.oop.service.FixedDepositImpl;
import com.oop.service.IFixedDeposit;

@WebServlet("/UpdateFixedDeposit")
public class UpdateFixedDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateFixedDeposit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		FixedDeposit fixeddeposit = new FixedDeposit();
		String depositID=request.getParameter("depositID");
		
		fixeddeposit.setDepositID(request.getParameter("depositID"));
		fixeddeposit.setInterest(request.getParameter("interest"));
		fixeddeposit.setDuration(request.getParameter("duration"));
		fixeddeposit.setAccountNum(request.getParameter("accountNum"));
		fixeddeposit.setAmount(request.getParameter("amount"));

		IFixedDeposit iFixedDeposit= new FixedDepositImpl();
		iFixedDeposit.updateFixedDeposit(depositID, fixeddeposit);
		
		request.setAttribute("fixeddeposit",fixeddeposit);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFixedDeposit.jsp");
		dispatcher.forward(request, response);
	}
	
}
