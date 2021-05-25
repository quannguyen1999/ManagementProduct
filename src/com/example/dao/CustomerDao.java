package com.example.dao;

import com.example.models.Customer;

public interface CustomerDao {
	void insert(Customer customer);
	
	Customer findById(String id);
	
	Customer findByUserName(String username);
}
