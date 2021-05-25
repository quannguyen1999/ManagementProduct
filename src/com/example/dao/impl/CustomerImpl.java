package com.example.dao.impl;

import com.example.dao.CustomerDao;
import com.example.dao.GenericDao;
import com.example.models.Customer;

public class CustomerImpl implements CustomerDao{
	//cung cấp sẵn có method CRUD trong hibernate
	GenericDao<Customer> genericService;

	public CustomerImpl() {
		super();
		genericService = new GenericImpl<>(Customer.class);
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
