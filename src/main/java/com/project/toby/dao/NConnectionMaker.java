package com.project.toby.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		// N 사의 독자적인 방법으로 Connection을 생성하는 코드
		return null;
	}

}
