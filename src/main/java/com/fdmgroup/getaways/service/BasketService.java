package com.fdmgroup.getaways.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.basket.Basket;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.repository.CRUD;

@Service
public class BasketService {

	private CRUD<Basket> basketCRUD;
	
	@Autowired
	public BasketService(CRUD<Basket> basketCRUD) {
		this.basketCRUD = basketCRUD;
	}

	public void addFlightToBasket(Basket basket, Flight bookedFlight) {
		basket.getFlights().add(bookedFlight);
	}

	public void addAccommodationToBasket(Basket basket, Accommodation bookedAccommodation) {
		basket.getAccommodations().add(bookedAccommodation);
	}

	public void createBasket(Basket basket) {
		basketCRUD.create(basket);
	}

	public Basket getBasketById(Long id) {
		return basketCRUD.readOneById(id);
	}

	public float calcaulteTotalPrice(List<Flight> flights, List<Accommodation> accommodations) {
		float calcualtePrice = 0;
		for (Accommodation accommodation : accommodations) {
			calcualtePrice = calcualtePrice + accommodation.getAccommodationPrice();
		}
		
		for (Flight flight : flights) {
			calcualtePrice = calcualtePrice + flight.getFlightPrice();
		}
		return calcualtePrice;
	}

	public boolean isFlightBookableWithinTime(Basket basket, Flight bookedFlight) {
		int departureTime =  bookedFlight.getDepartureTime().getHours();
		int currentTime = LocalTime.now().getHour();
		Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date departureDate = bookedFlight.getDateOfDeparture();
		Date currentDate = Date.from(instant);
		int compareDates = departureDate.compareTo(currentDate);
		if((compareDates == 0) && (departureTime-currentTime)>3) {
				addFlightToBasket(basket, bookedFlight);
				return true;
			}else if (compareDates > 0) {
				addFlightToBasket(basket, bookedFlight);
				return true;
			} else {
				return false;
		}
	}

	public void removeFlight(Basket basket,Flight flight) {
		basket.getFlights().remove(flight);
		
	}

	public void removeAccommdation(Basket basket, Accommodation bookedAccommodation) {
		basket.getAccommodations().remove(bookedAccommodation);
		
	}
}
