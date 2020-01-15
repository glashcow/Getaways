package com.fdmgroup.getaways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.CompanyType;
import com.fdmgroup.getaways.model.UserType;
import com.fdmgroup.getaways.service.UserService;

@Controller
public class RegisterController {

	private UserService userService;
	
	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value ="/register")
	public ModelAndView registerCustomer() {
		ModelAndView modelAndView = new ModelAndView("registerCustomer");
		modelAndView.addObject("accountUser", new AccountUser());
		return modelAndView;
	}

	@PostMapping(value="/signedUp")
	public ModelAndView customerRegistered(@ModelAttribute AccountUser accountUser) {
		accountUser.setUserType(UserType.CUSTOMER);
		accountUser.setVerified(true);
		userService.addAccountUser(accountUser);
		ModelAndView modelAndView = new ModelAndView("successfullSignUp"); 
		modelAndView.addObject("accountUser", new AccountUser());
		return modelAndView;	
	}
	@GetMapping(value ="/companyRegister")
	public ModelAndView registerCompany() {
		ModelAndView modelAndView = new ModelAndView("companyRegister");
		modelAndView.addObject("accountUser", new AccountUser());
		modelAndView.addObject("usertypes", CompanyType.values() );
		return modelAndView;
	}
	
	@PostMapping(value="/pendingRegestration")
	public ModelAndView companyRegistered(@ModelAttribute AccountUser accountUser) {
		userService.addAccountUser(accountUser);
		ModelAndView modelAndView = new ModelAndView("pendingRegestration");
		modelAndView.addObject("accountUser", new AccountUser());
		return modelAndView;	
	}
	
	@GetMapping(value="/customerHomePage")
	public ModelAndView customerHomePage() {
		ModelAndView modelAndView = new ModelAndView("customerHomePage");
		return modelAndView;
	}
}
