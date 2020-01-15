package com.fdmgroup.getaways.model.accommodations;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.flights.Trip;
import com.fdmgroup.getaways.model.locations.Locations;

@Entity(name="ACCOMMODATIONS")
public class Accommodation implements Trip {
	
	@Id
	@Column(name="ACCOMMODATION_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="accommodation_gen")
	@SequenceGenerator(name="accommodation_gen", sequenceName="accommodation_seq", allocationSize=1)
	private Long accommodationId;
	@Column(name="ACCOMMODATION_NAME")
	private String accommodationName;
	@Column(name="ACCOMMODATION_TYPE")
	@Enumerated(EnumType.STRING)
	private AccommodationType accommodationType;
	@Column(name="ACCOMMODATION_LOCATION")
	@Enumerated(EnumType.STRING)
	private Locations accommodationLocation;
	@Column(name="ROOM_CAPACITY")
	private int roomCapacity;
	@Column(name="AVAILABLE_START_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate availableStartDate;
	@Column(name="AVAILABLE_END_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate availableEndDate;
	@Column(name="PRICE")
	private float accommodationPrice;
	@OneToOne
	@JoinTable(name="AccountUser")
	private AccountUser accommodationOwner;
	
	
	public Accommodation() {
		
	}

	public Accommodation(String accommodationName, AccommodationType accommodationType,
			Locations accommodationLocation, int roomCapacity, LocalDate availableStartDate, LocalDate availableEndDate,
			float accommodationPrice) {
		
		this.accommodationName = accommodationName;
		this.accommodationType = accommodationType;
		this.accommodationLocation = accommodationLocation;
		this.roomCapacity = roomCapacity;
		this.availableStartDate = availableStartDate;
		this.availableEndDate = availableEndDate;
		this.accommodationPrice = accommodationPrice;
	}
	public Long getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}

	public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public Locations getAccommodationLocation() {
		return accommodationLocation;
	}

	public void setAccommodationLocation(Locations accommodationLocation) {
		this.accommodationLocation = accommodationLocation;
	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public LocalDate getAvailableStartDate() {
		return availableStartDate;
	}

	public void setAvailableStartDate(LocalDate availableStartDate) {
		this.availableStartDate = availableStartDate;
	}

	public LocalDate getAvailableEndDate() {
		return availableEndDate;
	}

	public void setAvailableEndDate(LocalDate availableEndDate) {
		this.availableEndDate = availableEndDate;
	}
	
	public float getAccommodationPrice() {
		return accommodationPrice;
	}

	public void setAccommodationPrice(float accommodationPrice) {
		this.accommodationPrice = accommodationPrice;
	}

	public AccountUser getAccommodationOwner() {
		return accommodationOwner;
	}

	public void setAccommodationOwner(AccountUser accommodationOwner) {
		this.accommodationOwner = accommodationOwner;
	}

	@Override
	public String toString() {
		return "Accommodation [accommodationId=" /* + accommodationId + */ + ", accommodationName=" + accommodationName

				+ ", accommodationType=" + accommodationType + ", accommodationLocation=" + accommodationLocation
				+ ", roomCapacity=" + roomCapacity + ", availableStartDate=" + availableStartDate
				+ ", availableEndDate=" + availableEndDate + ", accommodationPrice=" + accommodationPrice + "]";
	}
}
