package com.example.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.models.Account;
import com.example.models.CustomError;
import com.example.models.Product;
import com.example.models.TypeConditionProduct;
import com.example.services.FilesStorageService;
import com.example.services.ProductService;
import com.example.services.impl.FileStorageDao;
import com.example.services.impl.ProductDao;
import com.example.validations.AccountValidator;
import com.example.validations.ProductValidator;

@WebServlet(urlPatterns = {
		"/product",
		"/product-new",
		"/product-insert",
"/product-detail"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet{
	private static ProductService productService = new ProductDao();
	private static final long serialVersionUID = 1L;
	public static String LIST_PRODUCT = "product.jsp";
	public static String ADD_PRODUCT = "addProduct.jsp";
	public static String DETAIL_PRODUCT = "detailProduct.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		System.out.println("product request");
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/product-insert":
				reqre = insertBook(request, response);
				break;
			default:
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		reqre.forward(request, response);
	} 


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/product-new":
				reqre =  showNewProduct(request, response);
				break;
			case "/product-insert":
				reqre =  showNewProduct(request, response);
				break;
			case "/product-detail":
				reqre =  showDetailProduct(request, response);
				break;
			case "/product":
				reqre =  showListProduct(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		reqre.forward(request, response);
	}

	private RequestDispatcher showListProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		if(session != null) {
//			Boolean addSuccessRequest =  session.getAttribute("success") != null;
//			if(addSuccessRequest) {
//				String type = (String) session.getAttribute("success");
//				if(type != null) {
//					request.setAttribute("success", "addSuccess");
//					session.setAttribute("success", null);
//				}
//			}
//		}
		request.setAttribute("listProduct", productService.listProducts());
		return request.getRequestDispatcher(LIST_PRODUCT);
	}

	private RequestDispatcher showNewProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return request.getRequestDispatcher(ADD_PRODUCT);
	}

	private RequestDispatcher showDetailProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").isEmpty() ? "-1" : request.getParameter("id"));
		Product product = productService.findById(id);
		if(product == null) {
			return request.getRequestDispatcher("404.jsp");
		}
		request.setAttribute("product",product);
		return request.getRequestDispatcher(DETAIL_PRODUCT);
	}

	private RequestDispatcher insertBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("insert book");
		RequestDispatcher req = null;
		HttpSession session = request.getSession();
		String name=request.getParameter("name");  
		Float unitPrice = Float.parseFloat(request.getParameter("unitPrice").isEmpty() ? "0" : request.getParameter("unitPrice"));
		int unitInStock = Integer.parseInt(request.getParameter("unitInStock").isEmpty() ? "0" : request.getParameter("unitInStock"));
		String description =  request.getParameter("description");
		String manufacturer =  request.getParameter("manufacturer");
		String category =  request.getParameter("category");
		int typeConditionProductNumber = Integer.parseInt(request.getParameter("typeConditionProduct").isEmpty() ? "0" :request.getParameter("typeConditionProduct"));

		TypeConditionProduct typeConditionProduct;
		if(typeConditionProductNumber == 0) {
			typeConditionProduct = TypeConditionProduct.NEW;
		}else if(typeConditionProductNumber == 1) {
			typeConditionProduct = TypeConditionProduct.OLD;
		}else {
			typeConditionProduct = TypeConditionProduct.REFURBISHED;
		}

		Product product = new Product(name, unitPrice, unitInStock, description, manufacturer, category,
				typeConditionProduct, null);

		List<CustomError> listCustomErrors = ProductValidator.validateInsertProduct(product, request);

		if(listCustomErrors.size() >= 1) {
			request.setAttribute("listCustomerError", listCustomErrors);
			return request.getRequestDispatcher(ADD_PRODUCT);
		}else {
			productService.insert(product, request);
			session.setAttribute("success", "add success");
			return request.getRequestDispatcher(ADD_PRODUCT);
		}
	}
}
