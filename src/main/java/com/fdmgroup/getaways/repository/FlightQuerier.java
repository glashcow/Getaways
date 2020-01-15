package com.fdmgroup.getaways.repository;

import java.util.Date;
import java.util.List;

import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;

public interface FlightQuerier<T> {
	
	List<Flight> searchForAirport(Airports startLocation);
	List<Flight> searchForDate(Date dateOfFlight);
	List<Flight> searchBetweenPrices(float minimumPrice, float maximumPrice);
	List<Flight> searchFlightsLessThan(float price);
	List<Flight> searchforSelectedAirports(Airports startLocation, Airports endLocation);
}
