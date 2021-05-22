package com.example.services.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.example.models.Account;
import com.example.services.AccountService;
import com.example.services.GenericService;

public class AccountDao implements AccountService{
	
	GenericService<Account> genericService;
	
	public AccountDao() {
		super();
		genericService = new GenericDao<>(Account.class);
	}

	@Override
	public void insert(Account account) {
		genericService.insert(account);
	}

	@Override
	public List<Account> list() {
		return  genericService.list();
	}

	@Override
	public String hashPassword(String password) {
		return 	BCrypt.hashpw(password, BCrypt.gensalt(10));
	}

	@Override
	public Boolean comparePassword(String passwordHash, String password) {
		return BCrypt.checkpw(password, passwordHash);
	}

	@Override
	public Account findById(String id) {
		return genericService.findOne(id);
	}
}
