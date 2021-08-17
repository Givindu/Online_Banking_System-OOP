package com.oop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oop.model.Transaction;
import com.oop.service.TransactionService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		
		String formUsername = request.getParameter("form-username");
		String formPassword = request.getParameter("form-password");
		
		Transaction transfer=new Transaction();
		transfer.setAccountNo1(request.getParameter("form-username"));
		request.setAttribute("transaction", transfer);
		
		TransactionService iService = new TransactionService();
		HttpSession userSession = request.getSession(false);
		RequestDispatcher forwardUser = null;

		if(!("Username".equals(formUsername) && "Password".equals(formPassword))){
			
			try {
				
				if (iService.getUserAuthenticate(formUsername, formPassword)) {
					
					userSession = request.getSession(true);
					userSession.setAttribute("userName", formUsername);
					userSession.setAttribute("userStatus", "true");
					userSession.setAttribute("user", "user");
					forwardUser = getServletContext().getRequestDispatcher("/WEB-INF/views/AccountPage.jsp");
					forwardUser.forward(request, response);
					
				} else {
					
					userSession.setAttribute("status", "false");
					forwardUser = getServletContext().getRequestDispatcher("/Login.jsp");
					PrintWriter out = response.getWriter();
					forwardUser.include(request, response);
					out.println("<script>alert('Incorrect credentials')</script>");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			
			forwardUser = getServletContext().getRequestDispatcher("/Login.jsp");
			PrintWriter out = response.getWriter();
			forwardUser.include(request, response);
			out.println("<script>alert('Please enter details')</script>");
		
		}
	}

}
