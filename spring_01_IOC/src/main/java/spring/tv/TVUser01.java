package spring.tv;

public class TVUser01 {

	public static void main(String[] args) {
		TV user = new LgTV();
		user.on();

		user = new SamsungTV();
		
		user.on();
	}

}
