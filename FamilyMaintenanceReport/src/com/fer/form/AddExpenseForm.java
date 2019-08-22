package com.fer.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class AddExpenseForm extends ActionForm{
	private int Id;
	private String type;
	private String name;
	private String debitedFrom;
	private String date;
	private double price;
	private int numberOfItems;
	private double total;
	private String byWhom;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.type ="enter expense type";
		this.date ="enter date";
		this.price =0.0;
		this.numberOfItems =0;
		this.total =0.0;
		this.byWhom ="enter by whom";
	}
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors actionErrors = new ActionErrors();
		
		if(getType() == null || getType().trim().equals("")){
			actionErrors.add("typeError", new ActionMessage("addexpenseform.type.required"));
		}
		if(getDate() == null || getDate().trim().equals("")){
			actionErrors.add("dateError", new ActionMessage("addexpenseform.date.required"));
		}
		if(getPrice() == 0.0){
			actionErrors.add("priceError", new ActionMessage("addexpenseform.price.required"));
		}
		
		if(getNumberOfItems() == 0){
			actionErrors.add("numberOfItemsError", new ActionMessage("addexpenseform.numberOfItems.required"));
		}
		if(getTotal() ==0.0){
			actionErrors.add("totalError", new ActionMessage("addexpenseform.total.required"));
		}
		if(getByWhom() == null || getByWhom().trim().equals("")){
			actionErrors.add("byWhomError", new ActionMessage("addexpenseform.byWhom.required"));
		}
		return actionErrors;
	}
	

}
