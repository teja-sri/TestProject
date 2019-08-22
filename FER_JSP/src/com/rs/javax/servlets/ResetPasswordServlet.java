package com.rs.javax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.service.Service;
import com.rs.fer.service.ServiceImpl;
import com.rs.fer.util.HTMLUtil;

public class ResetPasswordServlet extends HttpServlet{
	
	Service service = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service = new ServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		boolean isResetPassword = service.resetPassword(3, currentPassword, newPassword);
		
		PrintWriter out = response.getWriter();
		
		HTMLUtil.displayHeaderandLeftframe(request, response, out, session.getAttribute("username").toString());
		
		out.println(isResetPassword ? "Password updated successfully.." : "failed to update password");
		
		HTMLUtil.displayFooter(request, response);
		
	}
	
	@Override
	public void destroy() {
		service = null;
	}

}
