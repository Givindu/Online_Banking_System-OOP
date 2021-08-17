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

/**
 * Servlet implementation class GetFixedDeposit
 */
@WebServlet("/GetFixedDeposit")
public class GetFixedDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFixedDeposit() {
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

 		String depositID = request.getParameter("depositID");
		IFixedDeposit iFixedDeposit = new FixedDepositImpl();
		FixedDeposit fixeddeposit = iFixedDeposit.getFixedDepositID(depositID);

		request.setAttribute("fixeddeposit", fixeddeposit);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/GetFixedDeposit.jsp");
		dispatcher.forward(request, response);
	}
	
}