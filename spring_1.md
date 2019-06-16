# Maven 

: 자바 프로젝트의 빌드를 자동화 해주는 빌트 tool

​	-> 자바 소스를 컴파일, 패키징, deploy하는 일을 Maven이 자동으로 수행한다.



# Spring

#### 환경설정

#### pom.xml

: POM(Project Object Model) 을 설정하는 파일로, 프로젝트 내의 빌드 옵션을 설정한다.

: dependencies에 들어있는 dependency로 인하여 Maven Dependencies에 junit이 들어있다.

- <https://mvnrepository.com/artifact/org.springframework/spring-context/4.3.10.RELEASE>에서 아래의 코드를 받아 pom.xml에 추가하면 Maven Dependencies에 해당 lib가 추가된다.

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>4.3.10.RELEASE</version>
</dependency>
==> spring관련 핵심lib
```

** 버전 등을 수정해도 즉시 적용된다. 



- ojdbc를 찾지 못하여 error 발생 -> ojdbc관련 lib를 추가한다.

```
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0.3</version>
		</dependency>

```



- dependency 위에 repository 추가 == 사설 repository처럼 사용 가능 

```
	<repositories>
		<!-- 오라클 드라이버 -->
		<repository>
			<id>codelds</id>
			<url>https://code.lds.org/nexus/content/groups/main-repo</url>
		</repository>
	</repositories>
```

- 프로젝트 -> Build Path
  - JRE System lib를 지우고 그냥  add ==> j2EE-1.5를 jdk로 변경

![1560134827363](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560134827363.png)



#### applicationContext.xml

: 대부분의 IoC 컨테이너는 각 컨테이너에서 관리할 객체들을 위한 별도의 설정 파일이 있다.  스프링 프레임워크가 관리할 컨테이너들이 등록된 xml 설정 파일 이다. 



*** spring의 환경설정 파일은 여러개로 쪼개어 분리하여 관리 할 수 있다. 

- 기능별로 분리해서 사용

```
		String[] config =	{"applicationContext.xml"};
```



## BeanFactory

- BeanFactory는 spring(등록한 bean들의 lifecycle을 관리, bean을 메모리에 올리고 내리는 일)의 가장 기본, core 기능을 지원하는 인터페이스
- applicationContext.xml에 등록된 <bean> 객체를 생성하고 관리한다. 
- 지연 로딩(Lazy Loading) 방식
- 프로그램이 실행하자마자 등록된  <bean> 객체를 생성, 메모리에 띄우는 것이 아니라 클라이언트의 요청(LookUp)이 들어올 때 (getBean()) 메모리에 띄운다.



- 환경설정 파일 (applicationContext.xml)을 로딩하여 스프링 컨테이너 중 하나인 BeanFactory를 구동한다.
- getBean() 메소드를 통하여, 구동된 컨테이너로부터 객체(LgTV)를 요청(LookUp)한다.
- user.on() 메소드를 통하여, Lg TV의 on() 메소드를 호출한다. 
- factory.close() : 스프링 컨테이너를 종료한다.

```java
String config =	"applicationContext.xml";
BeanFactory factory = new XmlBeanFactory(new ClassPathResource(config));
		
TV user = (TV)factory.getBean("lg");
user.on();

factory.close();
```




- getBean(id값)로 메소드가 수행되기 때문에 , 만약 id가 설정되지 않았다면, 가장 맨 앞글자를 소문자로 바꾸어 id를 자동으로 만든다.

```
		TV user = (TV)factory.getBean("samsung");
		user.on();
```



## ApplicationContext == Spring Context 

## ApplicationContext

- bean factory보다 확장된 기능을 지원하는 인터페이스
- 비즈니스 레이어를 만들어 application context가 이를 관리한다.
- BeanFactory가 제공하는 <bean>객체 관리 기능 외에도 트랜잭션 관리, 메시지 기반의 다국어 처리  등 다양한 기능을 지원한다.
- 즉시 로딩(pre-loding) 방식
  - 컨테이너가 구동되는 시점에 <bean> 으로 등록된 클래스들을 객체 생성한다. 프로그램이 실행하자마자 등록된 객체를 메모리에 띄운다. 필요에 따라 메모리에 올리는 시점을 지연시킬 수 있다(옵션). 



```java
ApplicationContext context = new ClassPathXmlApplicationContext(config);
```

- 2가지 선언 방법

```java
		TV user = (TV)applicationContext.getBean("lg");
		user.on();
		
		TV user2 = applicationContext.getBean("lg", TV.class);
		user2.on();
```



- 요청이 들어올 때 객체를 메모리에 띄우는 방법
  
  - lazy-init (: default = false) 속성
  
    : samsung 객체가 요청이 들어올 때 객체를 생성하고, 그렇지 않은 경우 객체 생성을 이뤄지지 않는다. 

```xml
<bean id="samsung" class="spring.tv.SamsungTV" lazy-init="true"></bean>
```



- getBean()을 여러번 해도 객체는 한번만 생성된다. == Singleton

  - scope (: default = singleton) 속성

    : 클라이언트의 요청 횟수와 관계없이, 한번 생성된 객체는 메모리에 하나만 생성되어 유지된다.

    ** scope="prototype" 인 경우, 해당 <bean> 이 요청될 때마다 새로운 객체를 생성한다.

  - scope ="singleton"

    ```xml
    <bean id="lg" class="spring.tv.LgTV" scope="singleton"></bean>
    ```

    ![1560144467271](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560144467271.png)

    

  - scope ="prototype"

    ```xml
    <bean id="lg" class="spring.tv.LgTV" scope="prototype"></bean>
    ```

    ![1560144621854](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560144621854.png)

- Calendar는 Calendar()으로 객체 생성이 불가 => Error 발생

```
		Calendar now = (Calendar) applicationContext.getBean("cal");
```



- 생성자가 아닌 생성을 해주는 메서드가 있다. === getInstance()

- factory-method="getInstance" 속성 부여

```
<bean id="now" class="java.util.Calendar" factory-method="getInstance"></bean>
```



- 필요 시, init(), destroy()를 직접 지정할 수 있다.

```
	<bean id="lg" class="spring.tv.LgTV" lazy-init="true" scope="prototype"
				init-method="init" destroy-method="destroy"></bean>
```

![1560145613341](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1560145613341.png)



***

**

- spring.biz : 비즈니스 레이어 
- spring.web : web단
- spring.aop : 양쪽에서 사용

**

- 다양한 방법으로 DB연동을 하기 위하여 DAO를 Interface로 구현한다. 











































































































