package com.fdmgroup.getaways.model.holidaypackages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.flights.Flight;

@Entity(name="HOLIDAY_PACKAGE")
public class HolidayPackage {

	@Id 
	@Column(name="PACKAGE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="package_gen")
	@SequenceGenerator(name="package_gen", sequenceName = "package_seq", allocationSize=1)
	private long id;

	@OneToOne
	@JoinTable(name="DEPARTING_FLIGHT")
	private Flight departingFlight;

	@OneToOne
	@JoinTable(name="RETURING_FLIGHT")
	private Flight returningFlight;

	@OneToOne
	@JoinTable(name="ACCOMMODATION")
	private Accommodation accommodation;

	@Column(name="PRICE")
	private float price;

	public HolidayPackage() {}

	public HolidayPackage(Flight departingFlight, Flight returningFlight, Accommodation accommodation, float price) {
		this.departingFlight = departingFlight;
		this.returningFlight = returningFlight;
		this.accommodation = accommodation;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Flight getDepartingFlight() {
		return departingFlight;
	}

	public void setDepartingFlight(Flight departingFlight) {
		this.departingFlight = departingFlight;
	}

	public Flight getReturningFlight() {
		return returningFlight;
	}

	public void setReturningFlight(Flight returningFlight) {
		this.returningFlight = returningFlight;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HolidayPackage [id=" + id + ", departingFlight=" + departingFlight + ", returningFlight="
				+ returningFlight + ", accommodation=" + accommodation + ", price=" + price + "]";
	}
}
