package com.fdmgroup.getaways.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.service.FlightService;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;

@Controller
public class AirlineController {

	private FlightService flightService;

	@Autowired
	public AirlineController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(value ="/addFlight")
	public ModelAndView addFlight(@ModelAttribute("flight")Flight flight) {
		ModelAndView modelAndView = new ModelAndView("addFlight");
		modelAndView.addObject("flight",new Flight());
		modelAndView.addObject("airports", Airports.values());
		return modelAndView;
	}

	@PostMapping(value ="/flightSubmit")
	public ModelAndView flightSubmitted(@ModelAttribute Flight flight, @RequestParam(name="dateOfDeparture") String dateOfDeparture, @RequestParam(name="departureTime") String departureTime, @RequestParam(name="arrivalTime") String arrivalTime)throws ParseException {
		Date departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfDeparture);
		Date theDepartureTime =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(departureTime); 
		Date theArrivalTime =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(arrivalTime);
		flight.setDateOfDeparture(departureDate);
		flight.setDepartureTime(theDepartureTime);
		flight.setArrivalTime(theArrivalTime);
		flightService.addFlight(flight);
		ModelAndView modelAndView = new ModelAndView("flightSubmitted");
		modelAndView.addObject("flight",new Flight());
		return modelAndView;
	}
	
	@GetMapping(value="/airlineHomePage")
	public ModelAndView airlineHomePage() {
		ModelAndView modelAndView = new ModelAndView("homePageForAirlines");
	    return modelAndView;
	}
}
