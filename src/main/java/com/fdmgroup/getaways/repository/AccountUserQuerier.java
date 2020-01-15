package com.fdmgroup.getaways.repository;

import javax.persistence.NoResultException;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.basket.Basket;

public interface AccountUserQuerier {
	
  AccountUser getUserNameAndPass(String userName, String password) throws NoResultException;

  Basket getUserBasket(AccountUser accountUser);
}
