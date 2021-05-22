package com.example.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/product" })
public class ProductServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String LIST_PRODUCT = "product.jsp";
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		RequestDispatcher req = null;
		req = request.getRequestDispatcher(LIST_PRODUCT);
		req.forward(request, response);
	}  
}
