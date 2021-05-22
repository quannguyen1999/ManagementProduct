package com.example.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.models.Product;

public interface ProductService {
	List<Product> listProducts();
	Product findById(int id);
	void insert(Product product, HttpServletRequest request);
	void updateProduct(Product product);
}
