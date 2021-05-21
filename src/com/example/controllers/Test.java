package com.example.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.example.dao.MyEntityManager;
import com.example.models.Account;
import com.example.models.TypeAccount;

public class Test {
	private static EntityManager em;
	public static void main(String[] args) {
		em = MyEntityManager.getInstance().getEntityManager();

		EntityTransaction tr = em.getTransaction();
		try {

			tr.begin();

			Account account = new Account("ok", "123",TypeAccount.ADMIN);

			em.persist(account);

			tr.commit();

		} catch (Exception e) {

			e.printStackTrace();

			tr.rollback();


		}
	}
}
