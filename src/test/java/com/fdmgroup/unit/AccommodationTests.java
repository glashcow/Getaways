package com.fdmgroup.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.getaways.configuration.Config;
import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.locations.Locations;
import com.fdmgroup.getaways.repository.AccommodationJpaDao;
import com.fdmgroup.getaways.service.AccommodationService;

public class AccommodationTests {

	private AnnotationConfigApplicationContext context;
	private Accommodation accommodation;
	private AccommodationService accommodationService;
	private List<Accommodation> listOfAccommodations = new ArrayList<>();
	private AccommodationJpaDao accommodationJpaDao;
	
	@BeforeEach
	public void init() {
		context = new AnnotationConfigApplicationContext(Config.class);
		accommodationService = context.getBean(AccommodationService.class);
		accommodation = new Accommodation();
	}
	
	@AfterEach
	public void cleanUp() {
		context.close();
	}
	
	@Test
	public void testGetAllAccommodationsAndReturnAListThatIsNotEmpty() {
		listOfAccommodations = accommodationService.getAllAccommodations();
		assertFalse(listOfAccommodations.isEmpty());
	}
	
	@Test
	public void testReturnListOfAccommodationsWhenOnlyOneAccommodationHasBeenAddedToList() {
		listOfAccommodations = accommodationService.getAllAccommodations();
		int expected = listOfAccommodations.size() + 1;
		accommodationService.addAccommodation(accommodation);
		listOfAccommodations = accommodationService.getAllAccommodations();
		int actual = listOfAccommodations.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDisplayAccommodationsFromSelectedLocation() {
		int expected = 1;
		Locations accommodationLocation = Locations.MADRID;
		List<Accommodation> accommodationsInLocation = accommodationService.getSelectedLocation(accommodationLocation);
		int actual = accommodationsInLocation.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThatOnceAccommodationBookedCapacityDecreases() {
		Accommodation bookedAccommodation = accommodationService.getAccommodationByID(2l);
		int expected = bookedAccommodation.getRoomCapacity()-1;
		bookedAccommodation.setRoomCapacity(accommodationService.decreaseAccommodationCapacityByBooking(bookedAccommodation.getRoomCapacity(), bookedAccommodation.getAccommodationId()));
		int actual = bookedAccommodation.getRoomCapacity();
		assertEquals(expected, actual);
	}

	
	
}
