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

import com.example.dao.AccountDao;
import com.example.dao.impl.AccountImpl;
import com.example.models.Account;
import com.example.models.CustomError;
import com.example.models.TypeAccount;
import com.example.validations.AccountValidator;

//định nghịa các url được phép vào servlet này
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//định nghĩa các dường dẫn file jsp
	private static String LOGIN_PAGE = "login.jsp";
	
	//gọi các service
	private AccountDao accountService = new AccountImpl();

	//thực hiên chức năng đăng nhập (post)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		RequestDispatcher req = null;
		String name=request.getParameter("username");  
		String password=request.getParameter("password");
		Account accountFind = null;
		
		//xác minh dữ liệu
		List<CustomError> listCustomErrors = AccountValidator.validateCreate(new Account(name, password, null));

		//nếu có lỗi thì trả về lại trang login
		if(listCustomErrors.size() >= 1) {
			request.setAttribute("listCustomerError", listCustomErrors);
			req = request.getRequestDispatcher(LOGIN_PAGE);
		}else {
			//ngược lại nếu thành công thì chuyển hướng sang trang thích hợp
			accountFind = accountService.findById(name);
			HttpSession session = request.getSession();
			session.setAttribute("username", name);
			session.setAttribute("typeUser", accountFind.getTypeAccount().toString());
			if(accountFind.getTypeAccount().equals(TypeAccount.ADMIN)) {
				response.sendRedirect(request.getContextPath()+"/product-new");
			}else {
				response.sendRedirect(request.getContextPath()+"/product");
			}
			return;
		}
		req.forward(request, response);
	}  

	//chuyển hướng tới trang login (get)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		reqre =  request.getRequestDispatcher(LOGIN_PAGE);
		reqre.forward(request, response);
	}
}