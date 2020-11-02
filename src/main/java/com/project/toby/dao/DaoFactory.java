package com.project.toby.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserDao의 생성 책임을 맡은 팩토리 클래스
 * 팩토리란, 객체의 생성 방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 것
 *
 */

// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
@Configuration
public class DaoFactory {
	
	// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	/*
	@Bean
	public UserDao userDao() {
		ConnectionMaker connectionMaker = new ConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		
		return userDao;
	}
	*/
	
	// 팩토리의 메소드는 UserDao 타입의 오브젝트를 어떻게 만들고, 어떻게 준비시킬지를 결정한다.
	public UserDao userDao() {
		ConnectionMaker connectionMaker = new DConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);

		return userDao;
	}
	
	/*
	public AccountDao accontDao() {
		return new AccountDao(connectionMaker());
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}
	*/
	
	/*
	 * DB 연결
	 */
	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker(); // 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
	}

}
