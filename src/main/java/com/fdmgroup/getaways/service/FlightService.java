package com.fdmgroup.getaways.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;
import com.fdmgroup.getaways.repository.CRUD;
import com.fdmgroup.getaways.repository.FlightQuerier;


@Service
public class FlightService{
	
	private CRUD<Flight> flightCRUD;
	private FlightQuerier<Flight> flightQuerier;
	

	@Autowired
	public FlightService(CRUD<Flight> flightCRUD, FlightQuerier<Flight> flightQuerier) {
		this.flightCRUD = flightCRUD;
		this.flightQuerier = flightQuerier;
	}

	public List<Flight> getAllFlights() {
		return flightCRUD.readAll();
	}

	public void addFlight(Flight flight) {
		flightCRUD.create(flight);
	}

	public List<Flight> getSelectedAirport(Airports startLocation) {
		return flightQuerier.searchForAirport(startLocation);
	}

	public List<Flight> showSelectedDate(Date dateOfFlight) {
		return flightQuerier.searchForDate(dateOfFlight);
	}

	public List<Flight> showFlightsBetweenPrices(float minimumPrice, float maximumPrice) {
		return flightQuerier.searchBetweenPrices(minimumPrice, maximumPrice);
	}

	public List<Flight> showFlightsLessThanPrice(float price) {
		return flightQuerier.searchFlightsLessThan(price);
	}

	public List<Flight> getSelectedAirports(Airports startLocation, Airports endLocation) {
		return flightQuerier.searchforSelectedAirports(startLocation, endLocation);
	}

	public int decreaseFlightCapcaityByBooking(int i, long id) {
		Flight flight = flightCRUD.readOneById(id);
		i = flight.getFlightCapacity() -1;
		flightCRUD.update(flight);
		return i;
	}

	public Flight getFlightByID(long i) {
		return flightCRUD.readOneById(i);
	}

}
