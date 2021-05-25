package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.dao.CustomerDao;
import com.example.dao.OrderDetailDao;
import com.example.dao.OrderDao;
import com.example.dao.ProductDao;
import com.example.dao.impl.CustomerImpl;
import com.example.dao.impl.OrderImpl;
import com.example.dao.impl.OrderDetailImpl;
import com.example.dao.impl.ProductImpl;
import com.example.models.Customer;
import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.models.OrderProductPK;
import com.example.models.Product;

//định nghịa các url được phép vào servlet này
@WebServlet(urlPatterns = {
		"/cart",
		"/cart-order",
		"/cart-order-detail",
		"/cart-clear",
		"/cart-remove","/cart-checkout"})
public class CartServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//gọi các service
	private ProductDao productService = new ProductImpl();
	private OrderDao orderService = new OrderImpl();
	private CustomerDao customerService = new CustomerImpl();
	private OrderDetailDao orderDetailService = new OrderDetailImpl();

	//định nghĩa các dường dẫn file jsp
	private static String LIST_CART = "listCart.jsp";

	//các request cho cart sẽ vào đây (method:get)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher reqre = null;
		String action = request.getServletPath();
		try {
			switch (action) {
			//thêm product vào cart
			//sau đó chuyển hướng lại trang product
			case "/cart-order":
				reqre = insertCart(request, response);
				response.sendRedirect(request.getContextPath()+"/product");
				return;
				//hiển thị danh sách cart
				//sau đó forward tới cart.jsp
			case "/cart":
				reqre = listCart(request, response);
				reqre.forward(request, response);
				return;
				//xóa cart qua id 
				//sau đó redirect lại trang cart
			case "/cart-remove":
				removeOneItem(request, response);
				response.sendRedirect(request.getContextPath()+"/cart");
				return;
				//xóa hết product trong cart
				//sau đó redirect lại trang cart
			case "/cart-clear":
				clearItem(request, response);
				response.sendRedirect(request.getContextPath()+"/cart");
				return;
				//tiến hành checkout cart
				//sau đó redirect lại trang cart
			case "/cart-checkout":
				checkoutCart(request, response);
				System.out.println("redirect cart");
				response.sendRedirect(request.getContextPath()+"/cart");
				break;
				//thêm product vào cart
				//sau đó chuyển hướng lại trang product
			case "/cart-order-detail":
				reqre = insertCart(request, response);
				response.sendRedirect(request.getContextPath()+"/product-detail?id="+request.getParameter("id"));
				return;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	//thực hiện checkout cart
	private void checkoutCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//lấy session hiện tại
		HttpSession session = request.getSession();
		
		//sau đó tìm thông tin customer thông qua username trong session
		Customer customer = customerService.findByUserName((String)session.getAttribute("username"));
		
		//show tất product trong cart thông qua session
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		
		//khởi tạo tống giá tất cả sản phẩm
		Float tong = 0f;
		
		//nếu sản phẩm không có gì thì trả về error, còn không thì tiếp tục
		if(listProducts != null && listProducts.size() >= 1) {
			//tính tổng tiền
			for(Product product: listProducts) {
				tong += product.getUnitPrice()*product.getUnitInStock(); 
			}
			
			//tạo random id order
			String idOrder = orderService.createRandomIdOrder();
			
			//gán id order vào OrderProduct
			OrderProduct orderProduct = new OrderProduct(idOrder,customer, new Date(System.currentTimeMillis()), tong);
			
			//thêm order và database
			orderService.insert(orderProduct);
			
			//sau đó thêm từng orderdetail vào 
			//product sẽ tự động cập nhập thông qua trigger
			for(Product product: listProducts) {
				//khóa chính của 2 bảng là order và product
				OrderProductPK orderProductPK = new OrderProductPK(idOrder, product.getProductId());
				OrderDetail orderDetail = new OrderDetail(orderProductPK, product.getUnitInStock(),
						product.getUnitPrice(), orderProduct, productService.findById(product.getProductId()));
				orderDetailService.insert(orderDetail);
			}
			
			//thêm success vào session để thông báo người dùng
			session.setAttribute("success", "checkout success");
			
			//reset là listProduct là null
			session.setAttribute("listProducts", null);
		}else {
			session.setAttribute("error", "checkout don't have any item");
		}
	}

	//hiển thị danh sách cart
	private RequestDispatcher listCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		//lấy danh sách product trong session và set lại vào attribute
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		request.setAttribute("listProducts", listProducts);
		return request.getRequestDispatcher(LIST_CART);
	}

	//clear cart 
	private void clearItem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("listProducts", null);
	}

	//xóa một item trong cart
	private void removeOneItem(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		int id = Integer.parseInt(request.getParameter("id").isEmpty() ? "-1" : request.getParameter("id"));
		for(int i=0;i<listProducts.size();i++) {
			if(listProducts.get(i).getProductId() == id) {
				//nếu xóa thành công thì trả về success
				listProducts.remove(listProducts.get(i));
				session.setAttribute("success", "remove success");
				break;
			}
		}
		//set lại list product trong session
		session.setAttribute("listProducts", listProducts);
	}
	
	//thêm product vào cart
	private RequestDispatcher insertCart(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<Product> listProducts = (List<Product>) session.getAttribute("listProducts");
		
		//lấy id từ paramater, nếu là null hay rỗng thì mặc định trả về -1		
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
		//nếu list sản phẩm là null thì khởi tạo mới lại
		if(listProducts == null) {
			listProducts = new ArrayList<>();
			//nếu hết hàng thì báo lỗi, ngược lại thì thêm vào
			if(pro.getUnitInStock() <= 0) {
				request.setAttribute("error", "product out of stock");
			}else {
				session.setAttribute("success", "add success");
				listProducts.add(product);
			}
		}else {
			//khởi tạo result để biết được tình trạng sản phẩm có thêm vào cart được hay không
			Boolean result = true;
			for(int i=0;i<listProducts.size();i++) {
				if(listProducts.get(i).getProductId() == id) {
					if(listProducts.get(i).getUnitInStock()<pro.getUnitInStock()) {
						session.setAttribute("success", "add success");
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
				session.setAttribute("success", "add success");
			}
		}
		//sau đó set list cart vào trong session
		session.setAttribute("listProducts", listProducts);
		
		//sau đó chuyển hướng sang trang list product
		return request.getRequestDispatcher(ProductServlet.LIST_PRODUCT);
	}
}
