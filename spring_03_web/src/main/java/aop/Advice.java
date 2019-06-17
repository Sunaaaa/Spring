package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import spring.biz.user.vo.UserVO;

public class Advice {

	public void log(JoinPoint jp) {
		System.out.println("==== Log 시작 ====");
		System.out.println(jp.toString());
		System.out.println(jp.getArgs()[0]);;
		System.out.println("==== Log END ====");
	}
	
	public void after_Return(Object data){
		System.out.println("=== after_Return 시작 ===");
		System.out.println("return value : " + data);
		System.out.println("=== after_Return 끝 ===");
	}
	
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		
		System.out.println("=== around ===");		
		Object obj = jp.proceed();
		UserVO vo;
		if (obj!=null & obj instanceof UserVO) {
			vo = (UserVO)obj;
			vo.setUserpwd("*****");
		}
		
		System.out.println("=== around ===");		
		return obj;
	}
}
