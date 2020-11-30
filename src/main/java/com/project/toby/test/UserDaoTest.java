package com.project.toby.test;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.toby.dao.UserDao;
import com.project.toby.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/project/toby/application.xml")
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
	
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	public static void main(String[] args) {
		JUnitCore.main("com.project.toby.test.UserDaoTest");
	}
	
	@Before
	public void setUp() {
		//ApplicationContext context = new GenericXmlApplicationContext("com/project/toby/application.xml");
		this.dao = this.context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("hongkd", "홍길동", "1234");
		this.user2 = new User("kimcs", "김철수", "5678");
		this.user3 = new User("kimyh", "김영희", "9012");
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		setUp();
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(userget1.getName()));
		assertThat(userget1.getPassword(), is(userget1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(userget2.getName()));
		assertThat(userget2.getPassword(), is(userget2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		setUp();
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	// 테스트 중에 발생할 것으로 기대되는 예외 클래스를 지정해준다.
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		setUp();
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id"); // 이 메소드 실행중에 예외가 발생해야 한다. 예외가 발생하지 않으면 테스트가 실패한다.
	}
}
