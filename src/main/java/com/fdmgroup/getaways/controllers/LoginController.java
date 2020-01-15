package com.fdmgroup.getaways.controllers;

import java.time.ZonedDateTime;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.CompanyType;
import com.fdmgroup.getaways.model.UserType;
import com.fdmgroup.getaways.service.LoginService;
import com.fdmgroup.getaways.service.UserService;

@Controller
public class LoginController {

	private static Logger LOGGER = LogManager.getLogger(LoginController.class);

	private LoginService loginService;
	public static final String CUSTOMER_SESSION = "customer";
	public final static String ADMIN_SESSION = "admin";
	public final static String ACCOMODATION_OWNER_SESSION = "accommodation";
	public final static String AIRLINE_SESSION = "airline";
	public final static String TRAVEL_AGENT_SESSION = "travelAgent";
	private UserType userType;
	private CompanyType companyType; 
	private UserService userService;

	@Autowired
	public LoginController(LoginService loginService, UserService userService) {
		this.loginService = loginService;
		this.userService = userService;
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("login", "accountUser", new AccountUser());
	}

	@PostMapping(value = "/LoginSubmit")
	public ModelAndView logonSubmit(@ModelAttribute("accountUser") AccountUser user, ModelMap model,
			HttpSession session) {
		
	
		String userNameForLogin = user.getUserName();
		try {
			user = loginService.getUserNameAndPassword(user.getUserName(), user.getPassword());
		} catch (NoResultException nre) {

			nre.printStackTrace();
			LOGGER.info("User failed attempted log in with username: {} at {}", userNameForLogin, ZonedDateTime.now());
			model.addAttribute("message", "incorrect username or password");
			return new ModelAndView("login");

		} 

		if (user.isVerified() && user.getUserType() == UserType.CUSTOMER) {
			session.setMaxInactiveInterval(15 * 60);
			model.addAttribute("message", "login successful");
			session.setAttribute(CUSTOMER_SESSION , user);
			return new ModelAndView("userHomePage");
		}
		if(user.isVerified()&& user.getUserType() == UserType.ADMIN) {
			session.setAttribute(ADMIN_SESSION, user);
			session.setMaxInactiveInterval(15 * 60);
			model.addAttribute("message", "login successful");
			return new ModelAndView("adminHomePage");
			} 
		if(user.isVerified() && user.getCompanyType() == CompanyType.ACCOMODATION_OWNER) {
			session.setAttribute(ACCOMODATION_OWNER_SESSION, user);
			session.setMaxInactiveInterval(15 * 60);
			model.addAttribute("message", "login successful");
			return new ModelAndView("accommodationHomePage");
		}
		if(user.isVerified() && user.getCompanyType() == CompanyType.AIRLINE) {
			session.setAttribute(AIRLINE_SESSION, user);
			session.setMaxInactiveInterval(15 * 60);
			model.addAttribute("message", "login successful");
			return new ModelAndView("airlineHomePage");
		}
		if(user.isVerified() && user.getCompanyType() == CompanyType.TRAVEL_AGENT) {
			session.setAttribute(TRAVEL_AGENT_SESSION, user);
			session.setMaxInactiveInterval(15 * 60);
			model.addAttribute("message", "login successful");
			return new ModelAndView("travelAgentHomePage");
		}
		else {
			model.addAttribute("message", "incorrect login details, please wait to be approved");
			return new ModelAndView("login");
		}

	}
}

