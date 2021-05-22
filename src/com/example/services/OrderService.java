package com.example.services;

import java.util.List;

import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.models.Product;

public interface OrderService {
	public void insert(OrderProduct order);
	
	public String createRandomIdOrder();
	
}
