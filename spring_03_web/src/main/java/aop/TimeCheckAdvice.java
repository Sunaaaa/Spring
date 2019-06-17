package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeCheckAdvice {
	static final Logger logger = LoggerFactory.getLogger("root");
	
	
	@Pointcut("execution(* spring.biz.*.service.*Service.login(..))")
	public void pointcut_3() {}

	@Around("pointcut_3()")
	public Object check(ProceedingJoinPoint pp) throws Throwable {

		long stime = System.currentTimeMillis();
		
		Object obj = pp.proceed();
		String name = pp.getSignature().getName();
		
		long etime = System.currentTimeMillis();
		
		long perf = etime-stime;		
		System.out.println(name + "=>" + perf + "(ms)초");
		
		return obj;
		
	}
}
