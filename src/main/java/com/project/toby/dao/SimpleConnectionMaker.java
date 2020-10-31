package com.project.toby.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SimpleConnectionMaker {

	/**
	 * 연결 취득
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dev_db?serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false"
				, "ejsong", "1qaz2wsx");
		
		return c;
	}
}
