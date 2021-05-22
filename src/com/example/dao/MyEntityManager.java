package com.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

//Để kết nối tới database
public class MyEntityManager {
	private static MyEntityManager instance = null;
	private EntityManager entityManager;
	
	private MyEntityManager() {
		entityManager = Persistence.createEntityManagerFactory("ManagementProduct").createEntityManager();
	}
	
	public synchronized static MyEntityManager getInstance() {
		if(instance == null) {
			instance = new MyEntityManager();
		}
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
