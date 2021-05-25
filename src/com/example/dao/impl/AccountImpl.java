package com.example.dao.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.example.dao.AccountDao;
import com.example.dao.GenericDao;
import com.example.models.Account;

public class AccountImpl implements AccountDao{
	//cung cấp sẵn có method CRUD trong hibernate
	GenericDao<Account> genericService;
	
	public AccountImpl() {
		super();
		//cần phải khai báo constructor để biết được đang gọi class nào
		genericService = new GenericImpl<>(Account.class);
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
