package com.project.toby.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.project.toby.dao.UserDao;
import com.project.toby.domain.User;

public class UserDaoTest {

//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		/*
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		UserDao dao = context.getBean("userDao", UserDao.class);
//		*/
//		
//		ApplicationContext context = new GenericXmlApplicationContext("com/project/toby/application.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
//		//UserDao dao = new DaoFactory().userDao();
//		
//		// 클라이언트 오브젝트에서 구체적인 오브젝트를 지정해 dao클래스로 넘긴다.
//		// DB 접속 방법을 수정해야 할 때도 오직 한 곳의 코드만 수정하면 된다.
//		// 런타임 오브젝트 의존관계를 설정하는 역할을 한다.
//		//ConnectionMaker connectionMaker = new DConnectionMaker(); 
//		//ConnectionMaker connectionMaker = new NConnectionMaker(); 
//		
//		//UserDao dao = new UserDao(connectionMaker); 
//		//UserDao dao = new DaoFactory().userDao();
//		
//		User user = new User();
//		user.setId("hongkd");
//		user.setName("홍길동");
//		user.setPassword("1234");
//		
//		dao.add(user);
//		
//		User user2 = dao.get(user.getId());
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		
//		if (!user.getName().equals(user2.getName())) {
//			System.out.println("테스트 실패 (name)");
//		}
//		else if (!user.getPassword().equals(user2.getPassword())) {
//			System.out.println("테스트 실패 (password)");
//		}
//		else {
//			System.out.println("조회 테스트 성공");
//		}
//	}
	
	public static void main(String[] args) {
		JUnitCore.main("com.project.toby.test.UserDaoTest");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("com/project/toby/application.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		User user = new User();
		user.setId("kimcs");
		user.setName("김철수");
		user.setPassword("5678");
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));
		
		User user2 = dao.get(user.getId());
		
		assertThat(user.getName(), is(user2.getName()));
		assertThat(user.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("com/project/toby/application.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user1 = new User("hongkd", "홍길동", "1234");
		User user2 = new User("kimcs", "김철수", "5678");
		User user3 = new User("kimyh", "김영희", "9012");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
}
