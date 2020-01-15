package com.fdmgroup.getaways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fdmgroup.getaways.model.basket.Basket;

@Entity(name="ACCOUNT_USER") 
public class AccountUser {

	@Id 
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_gen")
	@SequenceGenerator(name="user_gen", sequenceName = "user_seq", allocationSize=1)
	private long id;
	@Column(name="USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@Column(name="COMPANY_TYPE")
	@Enumerated(EnumType.STRING)
	private CompanyType companyType;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="VERIFIED")
	private boolean verified;

	@ManyToOne
	@JoinTable(name="USER_BASKET")
	private Basket basket;

	public AccountUser() {}

	public AccountUser(UserType userType, CompanyType companyType, String firstName, String lastName, String userName,
			String password, boolean verified, Basket basket) {
		this.userType = userType;
		this.companyType = companyType;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.userName = userName;
		this.password = password;
		this.verified = verified;
		this.basket = basket;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isVerified() {
		return verified;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	} 

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	

	@Override
	public String toString() {
		return "AccountUser [id=" + id + ", userType=" + userType + ", companyType=" + companyType + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password
				+ ", verified=" + verified + "]";
	}
	
}
