package com.fdmgroup.getaways.model;

public enum UserType {
	
	CUSTOMER("Customer"),
	ADMIN("Admin");
	
	private String type;
	

	private UserType(String type) {
		this.type = type;
	}

	public String getUserType() {
		return type;
	}


	public void setUserType(String type) {
		this.type = type;
	} 

}
