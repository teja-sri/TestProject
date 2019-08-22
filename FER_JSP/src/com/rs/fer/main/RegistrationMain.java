package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {
	
		
		Service service = new ServiceImpl();
		
		User user = new User();
		
		user.setFirstName("teja");
		user.setLastName("Rayapati");
		user.setMiddleName("sri");
		user.setUserName("tejaSri");
		user.setPassword("teja@123");
		user.setMobile("542368218");
		user.setEmail("teja@mail.com");
		
		boolean isRegister = service.register(user);
		
			if(isRegister) {
				System.out.println("record inserted successfully....");
			}else {
				System.out.println("record not inserted..");
			}	
		}
	}
