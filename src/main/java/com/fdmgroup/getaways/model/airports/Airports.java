package com.fdmgroup.getaways.model.airports;

public enum Airports {
	
	GLASGOW("Glasgow", "GLA"),
	EDINBURGH("Edinburgh", "EDI"), 
	LONDON("London", "LDN"),
	MANCHESTER("Manchester", "MAN"),
	MADRID("Madrid", "MAD"),
	PARIS("Paris", "CDG"),
	AMSTERDAM("Amsterdam", "AMS");
	
	private String fullName;
	private String abbreviation;

	private Airports(String fullName, String abbreviation) {
		this.fullName = fullName;
		this.abbreviation = abbreviation;
	}

	public String getFullName() {
		return fullName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
	
	public static String getAirports(int index){
		return Airports.values()[index].toString();
	}
}
