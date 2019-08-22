package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class LoginMain {

	public static void main(String[] args) {

		Service service = new ServiceImpl();

		User user = new User();
		user.setUserName("tejaSri");
		user.setPassword("teja");

		int userId = service.login(user.getUserName(), user.getPassword());

		if (userId > 0) {
			System.out.println("fetched records successfully..");
		} else {
			System.out.println("no  matched records found..");
		}
	}

}
