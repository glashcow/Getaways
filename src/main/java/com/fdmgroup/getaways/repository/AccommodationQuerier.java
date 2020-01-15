package com.fdmgroup.getaways.repository;

import java.util.List;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.locations.Locations;

public interface AccommodationQuerier<T> {
	
	List<Accommodation> searchAccommodationLocation(Locations locations);

}
