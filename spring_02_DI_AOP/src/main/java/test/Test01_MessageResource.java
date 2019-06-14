package test;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.biz.user.service.UserService;
import spring.biz.user.vo.UserVO;

public class Test01_MessageResource {

	public static void main(String[] args) {
		
		String[] config = {"applicationContext.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);
		
//		UserService service = (UserService)context.getBean("userservice");
		
//		System.out.println(context.getMessage("hello", null, Locale.KOREA));
//		System.out.println(context.getMessage("hello", null, Locale.ENGLISH));

//		String msg = context.getMessage("login.success", null, Locale.KOREA);
//		String msg = context.getMessage("login.success", new Object[] {"java01"}, Locale.KOREA);
//		System.out.println(msg);

		UserService service = (UserService)context.getBean("userservice");
//		service.login("admin", "a1234");
		UserVO vo = service.login("admin", "a1234");
		System.out.println(vo);

		System.out.println("== 끝 ==");
//		System.out.println(vo);
		
	}

}
