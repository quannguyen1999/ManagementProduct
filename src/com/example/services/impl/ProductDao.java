package com.example.services.impl;

import java.util.List;

import com.example.models.Account;
import com.example.models.Product;
import com.example.services.GenericService;
import com.example.services.ProductService;

public class ProductDao implements ProductService{

	GenericService<Product> genericService;
	
	public ProductDao() {
		super();
		genericService = new GenericDao<>(Product.class);
	}
	
	@Override
	public List<Product> listProducts() {
		return  genericService.list();
	}

	@Override
	public Product findById(String id) {
		return genericService.findOne(id);
	}

	@Override
	public void insert(Product product) {
		genericService.insert(product);
	}

}
