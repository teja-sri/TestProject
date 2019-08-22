package com.rs.fer.main;

import java.util.List;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class ReportExpenseMain {

	public static void main(String[] args) {

		Service service = new ServiceImpl();
		
		Expense expense = new Expense();
		
		expense.setUserid(4);
		expense.setExpenseType("car");
		
		List<Expense> list = service.expenseReport(expense.getUserid(), expense.getExpenseType(),"23/9/2018", "15/7/2019");
		
		if(list.size() > 0) {
			System.out.println(list);
		}else {
			System.out.println("no records found");
		}
	}

}
