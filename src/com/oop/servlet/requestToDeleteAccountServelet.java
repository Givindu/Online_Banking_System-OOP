package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Account;
import com.oop.service.AccountServicelmpl;
import com.oop.service.NAccountService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/requestToDeleteAccountServelet")
public class requestToDeleteAccountServelet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public requestToDeleteAccountServelet() {
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

		Account account = new Account();
		
		
		account.setAccount_number(request.getParameter("Accountnumber"));		

		NAccountService nAccountService = new AccountServicelmpl();
		nAccountService.requestToDelete(account.getAccount_number());

		request.setAttribute("account", account);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Delete.jsp");
		dispatcher.forward(request, response);
	}

}


