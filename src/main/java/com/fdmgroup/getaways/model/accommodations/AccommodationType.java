package com.fdmgroup.getaways.model.accommodations;

public enum AccommodationType {
	
	HOTEL("Hotel"),
	HOSTEL("Hostel"),
	RESORT("Resort"),
	COTTAGE("Cottage"),
	MANSION("Mansion"),
	LODGE("Lodge"),
	COUNTRY_HOME("Country Home"),
	LOG_CABIN("Log Cabin");
	
	private String accommodationType;

	private AccommodationType(String accommodationType) {
		this.accommodationType = accommodationType;
	}

	
	public String getAccommodationType() {
		return accommodationType;
	}


	public void setAccommodationType(String accommodationType) {
		this.accommodationType = accommodationType;
	}


	public static String getAccommodationTypes(int index){
		return AccommodationType.values()[index].toString();
	}
	
}
