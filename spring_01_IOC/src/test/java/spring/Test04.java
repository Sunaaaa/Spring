package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.biz.user.service.UserService;

public class Test04 {

	public static void main(String[] args) {
		
//		String[] config = {"user.xml", "applicationContext.xml"};
		String[] config = {"user.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);
		
		UserService service = (UserService)context.getBean("userservice");	
		System.out.println(service.login("admin", "a1234"));
		
//		context.getBean("lg");
/*
		System.out.println("user01 : "+context.getBean("user01"));
		System.out.println("user02 : "+context.getBean("user02"));
		System.out.println("user03 : "+context.getBean("user03"));
*/
		
	}

}
