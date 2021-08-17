package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.service.FixedDepositImpl;
import com.oop.service.IFixedDeposit;

@WebServlet("/DeleteFixedDeposit")
public class DeleteFixedDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteFixedDeposit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		String depositID = request.getParameter("depositID");			
		
		IFixedDeposit iFixedDeposit = new FixedDepositImpl();
		iFixedDeposit.removeFixedDeposit(depositID);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFixedDepositDeleteRequests.jsp");
		dispatcher.forward(request, response);
			
	}
	}
