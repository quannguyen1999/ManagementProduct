package com.example.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.models.Account;
import com.example.models.CustomError;
import com.example.validations.AccountValidator;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String LOGIN_PAGE = "login.jsp";
	private static String LOGIN_SUCCESS = "loginSuccess.jsp";

	private AccountValidator validatorAccount = new AccountValidator();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		RequestDispatcher req = null;
		String name=request.getParameter("username");  
		String password=request.getParameter("password");

		List<CustomError> listCustomErrors = validatorAccount.validateCreate(new Account(name, password, null));

		if(listCustomErrors.size() >= 1) {
			request.setAttribute("listCustomerError", listCustomErrors);
			req = request.getRequestDispatcher(LOGIN_PAGE);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginSuccess", name);
			req = request.getRequestDispatcher(LOGIN_SUCCESS);
		}

		req.forward(request, response);
	}  

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			default:
				reqre =  request.getRequestDispatcher(LOGIN_PAGE);
				reqre.forward(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}