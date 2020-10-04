package com.project.toby.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 오브젝트들을 구성하고 그 관계를 정의하는 역할 수행
 *
 */

// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
@Configuration
public class DaoFactory {
	
	// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
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
