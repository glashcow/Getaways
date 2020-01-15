package com.fdmgroup.getaways.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.basket.Basket;
import com.fdmgroup.getaways.repository.AccountUserQuerier;
import com.fdmgroup.getaways.repository.CRUD;

@Service
public class UserService {

	private CRUD<AccountUser>accountUserCRUD;
	private AccountUserQuerier userQuerier;

	@Autowired
	public UserService(CRUD<AccountUser> accountUserCRUD, AccountUserQuerier userQuerier) {
		this.accountUserCRUD = accountUserCRUD;
		this.userQuerier = userQuerier;
	}

	public void addAccountUser(AccountUser accountUser) {
		accountUserCRUD.create(accountUser);
	}

	public List<AccountUser> getAllAccountUser() {
		return accountUserCRUD.readAll();
	}

	public AccountUser getAnAccountUserByID(long id) {
		return accountUserCRUD.readOneById(id);
	}

	public void removeUser(AccountUser accountUser) {
		accountUserCRUD.delete(accountUser);
	}

	public void updateAccountUser(AccountUser accountUser) {
		accountUserCRUD.update(accountUser);
	}

	public List<AccountUser> getUserBasket(Basket basket) {
		return accountUserCRUD.readAll();
	}

	public Basket getUsersBasket(AccountUser accountUser) {
		return userQuerier.getUserBasket(accountUser);
	}
}
