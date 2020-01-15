package com.fdmgroup.getaways.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.getaways.model.basket.Basket;

@Repository
public class BasketJpaDao implements CRUD<Basket>{

	private EntityManager entityManager;

	@Autowired
	public BasketJpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Basket t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Basket readOneById(long id) {
		return entityManager.find(Basket.class, id);
	}

	@Override
	public List<Basket> readAll() {
		return entityManager.createQuery("SELECT B FROM BASKET B", Basket.class).getResultList();
	}
 
	@Override
	public void update(Basket t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Basket t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}
		
}
