package com.fdmgroup.getaways.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.service.UserService;

@Controller
public class AdminController {

	private UserService userService;
	public final static String ADMIN_SESSION = "user";

	@Autowired
	public AdminController(UserService adminService) {
		this.userService = adminService; 
	}

	@PostMapping(value="/adminHomePage")
	public ModelAndView adminHomePage(@ModelAttribute("accountUser")AccountUser accountUser) {
		ModelAndView modelAndView = new ModelAndView("adminHomePage");
		modelAndView.addObject("accountUser", new AccountUser()); 
		return modelAndView;
	}
	
	@GetMapping(value="/companiesForApproval")
	public ModelAndView displayUnapprovedCompanies() {
		ModelAndView modelAndView = new ModelAndView("unapprovedCompanies");
		List<AccountUser>listOfUsers = userService.getAllAccountUser();
		List<AccountUser>listOfUsersThatAreNotApproved = 
				listOfUsers.stream().filter(x ->  (!x.isVerified())  ).collect(Collectors.toList());
		modelAndView.addObject("unapprovedaccounts", listOfUsersThatAreNotApproved);
		modelAndView.addObject("accountUser", new AccountUser() ); 
		return modelAndView;
	}


	@GetMapping(value="/companiesApproved")
	public ModelAndView companiesApproved(@RequestParam("id") long id ) {
		AccountUser accountUser  = userService.getAnAccountUserByID(id);
		accountUser.setVerified(true);
		userService.updateAccountUser(accountUser);
		ModelAndView modelAndView = new ModelAndView("approveCompany");
		List<AccountUser> listofApprovedAgents = new ArrayList<>();
		listofApprovedAgents.add(accountUser);
		modelAndView.addObject("aprrovedAgents", listofApprovedAgents);
		modelAndView.addObject("accountUser", accountUser);
		return modelAndView;
	}	
	
	@GetMapping(value="/adminHomePage")
	public ModelAndView adminHomePage() {
		ModelAndView modelAndView = new ModelAndView("homePageForAdmins");
	    return modelAndView;
		
	}
	
}
