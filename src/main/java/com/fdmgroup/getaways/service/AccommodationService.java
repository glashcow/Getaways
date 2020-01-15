package com.fdmgroup.getaways.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.locations.Locations;
import com.fdmgroup.getaways.repository.AccommodationQuerier;
import com.fdmgroup.getaways.repository.CRUD;

@Service
public class AccommodationService {
	
	private CRUD<Accommodation> accommodationCRUD;
	private AccommodationQuerier<Accommodation> accommodationQuerier;

	@Autowired
	public AccommodationService(CRUD<Accommodation> accommodationCRUD,
			AccommodationQuerier<Accommodation> accommodationQuerier) {
		this.accommodationCRUD = accommodationCRUD;
		this.accommodationQuerier = accommodationQuerier;

	}

	public List<Accommodation> getAllAccommodations() {
		return accommodationCRUD.readAll();
	}

	public void addAccommodation(Accommodation accommodation) {
		accommodationCRUD.create(accommodation);		
	}

	public List<Accommodation> getSelectedLocation(Locations locations) {
		return accommodationQuerier.searchAccommodationLocation(locations);
	}

	public int decreaseAccommodationCapacityByBooking(int i, long id) {
		Accommodation accommodation = accommodationCRUD.readOneById(id);
		i = accommodation.getRoomCapacity() -1;
		accommodationCRUD.update(accommodation);
		return i;
	}

	public Accommodation getAccommodationByID(long l) {
		return accommodationCRUD.readOneById(l);
	}

}
