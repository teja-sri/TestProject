package com.rs.fer.main;

import com.rs.fer.pojo.Address;
import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class GetPersonalMain {

	public static void main(String[] args) {
		
		Service service = new ServiceImpl();
		
		int userId=3;
		
		User user =  service.getPersonal(userId);
		
		if(user!=null) {
			System.out.println("success..");
		}else {
			System.out.println("failed..");
		}
		
	}

}
