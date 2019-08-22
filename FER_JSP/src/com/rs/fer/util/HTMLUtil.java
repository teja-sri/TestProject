package com.rs.fer.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTMLUtil {

	public static void displayHeaderandLeftframe(HttpServletRequest request, HttpServletResponse response,
			PrintWriter out, String username) throws ServletException, IOException {

		request.getRequestDispatcher("layout/Header.html").include(request, response);

		out.println(username);
		
		request.getRequestDispatcher("layout/LeftFrame.html").include(request, response);
	}

	public static void displayFooter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("layout/Footer.html").include(request, response);
	}

}
