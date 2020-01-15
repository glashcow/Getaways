package com.fdmgroup.getaways.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.getaways.model.airports.Airports;
import com.fdmgroup.getaways.model.flights.Flight;

@Repository
public class FlightJpaDao implements CRUD<Flight>, FlightQuerier<Flight>{

	private EntityManager entityManager;

	@Autowired	
	public FlightJpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Flight readOneById(long id) {
		return entityManager.find(Flight.class, id);
	}

	@Override
	public List<Flight> readAll() {
		return entityManager.createQuery("SELECT F FROM FLIGHTS F", Flight.class).getResultList();
	}

	@Override
	public void update(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Flight t) {
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public List<Flight> searchForAirport(Airports name){
		return entityManager.createQuery("SELECT F FROM FLIGHTS F WHERE startLocation =: findStartAirport AND endLocation =: findEndAirport", Flight.class)
				.setParameter("findStartAirport", name)
				.setParameter("findEndAirport", name)
				.getResultList();
	}

	@Override
	public List<Flight> searchForDate(Date date) {
		return entityManager.createQuery("SELECT F FROM FLIGHTS F WHERE dateOfDeparture LIKE :date", Flight.class)
				.setParameter("date", date, TemporalType.DATE)
				.getResultList();
	}

	@Override
	public List<Flight> searchBetweenPrices(float minimumPrice, float maximumPrice) {
		return entityManager.createQuery("SELECT F FROM FLIGHTS F WHERE price BETWEEN :minimumPrice AND :maximumPrice", Flight.class)
				.setParameter("minimumPrice", minimumPrice)
				.setParameter("maximumPrice", maximumPrice)
				.getResultList();
	}

	@Override
	public List<Flight> searchFlightsLessThan(float price) {
		return entityManager.createQuery("SELECT F FROM FLIGHTS F WHERE price < :selectedPrice", Flight.class)
				.setParameter("selectedPrice", price)
				.getResultList();
	}
 

	public List<Flight> searchforSelectedAirports(Airports startLocation, Airports endLocation) {
		return entityManager.createQuery("SELECT F FROM FLIGHTS F WHERE startLocation =: findStartAirport AND endLocation =: findEndLocation", Flight.class)
				.setParameter("findStartAirport", startLocation)
				.setParameter("findEndLocation", endLocation)
				.getResultList();
	}
}
