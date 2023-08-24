package com.desh.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Categories extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Categories() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MysqlDatabaseManager dbManager = new MysqlDatabaseManager();
		ArrayList<Category> list;
		try {
			list = dbManager.showCategories();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			list = new ArrayList<>();
			e.printStackTrace();
		}
		
        String employeeJsonString = new Gson().toJson(list);
        
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        
        out.close();
        
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	
}
