package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.FixedDeposit;
import com.oop.model.Transaction;
import com.oop.service.FixedDepositImpl;
import com.oop.service.IFixedDeposit;
import com.oop.service.ITransactionService;
import com.oop.service.TransactionService;

/**
 * Servlet implementation class RequestDeleteFixedDepositServlet
 */
@WebServlet("/RequestDeleteFixedDepositServlet")
public class RequestDeleteFixedDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDeleteFixedDepositServlet() {
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

		String depositID = request.getParameter("depositID");
		String accountNo = request.getParameter("accountNo");
		
		FixedDeposit fixeddeposit = new FixedDeposit();
		fixeddeposit.setAccountNum(accountNo);
			
		FixedDepositImpl iFixedDeposit= new FixedDepositImpl();
		iFixedDeposit.requestDelete(depositID);

		request.setAttribute("fixeddeposit", fixeddeposit);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFixedDeposit.jsp");
		dispatcher.forward(request, response);
	}

}
