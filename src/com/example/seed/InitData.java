package com.example.seed;

import java.sql.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.dao.AccountDao;
import com.example.dao.CustomerDao;
import com.example.dao.OrderDetailDao;
import com.example.dao.OrderDao;
import com.example.dao.ProductDao;
import com.example.dao.impl.AccountImpl;
import com.example.dao.impl.CustomerImpl;
import com.example.dao.impl.OrderImpl;
import com.example.dao.impl.OrderDetailImpl;
import com.example.dao.impl.ProductImpl;
import com.example.models.Account;
import com.example.models.Customer;
import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.models.OrderProductPK;
import com.example.models.Product;
import com.example.models.TypeAccount;
import com.example.models.TypeConditionProduct;
import com.example.util.MyEntityManager;


//khởi tạo dữ liệu
public class InitData {
	public static void main(String[] args) {
		AccountDao accountService = new AccountImpl();
		CustomerDao customerService = new CustomerImpl();
		Account accountAdmin = new Account("admin", accountService.hashPassword("Khanhhoa123@"), TypeAccount.ADMIN);
		Account accountClient = new Account("quannda1", accountService.hashPassword("Khanhhoa123@"), TypeAccount.USER);
		Customer customer = new Customer("C101", "33/16", "HCM", "nguyendang1@gmail.com",
				"quan", "nguyen", "0905360857", accountClient);
		if(accountService.findById(accountAdmin.getUserName()) == null) {
			accountService.insert(accountAdmin);
		}
		
		if(accountService.findById(accountClient.getUserName()) == null) {
			accountService.insert(accountClient);
			customerService.insert(customer);
		}
	}
}
