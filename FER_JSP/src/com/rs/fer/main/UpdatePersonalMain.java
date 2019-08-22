package com.rs.fer.main;

import com.rs.fer.pojo.Address;
import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;

public class UpdatePersonalMain {

	public static void main(String[] args) {
		Service service = new ServiceImpl();
		
		User user = new User();

		user.setFirstName("sunil");
		user.setLastName("musuluri");
		user.setMobile("45688990");
		user.setEmail("abc@mail.com");
		user.setId(2);
		Address address = new Address();
		address.setLineOne("abc");
		address.setLineTwo("sdh");
		address.setStreet("geetha mandiram road");
		address.setCity("nandigama");
		address.setState("andhra");
		address.setZip("521185");
		address.setUserid(4);
		
		user.setAddress(address);
		
		boolean isInserted = service.Updatepersonal(user);
		
		if(isInserted) {
			System.out.println("success..");
		}else {
			System.out.println("failed..");
		}
	}

}
