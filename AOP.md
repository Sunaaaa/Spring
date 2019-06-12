# Spring AOP

- 비즈니스 컴포넌트 개발에서는 낮은 결합도, 높은 응집도가 중요
- 낮은 결합도 -> IoC
- 높은 응집도 -> AOP



## AOP 

: 관계 지향형 언어

횡단 관심 : 핵심 관심을 제외한 부가적인 기능

핵심 관심 : 비즈니스 로직 



### Aspect = Advice + PointCut

- Advice 

  - 공통 기능, 부가 기능, 횡단관심 

  - logging, Exception, Transaction 등 

  - 공통 기능을 언제 핵심 로직에 적용할 지를 정의할 수 있다 .

    | 공통 기능 동작시점 | 설명                                   |
    | ------------------ | -------------------------------------- |
    | Before             | 비즈니스 메소드 실행 전                |
    | After              | 비즈니스 메소드 실행 이후              |
    | After Returning    | 비즈니스 메소드의 return 문 실행 이후  |
    | After Throwing     | 비즈니스 메소드 실행 중, 예외발생 직후 |
    | Around             | 비즈니스 메소드 자체를 가로챈다.       |

    ```java
    public void m(){
    	
    		@Before
    	try{
    	
    	}catch(Exception e){
    		@After-Throwing
            
    	}finally{
    		@After	
    	}
        
        return 		
            @After-Returning	
    	// 메소드 전체 : @Around	
    }
    ```



### JoinPoint 

 : 메소드의 이름, 메소드가 속한 클래스와 패키지 정보 등의 다양한 정보를 이용할 수 있도록 제공하는 인터페이스

: Client가 호출하는 모든 비즈니스 메소드



- #### JoinPoint 메소드

  |          메소드          |                             설명                             |
  | :----------------------: | :----------------------------------------------------------: |
  | Signature getSignature() | 메소드의 시그니처(return type, 이름, 매개변수) 정보가 저장되어 있는 Signature 객체를 반환한다. |
  |    Object getTarget()    |     비즈니스 메소드를 포함하는 비즈니스 객체를 반환한다.     |
  |    Object[] getArgs()    | 메소드를 호출할때 넘겨준 인자 목록을 Object 배열로 반환한다. |

  

  #### Signature 메소드

  |         메소드         |                             설명                             |
  | :--------------------: | :----------------------------------------------------------: |
  |    String getName()    |               호출한 메소드의 이름을 반환한다.               |
  | String toLongString()  | 호출한 메소드의 return type, 이름, 매개변수를 패키지 경로 포함하여 반환한다. |
  | String toShortString() |     호출한 메소드의 시그니처를 축약한 문자열로 반환한다.     |

  

### PointCut

: 필터링된 조인포인트 

: 공통 기능을 수행하는 일부 메소드에서만 수행시킬 수 있다.



- 적용할 메소드를 선택하는 Algorithm
- 부가기능을 적용하고 싶은 메소드를 찾는다.

- Pointcut 에 걸린 메소드는 런타임 시, 프록시 기반으로 동작한다. Client와 해당 메소드 사이에서 AOP가 프록시의 역할을 수행한다.



## AOP 엘레먼트 

: AOP 관련된 다양한 설정



- aop:aspect 태그 하위에 각각 지정하여 사용한다. 

- 개발자는 UserService의 모든 메서드에 log를 출력하는 코드를 작성하지 않아도 AOP가 log를 출력하는 기능을 지원한다.	

```xml
	<bean id="advice" class="aop.Advice"></bean>	
	<aop:config>	<!-- spring 설정 파일 내의 root elemetnt -->
		<aop:pointcut 
			expression="execution(* spring.biz.user.service.UserService.*(..))" 
				id="mp"/>

		<aop:aspect ref="advice">
			<aop:before method="log" pointcut-ref="mp" />
		</aop:aspect>
	</aop:config>
```



- <bean id="advice" class="aop.Advice"></bean> : Advice 클래스를 메모리에 띄움
- < aop:pointcut > : 포인트 컷을 지정하기 위해 사용된다. 

- <aop:pointcut "execution(* spring.biz.user.service.UserService.*(..))" id="mp"/> : 부가 기능을 적용할 메소드는 UserService의 모든 메서드로 pointcut을 지정한다. UserService 내의 모든 메소드가 부가기능을 AOP로 부터 지원받는다. 
- <aop:before method="log" pointcut-ref="mp" /> : id가 mp로, UserService의 모든 메소드는 before 시점에 log 를 위치시킨다.
- <aop:before method="log" pointcut-ref="mp" /> : 이름이 log()인 메소드는 Before 방식으로 동작하고 pointcut은 "mp"를 참조한다.



## 동작 시점

### Before

- 메소드가 실행되기 전에 처리할 내용들을 기술한다.



- Schema 기반 설정

  - xml 파일

  ```
  	<aop:aspect ref="advice">
  		<aop:before method="before" pointcut-ref="mp" />
  	</aop:aspect>
  ```

  - Advice

  ```java
  public class Advice{
      public void before(JoinPoint jp){
          String method = jp.getSignature().getName();
          Obejct[] args = jp.getArgs();
      }
  }
  ```

  

  ** 매개변수로  JoinPoint를 선언한다. JoinPoint를 이용하여 인자로 넘어준 값들을 받을 수 있다. 또, JoinPoint의 다양한 매소드를 이용하여 메소드 이름, 인자 목록 등을 알 수 있다. 

  

- Annotation 기반 설정

  - Advice

  ```java
  @Component
  @Aspect
  public class AnnotationAdvice {
  	
  	static final Logger logger = LoggerFactory.getLogger("root");
  
  	@Pointcut("execution(* spring.biz.user.service.UserService.login(..))")
  	public void pointcut_1() {
  		
  	}
  
  	@Before("pointcut_1()")
  	public void log(JoinPoint jp) {
  		Object[] args = jp.getArgs();
  		System.out.println(args[0]);
  		logger.info(jp.toString());
  	}
  	
  ```

  ** 클래스 선언부에 @Servoce와 @Aspect를 추가하여 Component Scan의 Aspect 객체로 인식되도록 설정한다. 상단에 선언한 Pointcut의 메소드가 호출되면 log() 메소드가 Before 형태로 동작하도록 @Before를 추가한다.



### After

- 예외 발생 여부와 관계 없이 무조건 동작한다.



- Schema 기반 설정

  - xml

  ```
  	<aop:aspect ref="advice">
  		<aop:after method="after" pointcut-ref="mp"/>
  	</aop:aspect>
  ```

  - Advice

  ```java
  public class Advice{
     	public void after(){
  		//사후 처리 로직
      }
  }
  ```

  

- Annotation 기반 설정

  - Advice

    ```java
    @Component
    @Aspect
    public class AnnotationAdvice {
    	
    	static final Logger logger = LoggerFactory.getLogger("root");
    
    	@Pointcut("execution(* spring.biz.user.service.UserService.login(..))")
    	public void pointcut_1() {
    		
    	}
    
    	@After("pointcut_1()")
    	public void log() {
    		
    	}
    ```

    

### After Throwing

- 메소드의 실행 도중 에외가 발생하는 시점에 동작한다.

- 주로, 예외처리 Advice 설정에 많이 사용된다. 

  

- Schema 기반 설정

  - xml 파일

  ```
  	<aop:aspect ref="advice">
  		<aop:after-throwing method="after_throw" pointcut-ref="mp" throwing="e"/>
  	</aop:aspect>
  ```

  - Advice 

  ```java
  public class Advice{
     	public void after_throw(JoinPoint jp, Exception e){
  		String method = jp.getSignature().getName();
          System.out.println("[예외처리]"+ method + "() 수행 중 발생한 에러 메시지 : " 
                             			+ e.getMessage());
      }
  }
  ```

  

- Annotation 기반 설정

  - throwing의 e와 메소드의 Exception 변수 e가 같아야함

    ```java
    	@AfterThrowing(pointcut="pointcut_2()", throwing = "e")
    	public void exception(JoinPoint jp, Exception e ) {
    		
    		logger.error(jp.getSignature().getName());
    		logger.error(e.getMessage());
    		logger.error("a");
    	}
    
    ```

    

### After Returning

- 메소드가 정상적으로 수행이 된 이후, 수행 결과로 생성된 데이터를 return 하는 시점에 동작한다.

- 수행 결과로 생성된 데이터를 핸들링하는 로직을 추가할 때 많이 사용된다.



- Schema 기반 설정

  - xml

  ```
  	<aop:aspect ref="advice">
  		<aop:after-returning method="after_Return" 
  								pointcut-ref="mp" returning="a"/>
  	</aop:aspect>
  ```

  - Advice

  ```
  	public void after_Return(Object data){
  		System.out.println("=== after_Return 시작 ===");
  		System.out.println("return value : " + data);
  		System.out.println("=== after_Return 끝 ===");
  	}
  	
  ```



- Annotation 기반 설정

  - Advice

    ```java
    	@AfterReturning(pointcut="pointcut_2()", returning = "a")
    	public void exception(JoinPoint jp, Object a ) {
    		
    		logger.info(jp.getSignature().getName());
    		logger.info("a");
    	}
    ```

    

### Around 

- 메소드 전체를 가로챈다.
- 비즈니스 메소드의 호출 전, 처리 이후의 로직을 수행할 수 있다. 
- proceed()를 해야 해당 메소드가 수행된다.
- 메소드의 성능 분석에 사용된다.



- Schema 기반 설정

  - xml 파일

  ```
  	<aop:aspect ref="advice">
  		<aop:around method="around" pointcut-ref="mp"/>
  	</aop:aspect>
  ```

  - Advice

  ```java
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
  ```

  



- Annotation 기반 설정

  - throwing의 e와 메소드의 Exception 변수 e가 같아야함

    ```java
    @Component
    @Aspect
    public class AnnotationAdvice {
    	
    	static final Logger logger = LoggerFactory.getLogger("root");
    
    	@Pointcut("execution(* spring.biz.user.service.UserService.login(..))")
    	public void pointcut_1() {
    		
    	}
    
    	@Around("pointcut_1()")
    	public void around(ProceedingJoinPoint pp) throws Throwable {
    
    		long stime = System.currentTimeMillis();
    		
    		Object obj = pp.proceed();
    		String name = pp.getSignature().getName();
    		
    		long etime = System.currentTimeMillis();
    		
    		long perf = etime-stime;		
    		System.out.println(name + "=>" + perf + "(ms)초");
    		
    		return obj;
    		
    	}
    ```

  

- 동작 시점 
  - aop:aspect 태그 하위에 각각 지정하여 사용한다. 
  - 개발자는 UserService의 모든 메서드에 log를 출력하는 코드를 작성하지 않아도 AOP가 log를 출력하는 기능을 지원한다.

```
	<bean id="advice" class="aop.Advice"></bean>
	<aop:config>
		<aop:pointcut 
			expression="execution(* spring.biz.user.service.UserService.*(..))" 
				id="mp"/>

<!-- ref는 Advice Bean의 id -->				
		<aop:aspect ref="advice">
			<aop:before method="log" pointcut-ref="mp" />
		</aop:aspect>
	</aop:config>


```

- <bean id="advice" class="aop.Advice"></bean> : Advice 클래스를 메모리에 띄움
- <aop:pointcut "execution(* spring.biz.user.service.UserService.*(..))" id="mp"/> : 부가 기능을 적용할 메소드는 UserService의 모든 메서드로 pointcut을 지정한다. UserService 내의 모든 메소드가 부가기능을 AOP로 부터 지원받는다. 
- <aop:before method="log" pointcut-ref="mp" /> : id가 mp로, UserService의 모든 메소드는 before 시점에 log 를 위치시킨다.

