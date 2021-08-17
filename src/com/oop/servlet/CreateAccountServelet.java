package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Account;
import com.oop.exception.AgeException;
import com.oop.service.AccountServicelmpl;
import com.oop.service.NAccountService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/CreateAccountServelet")
public class CreateAccountServelet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccountServelet() {
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
		
		
		account.setOwner_name(request.getParameter("ownername"));
		account.setNic(request.getParameter("NationalID"));
		account.setAge(request.getParameter("Age"));
		account.setGender(request.getParameter("gender"));
		account.setAcc_type(request.getParameter("accounttype"));
		account.setAcc_balance(Integer.parseInt(request.getParameter("accountbalance")));
		account.setPassword(request.getParameter("password"));

		NAccountService nAccountService = new AccountServicelmpl();
		RequestDispatcher forwardUser = null;
		
		try{
		nAccountService.createAccount(account);
		request.setAttribute("Account", account);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Getaccount.jsp");
		dispatcher.forward(request, response);
		}catch(AgeException e){
			forwardUser = getServletContext().getRequestDispatcher("/homeView.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Age must be above 18')</script>");
		}
		

		
	}

}

