package com.fer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import com.fer.bean.Address;
import com.fer.bean.Balance;
import com.fer.bean.Expense;
import com.fer.bean.ExpenseGroup;
import com.fer.bean.ExpenseName;
import com.fer.bean.PersonalInfo;
import com.fer.bean.User;

public interface FamilyExpenseReportServiceInterface {
	
	public boolean registration(User user);

	public PersonalInfo login(String userName, String password);

	public boolean resetPassword(String username, String password);

	public boolean addExpense(Expense expense);

	public boolean editExpense(Expense expense);

	public boolean deleteExpense(Expense expense);

	public boolean updatePersonalInfo(User user);

	public boolean updateContactInfo(User user);

	public boolean updateAddressInfo(Address address, Transaction transaction);

	public ArrayList<Expense> expenseReport(String expenseType, String name, String byWhom,
		String fromDate, String toDate, int numberOfRecrods, int numberOfRecordsPerPage, int pageIndex);
	
	public boolean updatePersonalInfo(User user, Address address);
	
	public List<Expense> getExpense(Expense expense);
	
	public Expense generateExpenses(String id);
	
	public boolean uploadPicture(String userName, String imagePath);
	
	public List<Expense> getExpenses();
	
	boolean saveExpenseGroup(String expenseGroupName);
	
	boolean saveExpenseName(String expenseName);
	
	Map<String, ExpenseGroup> getExpenseGroups();
	
	Map<String, ExpenseName> getExpenseNames();
	
	boolean saveBalance(Balance balance);
	
	Map<String, Balance> getBalances();
	
	int totalExpensesCount(String expenseType, String name, String byWhom,
		String fromDate, String toDate, int numberOfRecrods, int numberOfRecordsPerPage, int pageIndex);
	
	boolean isEmailAvailable(String email);
	
}
