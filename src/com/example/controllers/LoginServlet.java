package com.example.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.models.Account;
import com.example.models.CustomError;
import com.example.services.AccountService;
import com.example.services.impl.AccountDao;
import com.example.validations.ValidatorAccount;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AccountService accountDao = new AccountDao();
	
	private ValidatorAccount validatorAccount = new ValidatorAccount();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		RequestDispatcher req = null;
		String name=request.getParameter("username");  
		String password=request.getParameter("password");

		List<CustomError> listCustomErrors = validatorAccount.validateCreate(new Account(name, password, null));
		
		if(listCustomErrors.size() >= 1) {
			request.setAttribute("listCustomerError", listCustomErrors);
			req = request.getRequestDispatcher("login.jsp");
		}else {
			req = request.getRequestDispatcher("loginSuccess.jsp");
		}
		
		req.forward(request, response);
	}  
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = request.getRequestDispatcher("login.jsp");
		reqre.forward(request, response);
	}
}