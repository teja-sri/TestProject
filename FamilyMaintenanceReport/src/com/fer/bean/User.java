package com.fer.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userId")
	private int id;
	
	@Column(name="firstName", length=30)
	private String firstName;
	
	@Column(name="lastName", length=30)
	private String lastName;
	
	@Column(name="email", length=30)
	private String email;
	
	@Column(name="userName", length=30)
	private String userName;
	
	@Column(name="password", length=30)
	private String password;
	
	@Column(name="middleName", length=30)
	private String middleName;
	
	@Column(name="phone", length=50)
	private String phone;
	
	@Column(name="imagePath", length=30)
	private String imagePath;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "expense", joinColumns = { @JoinColumn(name = "userId") })
	private List<Expense> expenses;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "balance", joinColumns = { @JoinColumn(name = "userId") })
	private List<Balance> balances;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", userName=" + userName
				+ ", middleName=" + middleName + ", phone=" + phone + "]";
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

}
