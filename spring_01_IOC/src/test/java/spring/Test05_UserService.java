package spring;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import javax.swing.Spring;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.biz.user.dao.UserDAO;
import spring.biz.user.dao.UserDAO_JDBC;
import spring.biz.user.service.UserService;
import spring.biz.user.service.UserServiceImpl;
import spring.biz.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:user.xml"})
public class Test05_UserService {

	@Autowired
	UserService service ;
	
	@Test
	public void login() {
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
//	@Test	
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
