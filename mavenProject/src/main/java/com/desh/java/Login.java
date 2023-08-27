package com.desh.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String phone;
		PrintWriter out = resp.getWriter();
		
		try {
			
			phone = req.getParameter("mobile");
			if(phone != null) {
				if(phone.length() == 10) {
					if(isValidNo(phone)) {
						MysqlDatabaseManager dbManager = new MysqlDatabaseManager();
						Customer customer = dbManager.isRegisteredUser(phone);
						String customerJsonString = new Gson().toJson(customer);
						resp.setContentType("application/json");
				        resp.setCharacterEncoding("UTF-8");
				        out.print(customerJsonString);
						out.close();
					}else {
						System.out.println("Invalid Phone pattern");
						out.println("Invalid Phone pattern");
					}
				}else {
					System.out.println("Phone no should include 10 digits");
					out.println("Phone no should include 10 digits");
				}
			}else {
				System.out.println("Empty phone input field");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean isValidNo(String number) {
		
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(number);
		return (m.matches());
		
	}

	
}
