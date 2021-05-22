package com.example.services.impl;

import java.util.List;

import javax.persistence.criteria.Order;

import com.example.models.Account;
import com.example.models.OrderDetail;
import com.example.models.OrderProduct;
import com.example.services.OrderService;
import com.example.services.GenericService;

public class OrderDao implements OrderService{

	GenericService<OrderProduct> genericService;

	public OrderDao() {
		super();
		genericService = new GenericDao<>(OrderProduct.class);
	}

	@Override
	public void insert(OrderProduct order) {
		// TODO Auto-generated method stub
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
