package com.example.controllers;

import java.sql.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.dao.MyEntityManager;
import com.example.models.Account;
import com.example.models.Customer;
import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.models.OrderProductPK;
import com.example.models.Product;
import com.example.models.TypeAccount;
import com.example.models.TypeConditionProduct;
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

public class InitData {
	private static Validator validator;
	public static void main(String[] args) {
		AccountService accountService = new AccountDao();
//		CustomerService customerService = new CustomerDao();
		ProductService productService = new ProductDao();
		OrderService orderService = new OrderDao();
		OrderDetailService orderDetailService = new OrderDetailDao();
//		Account accountAdmin = new Account("admin", accountService.hashPassword("Khanhhoa123@"), TypeAccount.ADMIN);
		Account accountClient = new Account("quannda1", accountService.hashPassword("Khanhhoa123@"), TypeAccount.USER);
		Customer customer = new Customer("C101", "33/16", "HCM", "nguyendang1@gmail.com",
				"quan", "nguyen", "0905360857", accountClient);

		Product product = productService.findById(1);
		String orderId = orderService.createRandomIdOrder();
		OrderProductPK orderProductPK = new OrderProductPK(orderId, product.getProductId());
		OrderProduct orderProduct = new OrderProduct(orderId, customer, new Date(System.currentTimeMillis()), 0);
		OrderDetail orderDetail = new OrderDetail(orderProductPK, 0, 0f, orderProduct, product);
		
		orderService.insert(orderProduct);
		orderDetailService.insert(orderDetail);
	}
}
