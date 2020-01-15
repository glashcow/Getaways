package com.fdmgroup.getaways.model.locations;

public enum Locations {

	GLASGOW("Glasgow"),
	EDINBURGH("Edinburgh"), 
	LONDON("London"),
	MANCHESTER("Manchester"),
	MADRID("Madrid"),
	PARIS("Paris"),
	AMSTERDAM("Amsterdam");
	
	private String locationName;

	private Locations(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public static String getLocations(int index) {
		return Locations.values()[index].toString();
	}
}
