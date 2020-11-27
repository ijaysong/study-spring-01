package com.project.toby.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.project.toby.domain.User;

public class UserDao {
	
	// 인터페이스를 통해 오브젝트에 접근하므로 구체적인 클래스 정보를 알 필요가 없다.
	private ConnectionMaker connectionMaker;
	
	private Connection c;
	
	private User user;
	
	private DataSource dataSource;
	
	//private static UserDao INSTANCE;
	
	/*
	public UserDao() {
		DaoFactory daoFactory = new DaoFactory();
		this.connectionMaker = daoFactory.connectionMaker();
	}
	*/
	
	/*
	public UserDao(ConnectionMaker connectionMaker) {
		//connectionMaker = new DConnectionMaker();
		this.connectionMaker = connectionMaker;
	}
	*/
	
	/*
	public UserDao() {
		simpleConnectionMaker = new SimpleConnectionMaker();
	}
	*/
	
	/**
	 * 사용자 정보 등록
	 * @param user 사용자 정보
	 * @throws Exception
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection c = simpleConnectionMaker.makeNewConnection();
		//Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("INSERT INTO TOBY_DB.USER(ID, NAME, PASSWORD) VALUES (?, ?, ?)");
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
		//Class.forName("com.mysql.cj.jdbc.Driver");
		//Connection c = simpleConnectionMaker.makeNewConnection();
		this.c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM TOBY_DB.USER WHERE ID = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			this.user = new User();
			this.user.setId(rs.getString("id"));
			this.user.setName(rs.getString("name"));
			this.user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if(this.user == null) throw new EmptyResultDataAccessException(1);
		
		return this.user;
	}
	
	/**
	 * 연결 취득
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// 상속을 통한 확장
	// public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	
	/*
	// 메소드로 추출
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dev_db?serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false"
				, "ejsong", "1qaz2wsx");
		
		return c;
	} 
	*/
	
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	/*
	public static synchronized UserDao getInstance(ConnectionMaker connectionMaker) {
		if(INSTANCE == null) INSTANCE = new UserDao(connectionMaker);
		return INSTANCE;
	}
	*/
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("DELETE FROM TOBY_DB.USER");
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public int getCount() throws SQLException {
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM TOBY_DB.USER");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
}
