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

import com.example.controllers.ProductServlet;
import com.example.models.Account;
import com.example.models.TypeAccount;

//Tất cả các request phải qua class này thực thi trước 
//@WebFilter("*")
public class SecurityFilter{// implements Filter{
//	private ServletContext context;
//
//
//	public void init(FilterConfig fConfig) throws ServletException {
//		this.context = fConfig.getServletContext();
//		this.context.log("AuthenticationFilter initialized");
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) res;
////		HttpSession session = request.getSession(false);
////		String loginURI = request.getContextPath() + "/login";
////		String logoutURI = request.getContextPath() + "/logout";
////
////		boolean loggedIn = session != null && session.getAttribute("username") != null;
////		boolean loginRequest = request.getRequestURI().equals(loginURI);
////		boolean logoutURL = request.getRequestURI().equals(logoutURI);
////
////		if(logoutURL) {
////			session=request.getSession();  
////			session.invalidate();  
////			response.sendRedirect(loginURI);
////		}else if(loginRequest && loggedIn) {
//////			response.sendRedirect(request.getContextPath() + "/listProduct");
////		}else if (
////				//tất cả request cho phép đều vào đây
////				loggedIn || 
////				loginRequest || 
////				request.getRequestURI().equals(request.getContextPath()+"/")) {
//			chain.doFilter(request, response);
////		} else {
////			response.sendRedirect(loginURI);
////		}
//	}
//	
//	public void checkRole(HttpSession session, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String username = (String)session.getAttribute("username");
//		TypeAccount typeUser = TypeAccount.valueOf((String)session.getAttribute("typeUser"));
//		
//		//error
//		String errorDenied = request.getContextPath() + "/error";
//		
//		//define url for admin
//		String createNewProduct = request.getContextPath() + "/listProduct/new";
//		String insertProduct = request.getContextPath() + "/listProduct/insert";
//		
//		switch (typeUser.toString()) {
//		case "ADMIN":
//			if(request.getRequestURI().equals(createNewProduct)) {
//				chain.doFilter(request, response);
//			}else if(request.getRequestURI().equals(insertProduct)) {
//				chain.doFilter(request, response);
//			}else {
//				response.sendRedirect(errorDenied);
//			}
//			break;
//		default:
//			break;
//		}
//	}
//
//	public void destroy() {
//		//close any resources here
//	}
}
