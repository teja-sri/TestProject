package com.rs.fer.main;

import java.util.List;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class GetExpenseMain {

	public static void main(String[] args) {
Service service = new ServiceImpl();
		
		Expense expense = new Expense();
		
		expense.setId(8);
		
		Expense id = service.getExpense(expense.getId());
		
		if(id.equals(0)) {
			System.out.println("no records");
		}else {
			System.out.println(id);
		}
	}
}
