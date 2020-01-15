package com.fdmgroup.getaways.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.repository.AccountUserQuerier;

@Service
public class LoginService {

	private AccountUserQuerier accountUserQuerier;

	@Autowired
	public LoginService(AccountUserQuerier accountUserQuerier) {
		this.accountUserQuerier = accountUserQuerier;
	}

	public AccountUser getUserNameAndPassword(String userName, String password) {
		return accountUserQuerier.getUserNameAndPass(userName, password);
	}

}


