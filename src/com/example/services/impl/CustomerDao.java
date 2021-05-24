package com.example.services.impl;

import com.example.models.Account;
import com.example.models.Customer;
import com.example.services.CustomerService;
import com.example.services.GenericService;

public class CustomerDao implements CustomerService{
	GenericService<Customer> genericService;

	public CustomerDao() {
		super();
		genericService = new GenericDao<>(Customer.class);
	}
	@Override
	public void insert(Customer customer) {
		genericService.insert(customer);
	}
	@Override
	public Customer findById(String id) {
		return genericService.findOne(id);
	}
	@Override
	public Customer findByUserName(String username) {
		return genericService.Query("from Customer where account like '"+username+"'");
	}

}
