package com.example.controllers;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.dao.MyEntityManager;
import com.example.models.Account;
import com.example.models.TypeAccount;
import com.example.services.AccountService;
import com.example.services.impl.AccountDao;

public class Test {
	//	private static EntityManager em;
	private static Validator validator;
	public static void main(String[] args) {
		Account account = new Account("admin", "asd", TypeAccount.ADMIN);
		System.out.println(account.getPassword().getClass().getTypeName());
		System.out.println(account.getPassword().getClass().getCanonicalName());
		System.out.println(account.getPassword().getClass().getSimpleName());
		System.out.println(account.getClass().getDeclaredFields()[0]);
		System.out.println(account.getPassword().getClass().getTypeName());
		System.out.println(account.getPassword().getClass().getTypeName());
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		validator = factory.getValidator();
//		validate(account);
		//		AccountService accountService = new AccountDao();
		//		accountService.insert(new Account("fucks", "123", TypeAccount.USER));
		//		accountService.list().forEach(t->{
		//			System.out.println(t);
		//		});
		//		System.out.println(accountService.findById("fucfk"));
		//		em = MyEntityManager.getInstance().getEntityManager();
		//
		//		EntityTransaction tr = em.getTransaction();
		//		try {
		//
		//			tr.begin();
		//
		//			Account account = new Account("ok", "123",TypeAccount.ADMIN);
		//
		//			em.persist(account);
		//
		//			tr.commit();
		//
		//		} catch (Exception e) {
		//
		//			e.printStackTrace();
		//
		//			tr.rollback();
		//
		//
		//		}
	}

	public static void validate(Account car) {
		Set<ConstraintViolation<Account>> cvs = validator.validate(car);
		for (ConstraintViolation<Account> cv : cvs) {
			System.out.println(cv.getPropertyPath() + ": " + cv.getMessage());
		}        
	}
}
