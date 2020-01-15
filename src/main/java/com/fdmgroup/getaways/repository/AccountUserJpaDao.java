package com.fdmgroup.getaways.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.basket.Basket;


@Repository
public class AccountUserJpaDao implements CRUD<AccountUser>, AccountUserQuerier {

	private EntityManager entityManager;

	@Autowired
	public AccountUserJpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(AccountUser t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();	
	}

	@Override
	public AccountUser readOneById(long id) {
		return entityManager.find(AccountUser.class, id);
	}

	@Override
	public List<AccountUser> readAll() {
		return entityManager.createQuery("SELECT a FROM ACCOUNT_USER a", AccountUser.class).getResultList();
	}

	@Override
	public void update(AccountUser t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();	
	}

	@Override
	public void delete(AccountUser t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public AccountUser getUserNameAndPass(String userName, String password) throws NoResultException {
		return entityManager.createQuery("SELECT a FROM ACCOUNT_USER a WHERE userName = : userNameToFind AND password = : passwordToFind" , AccountUser.class)
				.setParameter("userNameToFind", userName)
				.setParameter("passwordToFind", password)
				.getSingleResult();

	}
	@Override
	public Basket getUserBasket(AccountUser accountUser) {
		return accountUser.getBasket();
	}

} 
