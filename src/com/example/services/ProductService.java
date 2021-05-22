package com.example.services;

import java.util.List;

import com.example.models.Product;

public interface ProductService {
	List<Product> listProducts();
	Product findById(String id);
	void insert(Product product);
}
