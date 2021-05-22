package com.example.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/","/listProduct" })
public class ProductServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String LIST_PRODUCT = "product.jsp";
	public static String ADD_PRODUCT = "addProduct.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewProduct(request, response);
				break;
			case "/insert":
				break;
			case "/delete":
				break;
			case "/edit":
				break;
			case "/update":
				break;
			default:
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	} 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = request.getRequestDispatcher(LIST_PRODUCT);
		reqre.forward(request, response);
	}

	private void showNewProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(ADD_PRODUCT);
		dispatcher.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.sendRedirect("list");
	}
}
