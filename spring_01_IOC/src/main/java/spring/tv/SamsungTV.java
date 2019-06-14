package spring.tv;

public class SamsungTV implements TV{
	
	public SamsungTV() {
		System.out.println("SAMSUNG TV 생성");
	}
	
	@Override
	public void on() {
		System.out.println("SAMSUNG ON");
	}

	@Override
	public void off() {
		System.out.println("SAMSUNG OFF");
	}	
	
}
