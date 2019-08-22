package com.rs.fer.main;

import java.util.List;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class GetExpensesMain {

	public static void main(String[] args) {
		
		Service service = new ServiceImpl();
		
		Expense expense = new Expense();
		
		expense.setUserid(4);
		
		List<Expense> list = service.getExpenses(expense.getUserid());
		
		if(list.size() > 0) {
			System.out.println(list);
		}else {
			System.out.println("no records found");
		}
	}

}
