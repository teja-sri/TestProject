package com.fer.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="streetLine", length=30)
	private String streetLine;
	
	@Column(name="town", length=30)
	private String town;
	
	@Column(name="city", length=30)
	private String city;
	
	@Column(name="state", length=30)
	private String state;
	
	@Column(name="zip", length=30)
	private int zip;
	
	@Column(name="country", length=40)
	private String country;
	
	@OneToOne(targetEntity=User.class, cascade=CascadeType.ALL)
	@JoinColumn(name="userId", referencedColumnName="userId")
	private User userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getStreetLine() {
		return (streetLine == null) ? streetLine : streetLine.trim();
	}

	public void setStreetLine(String streetLine) {
		this.streetLine = streetLine;
	}

	public String getTown() {
		return (town == null) ? "" : town.trim();
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return (city == null) ? "" : city.trim();
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return (state == null) ? "" : state.trim();
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return (country == null) ? "" : country.trim();
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", streetLine="
				+ streetLine + ", town=" + town + ", city=" + city + ", state="
				+ state + ", zip=" + zip + ", country=" + country + "]";
	}

}
