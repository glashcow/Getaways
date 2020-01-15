package com.fdmgroup.getaways.model;

public enum CompanyType {
	
	AIRLINE("Airline"),
	TRAVEL_AGENT("Travel Agent"),
	ACCOMODATION_OWNER("Accomodation Owner");
	
	private String companyType;

	private CompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}
