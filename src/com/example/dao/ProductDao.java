package com.example.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.models.Product;

public interface ProductDao {
	List<Product> listProducts();
	Product findById(int id);
	
	//thêm sản phẩm
	//request: dùng để lấy ảnh về từ request
	void insert(Product product, HttpServletRequest request);
	
	void updateProduct(Product product);
}
