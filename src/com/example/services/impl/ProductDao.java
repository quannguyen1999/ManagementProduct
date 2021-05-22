package com.example.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.models.Account;
import com.example.models.Product;
import com.example.services.FilesStorageService;
import com.example.services.GenericService;
import com.example.services.ProductService;

public class ProductDao implements ProductService{

	GenericService<Product> genericService;
	
	FilesStorageService filesStorageService = new FileStorageDao();
	
	public ProductDao() {
		super();
		genericService = new GenericDao<>(Product.class);
	}
	
	@Override
	public List<Product> listProducts() {
		return  genericService.list();
	}

	@Override
	public Product findById(int id) {
		return genericService.findOneByIdNumer(id);
	}

	@Override
	public void insert(Product product,HttpServletRequest request) {
		product.setImage(filesStorageService.save(request));
		genericService.insert(product);
	}

	@Override
	public void updateProduct(Product product) {
		genericService.update(product);
	}

}
