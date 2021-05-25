package com.example.dao.impl;

import com.example.dao.GenericDao;
import com.example.dao.OrderDao;
import com.example.models.OrderProduct;

public class OrderImpl implements OrderDao{
	//cung cấp sẵn có method CRUD trong hibernate
	GenericDao<OrderProduct> genericService;

	public OrderImpl() {
		super();
		genericService = new GenericImpl<>(OrderProduct.class);
	}

	@Override
	public void insert(OrderProduct order) {
		genericService.insert(order);
	}

	@Override
	public String createRandomIdOrder() {
		boolean result1=false;
		String idRandom="";
		int random=0;
		while(result1==false) {
			random = (int)(Math.random() * 10000 + 1);
			idRandom="CT"+random;
			if(genericService.findOne(idRandom)==null) {
				result1=true;
			};
		}
		return idRandom;
	}


}
