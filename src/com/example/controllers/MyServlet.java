package com.example.controllers;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class MyServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Hello Readers</h1>");
		out.println("</body></html>");
	}
}
