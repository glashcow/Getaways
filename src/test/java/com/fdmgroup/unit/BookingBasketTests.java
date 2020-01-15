package com.fdmgroup.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.getaways.configuration.Config;
import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.basket.Basket;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.model.flights.Trip;
import com.fdmgroup.getaways.service.AccommodationService;
import com.fdmgroup.getaways.service.BasketService;
import com.fdmgroup.getaways.service.FlightService;
import com.fdmgroup.getaways.service.UserService;

public class BookingBasketTests {

	private FlightService flightService;
	private UserService userService;
	private AccommodationService accommodationService;
	private AnnotationConfigApplicationContext context;
	private Basket basket;
	private BasketService basketService;
	private List<Flight> flights;
	private List<Accommodation> accommodations;

	@BeforeEach
	public void init() {
		context = new AnnotationConfigApplicationContext(Config.class);
		flightService = context.getBean(FlightService.class);
		accommodationService = context.getBean(AccommodationService.class);
		userService = context.getBean(UserService.class);
		basketService = context.getBean(BasketService.class);
		flights = new ArrayList<>();
		accommodations = new ArrayList<>();
		basket = new Basket();
	}

	@AfterEach
	public void cleanUp() {
		context.close();
	}

	@Test
	public void testAddingFlightsAndAccommodationsToBasket() {
		Flight bookedFlight = flightService.getFlightByID(1l);
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(1L);
		basketService.addFlightToBasket(basket, bookedFlight);
		basketService.addAccommodationToBasket(basket, bookedAccommodation);
		assertAll(() -> assertEquals(1, basket.getAccommodations().size()),
				() -> assertEquals(1, basket.getFlights().size()));
	}

	@Test
	public void testAddingFlightToBasket() {
		List<Trip> basket = new ArrayList<Trip>();
		Flight bookedFlight = flightService.getFlightByID(1l);
		int expected = 1;
		basket.add(bookedFlight);
		int actual = basket.size();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddingAccommodationToBasket() {
		List<Trip> basket = new ArrayList<Trip>();
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(1l);
		int expected = 1;
		basket.add(bookedAccommodation);
		int actual = basket.size();
		assertEquals(expected, actual);
	}

	@Test
	public void getBasketForUser() {
		AccountUser accountUser = userService.getAnAccountUserByID(1L);
		long expected = 4;
		System.err.println(accountUser.getBasket());
		long actual = accountUser.getBasket().getBasketId();
		assertEquals(expected, actual);
	}

	@Test
	public void testCreatingABasketToAddStuff() {
		basketService.createBasket(basket);
		Basket basketDummy = basketService.getBasketById(basket.getBasketId());
		Long expected = 1l;
		Long actual = basketDummy.getBasketId();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddingItemsToBasketThenReturnBasketWithSameItems() {
		basketService.createBasket(basket);
		Flight bookedFlight = flightService.getFlightByID(1l);
		basketService.addFlightToBasket(basket, bookedFlight);
		long actualId = basket.getBasketId();
		long expectedId = 1;
		int expected = 1;
		int actual = basket.getFlights().size();
		assertAll(() -> assertEquals(expectedId, actualId), () -> assertEquals(expected, actual));
	}

	@Test
	public void testCantBookFlight3HoursBeforeDeparture() {
		basketService.createBasket(basket);
		Flight bookedFlight = flightService.getFlightByID(15l);
		basketService.isFlightBookableWithinTime(basket, bookedFlight);
		int expected = 0;
		int actual = basket.getFlights().size();
		assertEquals(expected, actual);
	}

	@Test
	public void calcualtePriceOfTotalBasket() {
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(1l);
		Flight bookedFlight = flightService.getFlightByID(1l);
		basketService.addAccommodationToBasket(basket, bookedAccommodation);
		basketService.addFlightToBasket(basket, bookedFlight);
		float expected = 383;
		float actual = basketService.calcaulteTotalPrice(basket.getFlights(), basket.getAccommodations());
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeAccomodationFromBasket() {
		int expected = 0;
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(1l);
		basketService.addAccommodationToBasket(basket, bookedAccommodation);
		basketService.removeAccommdation(basket, bookedAccommodation);
		int actual = basket.getAccommodations().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeAnAccomodationFromBasket() {
		int expected = 1;
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(1l);
		Accommodation bookedAccommodation2 = accommodationService.getAccommodationByID(2l);
		basketService.addAccommodationToBasket(basket, bookedAccommodation);
		basketService.addAccommodationToBasket(basket, bookedAccommodation2);
		basketService.removeAccommdation(basket, bookedAccommodation);
		int actual = basket.getAccommodations().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeFlightFromBasket() {
		int expected = 0;
		Flight bookedFlight = flightService.getFlightByID(1l);
		basketService.addFlightToBasket(basket, bookedFlight);
		basketService.removeFlight(basket, bookedFlight);
		int actual = basket.getAccommodations().size();
		assertEquals(expected, actual);
	}

}
