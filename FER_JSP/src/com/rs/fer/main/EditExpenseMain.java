package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class EditExpenseMain {

	public static void main(String[] args) {
		Service service = new ServiceImpl();
		
		
		
		Expense expense = new Expense();
		
		expense.setExpenseType("laptop");
		expense.setNo_of_items(2);
		expense.setPrice(50000);
		expense.setTotal(100000);
		expense.setBy_whom("divya");
		expense.setDate("1/1/2019");
		expense.setUserid(3);
		expense.setId(1);
		boolean isRecordEdited = service.editExpense(expense);
	if(isRecordEdited) {
		System.out.println("record edited successfully....");
	}else {
		System.out.println("no matched records found...");
	}	
	}

}
