package com.project.toby.test;

import java.sql.SQLException;

import com.project.toby.dao.ConnectionMaker;
import com.project.toby.dao.DConnectionMaker;
import com.project.toby.dao.UserDao;
import com.project.toby.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		//UserDaoTemp dao = context.getBean("userDao", UserDaoTemp.class);
		//UserDao dao = new DaoFactory().userDao();
		
		// 클라이언트 오브젝트에서 구체적인 오브젝트를 지정해 dao클래스로 넘긴다.
		// DB 접속 방법을 수정해야 할 때도 오직 한 곳의 코드만 수정하면 된다.
		// 런타임 오브젝트 의존관계를 설정하는 역할을 한다.
		ConnectionMaker connectionMaker = new DConnectionMaker(); 
		// ConnectionMaker connectionMaker = new NConnectionMaker(); 
		
		UserDao dao = new UserDao(connectionMaker); 
		
		User user = new User();
		user.setId("hongkd");
		user.setName("홍길동");
		user.setPassword("1234");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "조회 성공");
	}
}
