package com.fdmgroup.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.fdmgroup.getaways.configuration.Config;
import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.repository.FlightJpaDao;
import com.fdmgroup.getaways.service.FlightService;

public class FlightTests {
	
	private FlightService flightService;
	private AnnotationConfigApplicationContext context;
	private List<Flight> listOfFlights = new ArrayList<>();
	private Flight flight;
	private FlightJpaDao fjd;
	
	@BeforeEach
	public void init(){
		context = new AnnotationConfigApplicationContext(Config.class);
		flightService = context.getBean(FlightService.class);
		flight = new Flight();
	}
	
	@AfterEach
	public void cleanUp() {
		context.close();
	}
	
	@Test
	public void testGetAllFlightsReturnsAnExtraFlightWhenYouAddAFlight() {
		listOfFlights = flightService.getAllFlights();
		int expected = listOfFlights.size() + 1;
		flightService.addFlight(flight);
		List<Flight> newListOfFlights = flightService.getAllFlights();
		int actual = newListOfFlights.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testReturnListOfFlightsIncreasesWhenYouAddAFLight() {
		listOfFlights = flightService.getAllFlights();
		int expected = listOfFlights.size()+1;
		flightService.addFlight(flight);
		List<Flight> newListOfFlights = flightService.getAllFlights();
		int actual = newListOfFlights.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDisplayFlightsLeavingFromEdinburghReturnsFlightsFromThatAirport() {
		List<Flight> flightsFromAirport = new ArrayList<>();
		flightsFromAirport = flightService.getSelectedAirport(Airports.EDINBURGH);
		Flight edinburghFlight = flightsFromAirport.get(1);
		assertTrue(edinburghFlight.getStartLocation() == Airports.EDINBURGH);
	}
	
	@Test
	public void testDisplayFlightsLeavingToAndFromLocation() {
		int expected = 1;
		Airports startLocation = Airports.GLASGOW;
		Airports endLocation = Airports.MADRID;
		List<Flight> flightsFromAirport = flightService.getSelectedAirports(startLocation, endLocation);
		int actual = flightsFromAirport.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThatDateSelectedReturnsFlightsWithDate() {
		int expected = 1;
		Date dateOfFlight = new Date(2000-04-30);
		flight.setDateOfDeparture(dateOfFlight);
		flightService.addFlight(flight);
		List<Flight> datesOfFlights = flightService.showSelectedDate(dateOfFlight);
		int actual = datesOfFlights.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFilteringFlightsBetweenPrices() {
		int expected = flightService.getAllFlights().size();
		float minimumPrice = 0;
		float maximumPrice = 100000;
		List<Flight> flightsBetweenPrices = flightService.showFlightsBetweenPrices(minimumPrice, maximumPrice);
		int actual = flightsBetweenPrices.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFilterFlightsBetweenLessThanPrice() {
		flight.setFlightPrice(2);
		flightService.addFlight(flight);
		int expected = 1;
		float price = 3;
		List<Flight> LessThanPriceFlights = flightService.showFlightsLessThanPrice(price);
		int actual = LessThanPriceFlights.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThatOnceBookedFlightCapacityDecreases() {
		Long num = 1L; 
		Flight bookedFlight = flightService.getFlightByID(num);
		int expected = bookedFlight.getFlightCapacity() - 1;
		bookedFlight.setFlightCapacity(flightService.decreaseFlightCapcaityByBooking(bookedFlight.getFlightCapacity(), bookedFlight.getFlightId()));
		int actual = bookedFlight.getFlightCapacity();
		assertEquals(expected, actual);
	}
	
}