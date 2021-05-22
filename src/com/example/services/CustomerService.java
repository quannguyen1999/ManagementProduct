package com.example.services;

import com.example.models.Customer;

public interface CustomerService {
	void insert(Customer customer);
	
	Customer findById(String id);
}
