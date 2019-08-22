package com.rs.fer.service;

import java.util.List;

import com.rs.fer.pojo.Expense;
import com.rs.fer.pojo.User;

public interface Service {

	boolean register(User user);

	int login(String username, String password);

	boolean addExpense(Expense expense);

	boolean editExpense(Expense expense);

	boolean deleteExpense(int expenseId);

	Expense getExpense(int expenseId);

	List<Expense> getExpenses(int userId);

	List<Expense> expenseReport(int userid,String expenseType, String fromDate, String toDate);

	boolean resetPassword(int userid, String currentPaswword, String newPassword);

	User getPersonal(int userId);

	boolean Updatepersonal(User user);
}
