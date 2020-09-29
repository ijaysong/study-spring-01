package com.project.toby.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SimpleConnectionMaker {

	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dev_db?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false"
				, "ejsong", "1qaz2wsx");
		
		return c;
	}
}
