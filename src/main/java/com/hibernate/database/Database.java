package com.hibernate.database;

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
@WebServlet("/database")
public class Database extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Database() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setup connection variables
		String url = "jdbc:mysql://localhost:3306/crm?useSSL=false";
		String user = "root";
		String password = "root";
		String driver = "com.mysql.jdbc.Driver";

		// get connection to database
		PrintWriter out = response.getWriter();
		try {

			out.println("Connecting to database: " + url);

			Class.forName(driver);

			Connection myConnection = DriverManager.getConnection(url, user, password);

			out.println("Connection success");

			myConnection.close();
		} catch (Exception e) {
			out.println("connection failed.");
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
