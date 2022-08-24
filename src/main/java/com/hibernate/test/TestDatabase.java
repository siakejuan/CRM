package com.hibernate.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class TestDatabase
 */
@WebServlet("/TestDatabase")
public class TestDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TestDatabase() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setup connection variables
		String url = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user = "root";
		String password = "password";
		String driver = "com.mysql.jdbc.Driver";
		 
		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to database: " + url);
			
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(url, user, password);

			out.println("Connection success");

			myConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
