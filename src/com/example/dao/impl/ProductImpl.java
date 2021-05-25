package com.example.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.dao.FilesStorageDao;
import com.example.dao.GenericDao;
import com.example.dao.ProductDao;
import com.example.models.Product;

public class ProductImpl implements ProductDao{
	//cung cấp sẵn có method CRUD trong hibernate
	GenericDao<Product> genericService;
	
	FilesStorageDao filesStorageService = new FileStorageImpl();
	
	public ProductImpl() {
		super();
		genericService = new GenericImpl<>(Product.class);
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
