package com.fer.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "expenseId")
	private int id;
	
	@Column(name="type", length=30)
	private String type;
	
	@Column(name="name", length=50)
	private String name;
	
	@Column(name="debitedFrom", length=5)
	private String debitedFrom;
	
	@Column(name="date", length=40)
	private String date;
	
	@Column(name="price")
	private double price;
	
	@Column(name="numberOfItems", length=10)
	
	private int numberOfItems;
	
	@Column(name="total")
	private double total;
	
	@Column(name="byWhom", length=30)
	private String byWhom;
	
	@Column(name="userId")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getByWhom() {
		return byWhom;
	}

	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDebitedFrom() {
		return debitedFrom;
	}

	public void setDebitedFrom(String debitedFrom) {
		this.debitedFrom = debitedFrom;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", type=" + type + ", name=" + name + ", date=" + date
				+ ", price=" + price + ", numberOfItems=" + numberOfItems
				+ ", total=" + total + ", byWhom=" + byWhom + "]";
	}

}
