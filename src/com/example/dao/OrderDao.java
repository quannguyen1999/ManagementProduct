package com.example.dao;

import com.example.models.OrderProduct;

public interface OrderDao {
	//them order
	public void insert(OrderProduct order);
	
	//tạo random id order
	public String createRandomIdOrder();
	
}
