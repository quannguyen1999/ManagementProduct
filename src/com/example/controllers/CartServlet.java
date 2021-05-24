package com.example.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.models.Account;
import com.example.models.Customer;
import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.models.OrderProductPK;
import com.example.models.Product;
import com.example.models.TypeAccount;
import com.example.services.AccountService;
import com.example.services.CustomerService;
import com.example.services.OrderDetailService;
import com.example.services.OrderService;
import com.example.services.ProductService;
import com.example.services.impl.AccountDao;
import com.example.services.impl.CustomerDao;
import com.example.services.impl.OrderDao;
import com.example.services.impl.OrderDetailDao;
import com.example.services.impl.ProductDao;

@WebServlet(urlPatterns = {
		"/cart",
		"/cart-order",
		"/cart-order-detail",
		"/cart-clear",
		"/cart-remove",
"/cart-checkout"})
public class CartServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductDao();

	private OrderService orderService = new OrderDao();

	private CustomerService customerService = new CustomerDao();

	private OrderDetailService orderDetailService = new OrderDetailDao();

	private static String LIST_CART = "listCart.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/cart-order":
				reqre = insertCart(request, response);
				response.sendRedirect(request.getContextPath()+"/product");
				return;
			case "/cart":
				reqre = listCart(request, response);
				reqre.forward(request, response);
				return;
			case "/cart-remove":
				removeOneItem(request, response);
				response.sendRedirect(request.getContextPath()+"/cart");
				return;
			case "/cart-clear":
				clearItem(request, response);
				response.sendRedirect(request.getContextPath()+"/cart");
				return;
			case "/cart-checkout":
				checkoutCart(request, response);
				response.sendRedirect(request.getContextPath()+"/cart");
				break;
			case "/cart-order-detail":
				reqre = insertCart(request, response);
				response.sendRedirect(request.getContextPath()+"/product-detail?id="+request.getParameter("id"));
				return;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void checkoutCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		Customer customer = customerService.findByUserName((String)session.getAttribute("username"));
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		Float tong = 0f;
		if(listProducts.size() >= 1) {
			for(Product product: listProducts) {
				tong += product.getUnitPrice()*product.getUnitInStock(); 
			}
			String idOrder = orderService.createRandomIdOrder();
			OrderProduct orderProduct = new OrderProduct(idOrder,customer, new Date(System.currentTimeMillis()), tong);
			orderService.insert(orderProduct);
			for(Product product: listProducts) {
				OrderProductPK orderProductPK = new OrderProductPK(idOrder, product.getProductId());
				OrderDetail orderDetail = new OrderDetail(orderProductPK, product.getUnitInStock(),
						product.getUnitPrice(), orderProduct, productService.findById(product.getProductId()));
				orderDetailService.insert(orderDetail);
			}
		}
		session.setAttribute("listProducts", null);
	}


	private RequestDispatcher listCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher req = null;
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		request.setAttribute("listProducts", listProducts);
		return request.getRequestDispatcher(LIST_CART);
	}

	private void clearItem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("listProducts", null);
	}

	private void removeOneItem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		int id = Integer.parseInt(request.getParameter("id").isEmpty() ? "-1" : request.getParameter("id"));
		for(int i=0;i<listProducts.size();i++) {
			if(listProducts.get(i).getProductId() == id) {
				listProducts.remove(listProducts.get(i));
			}
		}
		session.setAttribute("listProducts", listProducts);
	}
	
	private RequestDispatcher insertCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher req = null;
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		int id = Integer.parseInt(request.getParameter("id").isEmpty() ? "-1" : request.getParameter("id"));
		//get current product in database
		Product pro = productService.findById(id);
		
		//get current product and set unit in stock is 1
		Product product = new Product(pro.getProductId(),pro.getName(),
				pro.getUnitPrice(), 
				1, 
				pro.getDescription(), 
				pro.getManufacturer(), 
				pro.getCategory(),
				pro.getTypeConditionProduct(), pro.getImage());
		if(listProducts == null) {
			listProducts = new ArrayList<>();
			if(pro.getUnitInStock() <= 0) {
				request.setAttribute("error", "product out of stock");
			}else {
				listProducts.add(product);
			}
		}else {
			Boolean result = true;
			for(int i=0;i<listProducts.size();i++) {
				if(listProducts.get(i).getProductId() == id) {
					if(listProducts.get(i).getUnitInStock()<pro.getUnitInStock()) {
						listProducts.get(i).setUnitInStock(listProducts.get(i).getUnitInStock()+1);
						result=false;
						break;
					}else {
						request.setAttribute("error", "product not enough stock");
						result=false;
						break;
					}
				}
			}
			if(result==true) {
				listProducts.add(product);
			}
		}
		session.setAttribute("addSuccess", "addSuccess");
		session.setAttribute("listProducts", listProducts);
		return request.getRequestDispatcher(ProductServlet.LIST_PRODUCT);
	}
}
