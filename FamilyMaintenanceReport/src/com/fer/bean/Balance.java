package com.fer.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Balance")
public class Balance {
	
	@Id
	@Column(name = "balanceId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int balanceId;
	
	@Column(name="type", length=5)
	private String type;
	
	@Column(name="amount")
	private float amount;
	
	@Column(name="actionType")
	private String actionType;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="userId")
	private int userId;

	public int getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
