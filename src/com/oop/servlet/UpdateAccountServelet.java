package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.exception.AgeException;
import com.oop.model.Account;
import com.oop.service.AccountServicelmpl;
import com.oop.service.NAccountService;

/**
 * Update employees servlet
 */
@WebServlet("/UpdateAccountServelet")
public class UpdateAccountServelet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAccountServelet() {
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
		String Account_number = request.getParameter("Account number");	
		account.setAccount_number(Account_number);
		account.setOwner_name(request.getParameter("Owner Name"));
		account.setNic(request.getParameter("National ID"));
		account.setAge(request.getParameter("Age"));
		account.setGender(request.getParameter("Gender"));
		System.out.println("sevr"+request.getParameter("Gender"));
		account.setAcc_type(request.getParameter("Account Type"));
		account.setAcc_balance(Integer.parseInt(request.getParameter("Account balance")));
		
		NAccountService nAccountService = new AccountServicelmpl();
		RequestDispatcher forwardUser = null;
		
		try {
			nAccountService.updateAccount(Account_number,account);
			request.setAttribute("Account", account);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Getaccount.jsp");
			dispatcher.forward(request, response);
		}catch(AgeException e){
			request.setAttribute("Account", account);
			forwardUser = getServletContext().getRequestDispatcher("/WEB-INF/views/Getaccount.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Age must be above 18')</script>");
		}
	}

}
