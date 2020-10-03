package com.project.toby.dao;

/**
 * 애플리케이션의 오브젝트들을 구성하고 그 관계를 정의하는 역할 수행
 *
 */
public class DaoFactory {
	
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
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker(); // 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
	}

}
