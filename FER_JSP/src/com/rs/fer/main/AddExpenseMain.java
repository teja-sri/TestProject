package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rs.fer.pojo.Expense;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class AddExpenseMain {

	public static void main(String[] args) {
		
		Service service = new ServiceImpl();
				
				Expense expense = new Expense();
				expense.setExpenseType("car");
				expense.setNo_of_items(1);
				expense.setPrice(500000);
				expense.setTotal(500000);
				expense.setBy_whom("teja");
				expense.setDate("132/1/2019");
				expense.setUserid(4);
				boolean isRecordInserted = service.addExpense(expense);
			if(isRecordInserted) {
				System.out.println("record inserted successfully....");
			}else {
				System.out.println("record not inserted..");
			}	
	}

}
