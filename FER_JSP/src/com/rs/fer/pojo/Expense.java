package com.rs.fer.pojo;

public class Expense {
	
	int id;
	String expenseType;
	float price;
	int no_of_items;
	float total;
	String by_whom;
	String date;
	int userid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNo_of_items() {
		return no_of_items;
	}
	public void setNo_of_items(int no_of_items) {
		this.no_of_items = no_of_items;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getBy_whom() {
		return by_whom;
	}
	public void setBy_whom(String by_whom) {
		this.by_whom = by_whom;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	

}
