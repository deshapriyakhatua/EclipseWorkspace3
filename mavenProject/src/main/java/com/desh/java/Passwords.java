package com.desh.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Passwords extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public Passwords() {
        
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MysqlDatabaseManager dbManager = new MysqlDatabaseManager();
		ArrayList<UserPassword> list;
		try {
			list = dbManager.showUserPasswords();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			list = new ArrayList<>();
			e.printStackTrace();
		}
		
        String passwordsJsonString = new Gson().toJson(list);
        
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(passwordsJsonString);
        
        out.flush();
        
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
