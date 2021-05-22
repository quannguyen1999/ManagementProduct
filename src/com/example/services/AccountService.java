package com.example.services;

import java.util.List;

import com.example.models.Account;

public interface AccountService {
	void insert(Account account);
	
	List<Account> list();
	
	Account findById(String id);
	
	String hashPassword(String password);
	
	Boolean comparePassword(String passwordHash, String password);
}
