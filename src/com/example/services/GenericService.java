package com.example.services;

import java.util.List;

import com.example.models.CustomError;


//cung cấp CRUD cho các service khác
public interface GenericService<T> {
	void insert(T t);
	
	List<T> list();
	
	T Query(String sql);
	
	T findOne(String id);
	
	//Lấy ra lỗi được định nghĩa trong object
	List<CustomError> validator(T t);
}
