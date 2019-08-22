package com.fer.bean;

public class PersonalInfo {
	private User user;
	private Address address;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "PersonalInfo [user=" + user + ", address=" + address + "]";
	}

	
}
