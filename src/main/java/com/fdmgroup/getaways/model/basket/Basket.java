package com.fdmgroup.getaways.model.basket;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.flights.Flight;


@Entity(name="BASKET")
public class Basket {

	@Id
	@Column(name="BASKET_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="basket_gen")
	@SequenceGenerator(name="basket_gen", sequenceName = "basket_seq", allocationSize = 1)
	private Long basketId;
	@ManyToMany
	@JoinTable(name="BASKET_FLIGHT",
	joinColumns = {@JoinColumn(name="BASKET_ID")},
	inverseJoinColumns={@JoinColumn(name="FLIGHT_ID")}
			)
	List<Flight> flights;
	@ManyToMany
	@JoinTable(name="BASKET_ACCOM",
	joinColumns = {@JoinColumn(name="BASKET_ID")},
	inverseJoinColumns={@JoinColumn(name="ACCOM_ID")}
			)
	List<Accommodation> accommodations;

	public Basket() {
		accommodations = new ArrayList<>();
		flights = new ArrayList<>();
	}

	public Basket(List<Flight> flights, List<Accommodation> accommodations) {
		this.flights = flights;
		this.accommodations = accommodations;
	}


	public Basket(Long basketId, List<Flight> flights, List<Accommodation> accommodations) {
		this.basketId = basketId;
		this.flights = flights;
		this.accommodations = accommodations;
	}


	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Accommodation> getAccommodations() {
		return accommodations;
	}

	public void setAccommodations(List<Accommodation> accommodations) {
		this.accommodations = accommodations;
	}

	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + ", flights=" + flights + ", accommodations=" + accommodations + "]";
	}

}
