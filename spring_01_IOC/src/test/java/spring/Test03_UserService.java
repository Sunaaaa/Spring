package spring;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.biz.user.dao.UserDAO;
import spring.biz.user.dao.UserDAO_JDBC;
import spring.biz.user.service.UserService;
import spring.biz.user.service.UserServiceImpl;
import spring.biz.user.vo.UserVO;

public class Test03_UserService {

	UserService service ;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		
		UserDAO dao = new UserDAO_JDBC();
		service = new UserServiceImpl(dao);
	}

//	@Test
	public void login() {
		System.out.println(service.login("admin", "1234"));
		System.out.println(service.login("admin", "a1234"));
	}
//	@Test
	public void name() {
		for (UserVO vo : service.getlistUser()) {
			System.out.println(vo);
		}		
	}

//	@Test
	public void add() {
		UserVO user = new UserVO();
		user.setUserid("java02");
		user.setUsername("홍길동");
		user.setUserpwd("1234");
		user.setEmail("G@G.com");
		user.setPhone("070-1111-1112");
		user.setAddress("서울 역삼 멀캠");
		
		int row = service.addUser(user);
		System.out.println("insert ==> "+row);
	}
	@Test	
	public void update() {
		UserVO user = service.getUser("java01");
		user.setUserpwd("0000");
		user.setPhone("010-9999-9999");
		int row = service.updateUser(user);
		System.out.println("update ==> " + row);
	}
//	@Test
	public void getUser() {
		System.out.println(service.getUser("admin"));
	}
//	@Test
	public void searchUser() {
		for (UserVO vo : service.search("userid","a")) {
			System.out.println(vo);	
		}
		
	}

//	@Test
	public void remove() {
		int row = service.remove("java02");
		System.out.println("delete ==> "+row);
	}
}
