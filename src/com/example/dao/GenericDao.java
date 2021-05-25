package com.example.dao;

import java.util.List;

import com.example.models.CustomError;


//cung cấp CRUD cho các service khác
public interface GenericDao<T> {
	void insert(T t);
	
	List<T> list();
	
	T Query(String sql);
	
	T findOne(String id);
	
	T findOneByIdNumer(int id);
	
	Boolean update(T t);
	
	//Lấy ra lỗi được định nghĩa trong object
	List<CustomError> validator(T t);
}
