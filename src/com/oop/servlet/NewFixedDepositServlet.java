package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.FixedDeposit;

/**
 * Servlet implementation class NewFixedDepositServlet
 */
@WebServlet("/NewFixedDepositServlet")
public class NewFixedDepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFixedDepositServlet() {
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
		
		String accountNo = request.getParameter("accountNo");		
 		FixedDeposit fd=new FixedDeposit();
 		fd.setAccountNum(accountNo);

		request.setAttribute("fd", fd);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/NewFixedDeposit.jsp");
		dispatcher.forward(request, response);
	}

}
