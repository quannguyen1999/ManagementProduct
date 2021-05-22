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

//Tất cả các request phải qua class này thực thi trước 
@WebFilter("*")
public class SecurityFilter implements Filter{
	private ServletContext context;


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

		boolean loggedIn = session != null && session.getAttribute("loginSuccess") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		boolean logoutURL = request.getRequestURI().equals(logoutURI);

		if(logoutURL) {
			session=request.getSession();  
			session.invalidate();  
			response.sendRedirect(loginURI);
		}else if(loginRequest && loggedIn) {
			response.sendRedirect(request.getContextPath() + "/listProduct");
		}else if (
				//tất cả request cho phép đều vào đây
				loggedIn || 
				loginRequest || 
				request.getRequestURI().equals(request.getContextPath()+"/")) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURI);
		}
	}

	public void destroy() {
		//close any resources here
	}
}
