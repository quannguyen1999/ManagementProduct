package com.example.services.impl;

import java.util.List;

import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.services.GenericService;
import com.example.services.OrderDetailService;
import com.example.services.OrderService;

public class OrderDetailDao implements OrderDetailService{

	GenericService<OrderDetail> genericService;

	public OrderDetailDao() {
		super();
		genericService = new GenericDao<>(OrderDetail.class);
	}
	@Override
	public void insert(OrderDetail orderDetails) {
		// TODO Auto-generated method stub
			genericService.insert(orderDetails);
	}

}
