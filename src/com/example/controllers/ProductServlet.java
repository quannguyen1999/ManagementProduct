package com.example.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.dao.ProductDao;
import com.example.dao.impl.ProductImpl;
import com.example.models.CustomError;
import com.example.models.Product;
import com.example.models.TypeConditionProduct;
import com.example.validations.ProductValidator;

//định nghịa các url được phép vào servlet này
@WebServlet(urlPatterns = {
		"/product",
		"/product-new",
		"/product-insert",
"/product-detail"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//gọi các service
	private static ProductDao productService = new ProductImpl();
	
	//định nghĩa các dường dẫn file jsp
	public static String LIST_PRODUCT = "product.jsp";
	public static String ADD_PRODUCT = "addProduct.jsp";
	public static String DETAIL_PRODUCT = "detailProduct.jsp";
	
	public static String ERROR_404 = "404.jsp";

	//các request post
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			//thêm sản phẩm 
			case "/product-insert":
				reqre = insertBook(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		reqre.forward(request, response);
	} 


	//các request get
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			//khởi tạo product mới
			case "/product-new":
				reqre =  showNewProduct(request, response);
				break;
			//phòng trường hợp người dùng gọi insert thì tự động khởi tạo product mới
			case "/product-insert":
				reqre =  showNewProduct(request, response);
				break;
			//show detail sản phẩm
			case "/product-detail":
				reqre =  showDetailProduct(request, response);
				break;
			//hiển thị danh sách sản phẩm
			case "/product":
				reqre =  showListProduct(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		reqre.forward(request, response);
	}

	//hiển thì danh sách sản phẩm
	private RequestDispatcher showListProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listProduct", productService.listProducts());
		return request.getRequestDispatcher(LIST_PRODUCT);
	}

	//khởi tạo product
	private RequestDispatcher showNewProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return request.getRequestDispatcher(ADD_PRODUCT);
	}

	//show detail product
	private RequestDispatcher showDetailProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").isEmpty() ? "-1" : request.getParameter("id"));
		Product product = productService.findById(id);
		//nếu không tìm thấy thì trả về trang 404
		if(product == null) {
			return request.getRequestDispatcher(ERROR_404);
		}
		request.setAttribute("product",product);
		return request.getRequestDispatcher(DETAIL_PRODUCT);
	}

	//thêm sản phẩm vào database
	private RequestDispatcher insertBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher req = null;
		HttpSession session = request.getSession();
		
		//lấy dữ liệu từ request
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

		//sau đó gán vào product
		Product product = new Product(name, unitPrice, unitInStock, description, manufacturer, category,
				typeConditionProduct, null);

		//kiểm tra dữ liệu
		List<CustomError> listCustomErrors = ProductValidator.validateInsertProduct(product, request);

		//nếu có lỗi thì trả về danh sách các lỗi
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
