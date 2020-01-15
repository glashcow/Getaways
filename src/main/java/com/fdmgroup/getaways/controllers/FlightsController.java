package com.fdmgroup.getaways.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.AttributeValueExp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.service.FlightService;

import oracle.net.aso.e;

@Controller
public class FlightsController {

	private FlightService flightService;

	@Autowired
	public FlightsController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(value = "/displayFlights")
	public ModelAndView displayFlights() {
		ModelAndView modelAndView = new ModelAndView("displayFlights");
		List<Flight> allFlights = flightService.getAllFlights();
		modelAndView.addObject("allFlights", allFlights);
		modelAndView.addObject("flights", new Flight());
		modelAndView.addObject("airports", Airports.values());
		return modelAndView;
	}

	@PostMapping(value = "/displayAirports")
	public ModelAndView displayAirports(@ModelAttribute("flights") Flight airport) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayFlights");
		List<Flight> listOfFlights = new ArrayList<Flight>();

		if (!airport.getStartLocation().toString().isEmpty() && airport.getEndLocation().toString().isEmpty()) {
			listOfFlights = flightService.getSelectedAirport(airport.getStartLocation());
		} else if (airport.getStartLocation().toString().isEmpty() && !airport.getEndLocation().toString().isEmpty()) {
			listOfFlights = flightService.getSelectedAirport(airport.getEndLocation());
		}
		 else {
		}
			listOfFlights = flightService.getSelectedAirports(airport.getStartLocation(), airport.getEndLocation());
		
		modelAndView.addObject("allFlights", listOfFlights);
		return modelAndView;
	}
	

	@PostMapping(value = "/displayFlights")
	public ModelAndView displayDates(@RequestParam(name = "datePicker") String dateOfFlight) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateOfFlight);  
		modelAndView.setViewName("displayFlights");
		List<Flight> listOfFlights = flightService.showSelectedDate(date1);
		modelAndView.addObject("flights", new Flight());
		modelAndView.addObject("allFlights", listOfFlights);
		return modelAndView;
	}
	
	@PostMapping(value="/displayFlightsFilteredByPrice")
	public ModelAndView displayFilteredPrices(@RequestParam("selectPrice") int price) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayFlights");
		List<Flight> flightsFilteredByPrice = flightService.showFlightsLessThanPrice(price);
		modelAndView.addObject("allFlights", flightsFilteredByPrice);
		modelAndView.addObject("flights", new Flight());
		return modelAndView;
		
	}

}
