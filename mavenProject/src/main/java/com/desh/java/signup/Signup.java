package com.desh.java.signup;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.desh.java.MysqlDatabaseManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String phone = req.getParameter("phone");
			String password = req.getParameter("password");

			boolean isValidPhone = isValidNo(phone);
			boolean isValidPassword = isValidPassword(password);

			if (isValidPhone && isValidPassword) {
				MysqlDatabaseManager dbManager = new MysqlDatabaseManager();
				dbManager.registerCustomerDetails("1234567890", phone, password);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean isValidNo(String number) {

		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(number);
		return (m.matches());

	}

	public boolean isValidPassword(String password) {

		return password.length() > 8 && password.length() < 16;

	}

}
