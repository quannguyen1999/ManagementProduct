package com.example.services;

import java.util.List;

import com.example.models.CustomError;


public interface GenericService<T> {

	void insert(T t);
	
	List<T> list();
	
	T Query(String sql);
	
	T findOne(String id);
	
	List<CustomError> validator(T t);
}
