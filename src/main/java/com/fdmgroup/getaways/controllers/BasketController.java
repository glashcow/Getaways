package com.fdmgroup.getaways.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.basket.Basket;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.service.AccommodationService;
import com.fdmgroup.getaways.service.BasketService;
import com.fdmgroup.getaways.service.FlightService;

@Controller
public class BasketController {

	private AccommodationService accommodationService;
	private FlightService flightService;
	private Basket basket = new Basket();
	private BasketService basketService;
	private List<Accommodation> accommodations = new ArrayList<>();
	private List<Flight> flights = new ArrayList<>();
	
	@Autowired
	public BasketController(AccommodationService accommodationService, FlightService flightService, BasketService basketService) {
		this.accommodationService = accommodationService;
		this.flightService = flightService;
		this.basketService = basketService;
	}
	
	@GetMapping(value="/addFlightToBasket")
	public ModelAndView addingFlight(@RequestParam("id") Long flightID, ModelMap model) {
		basketService.createBasket(basket);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("flightAdded");
		Flight bookedFlight = flightService.getFlightByID(flightID);
		boolean bookable = basketService.isFlightBookableWithinTime(basket, bookedFlight); 
		if(bookable=true) {
			System.err.println(bookable);
			model.addAttribute("message", "Your flight has been added to your basket");
			modelAndView.addObject("basket", basket.getFlights());		
		} else {
			System.err.println(bookable);
			model.addAttribute("message", "Unable to book this flight for security reasons!");
			System.out.println(model);
		}
		return modelAndView;
	}
	
	@GetMapping(value="/addAccommodationToBasket")
	public ModelAndView addingAccommodationToBasket(@RequestParam("id") Long accommodationId) {
		ModelAndView modelAndView = new ModelAndView();
		basketService.createBasket(basket);
		modelAndView.setViewName("accommodationAdded");
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(accommodationId);
		basketService.addAccommodationToBasket(basket, bookedAccommodation);
		modelAndView.addObject("basket", basket.getAccommodations());
		return modelAndView;  
	}
	
	@GetMapping(value="/displayBasket")
	public ModelAndView displayBasket() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayBasket");
		float totalPrice = basketService.calcaulteTotalPrice(basket.getFlights(), basket.getAccommodations());
		modelAndView.addObject("basketFlights", basket.getFlights());
		modelAndView.addObject("basketAccom", basket.getAccommodations());
		modelAndView.addObject("totalPrice", totalPrice);
		return modelAndView;
	}
	
	@GetMapping(value="/orderReview")
	public ModelAndView orderReview() {
		ModelAndView modelAndView = new ModelAndView("reviewOrder");
		float totalPrice = basketService.calcaulteTotalPrice(basket.getFlights(), basket.getAccommodations());
		modelAndView.addObject("basketFlights", basket.getFlights());
		modelAndView.addObject("basketAccom", basket.getAccommodations());
		modelAndView.addObject("totalPrice", totalPrice);
		for (Flight flight : basket.getFlights()) {
			flight.setFlightCapacity(flightService.decreaseFlightCapcaityByBooking(1, flight.getFlightId()));
			
		}
		for (Accommodation accommodation : basket.getAccommodations()) {
			accommodation.setRoomCapacity(accommodationService.decreaseAccommodationCapacityByBooking(1, accommodation.getAccommodationId()));
		}
		return modelAndView;
	}
}
