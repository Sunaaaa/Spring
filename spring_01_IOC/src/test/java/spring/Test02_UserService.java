package spring;

import spring.biz.user.dao.UserDAO;
import spring.biz.user.dao.UserDAO_JDBC;
import spring.biz.user.service.UserService;
import spring.biz.user.service.UserServiceImpl;
import spring.biz.user.vo.UserVO;

public class Test02_UserService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		UserDAO dao = null;

		//항상 JDBC기반으로만 DB연동	-> UserDAO를 인터페이스로 만들지 않았으면 이렇게 해야함
//		UserDAO_JDBC dao = new UserDAO_JDBC();
		
		UserDAO dao = new UserDAO_JDBC();
		
		UserService service = new UserServiceImpl(dao);
		
//		service.login("admin", "1234");
//		service.login("admin", "a1234");


		System.out.println(service.login("admin", "1234"));
		System.out.println(service.login("admin", "a1234"));
		System.out.println("---------------------------------------------");
		for (UserVO vo : service.getlistUser()) {
			System.out.println(vo);
		}
	}

}
