package com.example.dao;

import java.util.List;

import com.example.models.Account;

public interface AccountDao {
	//thêm account
	void insert(Account account);
	
	List<Account> list();
	
	Account findById(String id);
	
	//chuyển password thành bscrypt
	String hashPassword(String password);
	
	//so sánh password và hash
	Boolean comparePassword(String passwordHash, String password);
}
