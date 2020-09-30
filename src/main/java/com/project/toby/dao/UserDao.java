package com.project.toby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.toby.domain.User;

public class UserDao {
	
	private ConnectionMaker connectionMaker; // 인터페이스를 통해 오브젝트에 접근하므로 구체적인 클래스 정보를 알 필요가 없다.
	
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker; // UserDao 클래스에는 구체적인 클래스를 명시해야하는 한계가 사라짐.
	}
	
	/**
	 * 사용자 정보 등록
	 * @param user 사용자 정보
	 * @throws Exception
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection(); // 인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀐다고 해도 메소드 이름이 변경되지 않는다.
		
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO USER (ID, NAME, PASSWORD) VALUES (?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	/**
	 * 사용자 정보 취득
	 * @param id 아이디
	 * @return
	 * @throws Exception
	 */
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"SELECT * FROM USER WHERE id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	// 리팩토링 : 기능이 추가되거나 바뀐 것은 없지만, 이전보다 훨씬 깔끔해지고 미래의 변화에 좀 더 쉽게 대응할 수 있는 코드를 만드는 것
	// 메소드 추출 : 공통의 기능을 담당하는 메소드로 중복된 코드를 뽑아내는 것
	// 템플릿 메소드 패턴 : 슈퍼 클래스테서 기본적인 로직의 흐름(커넥션 가져오기, 등록, 조회 등)을 만들고, 그 기능의 일부를 추상메소드나 오버라이딩이 가능한 protected 메소드 등으로 
	//                 만들 뒤 서브 크래스에서 이런 메소드를 필요에 맞게 구현해서 사용하도록 하는 방법을 말한다. 디자인 패턴의 일부
	
	/**
	 * 연결 취득
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	//public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	
	/*
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dev_db?serverTimezone=UTC&characterEncoding=UTF-8&useSSL=false"
				, "ejsong", "1qaz2wsx");
		
		return c;
	} 
	*/
}
