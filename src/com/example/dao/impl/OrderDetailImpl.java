package com.example.dao.impl;


import com.example.dao.GenericDao;
import com.example.dao.OrderDetailDao;
import com.example.models.OrderDetail;

public class OrderDetailImpl implements OrderDetailDao{
	//cung cấp sẵn có method CRUD trong hibernate
	GenericDao<OrderDetail> genericService;

	public OrderDetailImpl() {
		super();
		genericService = new GenericImpl<>(OrderDetail.class);
	}
	@Override
	public void insert(OrderDetail orderDetails) {
			genericService.insert(orderDetails);
	}

}
