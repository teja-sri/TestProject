package com.fer.form;

import org.apache.struts.validator.ValidatorForm;

public class PersonalInfoForm extends ValidatorForm{
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String email;
	private String phone;
	
	private String streetLine;
	private String city;
	private String town;
	private String state;
    private String zip;
	private String country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetLine() {
		return streetLine;
	}
	public void setStreetLine(String streetLine) {
		this.streetLine = streetLine;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}

	
	

}
