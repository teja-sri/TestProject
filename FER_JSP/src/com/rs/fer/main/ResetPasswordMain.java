package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rs.fer.pojo.Expense;
import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class ResetPasswordMain {

	public static void main(String[] args) {
		
		Service service = new ServiceImpl();
		
		User user = new User();
		
		user.setId(1);
		user.setPassword("teja@123");
		boolean isRecordEdited = service.resetPassword(user.getId(), "1234", user.getPassword()	 );
	if(isRecordEdited) {
		System.out.println("password updated  successfully....");
	}else {
		System.out.println("no matched records found...");
		}	

	}

}
