package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.pojo.User;
import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class UpdatePersonalInfoServlet extends HttpServlet{
	
	Service service = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("userObj");
		
		PrintWriter out = response.getWriter();
		
		boolean isUpdatePersonal = service.Updatepersonal(user);
		
		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());
		
		out.println(isUpdatePersonal ? "User Information updated successfully.." : "update failed");
		
		HTMLUtil.displayFooter(request, response);
		
	}
	
	@Override
	public void destroy() {
		service = null;
	}

}
