package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	
	public static Connection con;
	
	static {
		try {
			String path = "jdbc:mysql://localhost:3306/hsbc_september";
			String username = "root";
			String password = "Suhana@123";
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(path, username, password);
			System.out.println("----------->Check Connection:-"+con);
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String args[]) {
		System.out.println(con);
	}
}
