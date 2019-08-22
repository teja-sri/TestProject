package com.fer.form;

import org.apache.struts.action.ActionForm;

public class ExpenseForm extends ActionForm{
	private String id;
	private String expenseType;
	private String name;
	private String debitedFrom;
	private String date;
	private float price;
	private int numOfItems;
	private float total;
	private String byWhom;
	private String expenseId;
	
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumOfItems() {
		return numOfItems;
	}
	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getByWhom() {
		return byWhom;
	}
	public void setByWhom(String byWhom) {
		this.byWhom = byWhom;
	}
	public String getDebitedFrom() {
		return debitedFrom;
	}
	public void setDebitedFrom(String debitedFrom) {
		this.debitedFrom = debitedFrom;
	}
}
