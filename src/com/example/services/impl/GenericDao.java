package com.example.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;

import com.example.dao.MyEntityManager;
import com.example.models.Account;
import com.example.models.CustomError;
import com.example.services.GenericService;

public class GenericDao<T> implements GenericService<T>{

	private Class<T> clazz;

	private static Validator validator;

	private static EntityManager em;

	public GenericDao(Class< T > clazzToSet) {
		em = MyEntityManager.getInstance().getEntityManager();
		this.clazz = clazzToSet;
	}


	@Override
	public void insert(T t) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(t);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
	}

	@Override
	public List<T> list() {
		return em.createQuery("from "+clazz.getName()).getResultList();
	}


	@Override
	public T Query(String sql) {
		// TODO Auto-generated method stub
		return (T) em.createQuery(sql).getSingleResult();
	}


	@Override
	public T findOne(String id) {
		return em.find(clazz, id);
	}


	@Override
	public List<CustomError> validator(T t) {
		List<CustomError> listCustomErrors = new ArrayList<CustomError>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<T>> cvs = validator.validate(t);
		for (ConstraintViolation<T> cv : cvs) {
			listCustomErrors.add(new CustomError(cv.getPropertyPath().toString(),cv.getMessage()));
		}  
		return listCustomErrors;
	}


	@Override
	public T findOneByIdNumer(int id) {
		return em.find(clazz, id);
	}


	@Override
	public Boolean update(T t) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(t);
			tr.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
		return true;
	}
}
