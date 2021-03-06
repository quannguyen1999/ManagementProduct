package com.example.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.models.TypeAccount;

//Tất cả các request phải qua class này thực thi trước 
@WebFilter("*")
public class SecurityFilter implements Filter{
	private ServletContext context;

	//định nghĩa các đường dẫn file error
	private static String ERROR_403 = "403.jsp";
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);
		
		String loginURI = request.getContextPath() + "/login";
		String logoutURI = request.getContextPath() + "/logout";
		String productURI = request.getContextPath() + "/product";
		String productDetailURL = request.getContextPath() + "/product-detail";

		boolean loggedIn = session != null && session.getAttribute("username") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		boolean logoutRequest = request.getRequestURI().equals(logoutURI);
		boolean productRequest = request.getRequestURI().equals(productURI);
		boolean productDetailRequest = request.getRequestURI().equals(productDetailURL);
		if (request.getRequestURI().endsWith(".png") || request.getRequestURI().endsWith(".jpg")
				|| request.getRequestURI().endsWith(".PNG")) {
		    chain.doFilter(request, response);
		    return;
		}
		if(logoutRequest) {
			session=request.getSession();  
			session.invalidate();  
			response.sendRedirect(loginURI);
		}else if(productRequest || productDetailRequest) {
			chain.doFilter(request, response);
		}else if(loginRequest) {
			if(session != null && session.getAttribute("username") != null) {
				response.sendRedirect(productURI);
			}else {
				chain.doFilter(request, response);
			}
		}else if(session != null && session.getAttribute("username") != null) {
			checkRole(session, request, response, chain);
		}else {
			session = request.getSession();
			session.setAttribute("message","please login first");
			response.sendRedirect(loginURI);
		}
	}
	
	public void handlerError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		reqre =  request.getRequestDispatcher(ERROR_403);
		reqre.forward(request, response);
		return;
	}
	
	public void checkRole(HttpSession session, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		TypeAccount typeUser = TypeAccount.valueOf((String)session.getAttribute("typeUser"));
		//define url for admin
		String productList = request.getContextPath() + "/product";
		String productNew = request.getContextPath() + "/product-new";
		String productInsert = request.getContextPath() + "/product-insert";
		String productDetail = request.getContextPath() + "/product-detail";
		
		String cartList = request.getContextPath() +"/cart";
		String cartOrder = request.getContextPath() +"/cart-order";
		String cartOrderDetail = request.getContextPath() +"/cart-order-detail";
		String cartClear = request.getContextPath() +"/cart-clear";
		String cartRemove = request.getContextPath() +"/cart-remove";
		String cartCheckout = request.getContextPath() +"/cart-checkout";
		
		switch (typeUser.toString()) {
		case "ADMIN":
			if(request.getRequestURI().equals(productNew) ||
					request.getRequestURI().equals(productInsert)
					) {
				chain.doFilter(request, response);
			}else {
				handlerError(request, response);
				return;
			}
			break;
		default:
			if(request.getRequestURI().equals(cartList) ||
					request.getRequestURI().equals(cartOrder) ||
					request.getRequestURI().equals(cartOrderDetail) ||
					request.getRequestURI().equals(cartClear) ||
					request.getRequestURI().equals(cartRemove) ||
					request.getRequestURI().equals(cartCheckout) ||
					request.getRequestURI().equals(productList) ||
					request.getRequestURI().equals(productDetail)
					) {
				chain.doFilter(request, response);
			}else {
				handlerError(request, response);
				return;
			}
			break;
		}
	}

	public void destroy() {
		//close any resources here
	}
}
