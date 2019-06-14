package spring.tv;

public class TVUser02 {

	public static void main(String[] args) {

		TV user = (TV)TVFactory.getBean("Lg");
		user.on();
		
		
	}

}
