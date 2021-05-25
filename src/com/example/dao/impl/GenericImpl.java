package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.example.dao.GenericDao;
import com.example.models.CustomError;
import com.example.util.MyEntityManager;

//cung cấp các CRUD
public class GenericImpl<T> implements GenericDao<T>{

	//class cần ánh xạ
	private Class<T> clazz;

	//cung cấp validator
	private static Validator validator;

	private static EntityManager em;

	//kết nối database và xác định loại đối tượng nào
	public GenericImpl(Class< T > clazzToSet) {
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
