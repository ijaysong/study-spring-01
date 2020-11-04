package com.project.toby.test;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project.toby.dao.CountingConnectionMaker;
import com.project.toby.dao.CountingDaoFactory;
import com.project.toby.dao.UserDao;

public class UserDaoConnectionCountingTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// DAO 사용 코드
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection Counter : " + ccm.getCounter());
	}
}
