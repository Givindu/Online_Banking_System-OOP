package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.exception.AccountException;
import com.oop.model.Transaction;
import com.oop.service.ITransactionService;
import com.oop.service.TransactionService;

/**
 * Servlet implementation class FundTransfer2
 */
@WebServlet("/FundTransfer2")
public class FundTransfer2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundTransfer2() {
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
		
		Transaction transaction = new Transaction();
		
		transaction.setAccountNo1(request.getParameter("accountNo1"));
		transaction.setAccountNo2(request.getParameter("accountNo2"));
		transaction.setAmount(Float.parseFloat(request.getParameter("amount")));

		ITransactionService iTransactionService = new TransactionService();
		RequestDispatcher forwardUser = null;
		
		try{
			iTransactionService.addTransaction(transaction);
			request.setAttribute("transfer", transaction);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListTransactions.jsp");
			dispatcher.forward(request, response);
		}catch(AccountException e) {
			request.setAttribute("transfer", transaction);
			forwardUser = getServletContext().getRequestDispatcher("/WEB-INF/views/NewTransfer.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Recipient account should be different!')</script>");
		}
		
	}

}
