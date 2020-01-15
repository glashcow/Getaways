package com.fdmgroup.getaways.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.model.holidaypackages.HolidayPackage;
import com.fdmgroup.getaways.service.AccommodationService;
import com.fdmgroup.getaways.service.FlightService;
import com.fdmgroup.getaways.service.UserService;

@Controller
public class TravelAgentController {
	
    private FlightService flightService;
    
	@Autowired
	public TravelAgentController(FlightService flightService) {
		this.flightService = flightService;
	}
	
	@GetMapping(name="/addOutwardFlight")
	public ModelAndView addPackage(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("addOutwardFlight");
		List<Flight>allFlights = flightService.getAllFlights();
		modelAndView.addObject("flightList", allFlights);
		model.addAttribute("message", "outward flight added");
		return modelAndView;
	}
	
	@PostMapping(name="/addReturnFlight")
	public ModelAndView packageSubmitted(@RequestParam("flightId")Long flightId) {
		ModelAndView modelAndView = new ModelAndView("addReturnFlight");
		Flight flight = flightService.getFlightByID(flightId);
		HolidayPackage holidayPackage = new HolidayPackage();
		holidayPackage.setDepartingFlight(flight);
		List<Flight>allFlights = flightService.getAllFlights();
		modelAndView.addObject("theFlightList", allFlights);
		return modelAndView;
	}
}
