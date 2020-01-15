package com.fdmgroup.getaways.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.accommodations.AccommodationType;
import com.fdmgroup.getaways.model.locations.Locations;
import com.fdmgroup.getaways.service.AccommodationService;

@Controller
public class AccommodationController {

	private AccommodationService accommodationService;

	@Autowired
	public AccommodationController(AccommodationService accommodationService) {
		this.accommodationService = accommodationService;
	}

	@GetMapping(value = "/displayAccommodations")
	public ModelAndView displayAccommodations() {
		ModelAndView modelAndView = new ModelAndView("displayAccommodations");
		List<Accommodation> allAccommodations = accommodationService.getAllAccommodations();
		modelAndView.addObject("allAccommodations", allAccommodations);
		modelAndView.addObject("accommodations", new Accommodation());
		modelAndView.addObject("locations", Locations.values());
		return modelAndView;
	}

	@PostMapping(value = "/displayAccommodationsInLocation")
	public ModelAndView displayAccommodationsInLocation(
			@ModelAttribute("accommodations") Accommodation accommodationsInLocation) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("displayAccommodations");
		List<Accommodation> listOfAccommodations = accommodationService
				.getSelectedLocation(accommodationsInLocation.getAccommodationLocation());
		modelAndView.addObject("allAccommodations", listOfAccommodations);
		return modelAndView;
	}

	@GetMapping(value = "/addAccommodation")
	public ModelAndView addAccommodation() {
		ModelAndView modelAndView = new ModelAndView("addAccommodation");
		modelAndView.addObject("accommodation", AccommodationType.values());
		modelAndView.addObject("locations", Locations.values());
		modelAndView.addObject("accommodations", new Accommodation());
		return modelAndView;
	}

	@PostMapping(value = "/accommodationSubmit")
	public ModelAndView submitAccommodation(@ModelAttribute("accommodations") Accommodation accommodation,
			@RequestParam(name = "availableStartDate") String startDate,
			@RequestParam(name = "availableEndDate") String endDate) throws ParseException {
		Date theStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date theEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		accommodationService.addAccommodation(accommodation);
		ModelAndView modelAndView = new ModelAndView("accommodationSubmitted");
		modelAndView.addObject("accommodations", new Accommodation());
		return modelAndView;
	}

	@GetMapping(value = "/accommodationHomePage")
	public ModelAndView accomodationHomePage() {
		ModelAndView modelAndView = new ModelAndView("homePageForAccommodation");
		return modelAndView;
	}
}
