package spring.tv;

public class LgTV implements TV {

	public LgTV() {
		System.out.println("LG TV 생성");
	}
	
	@Override
	public void on() {
		System.out.println("LG ON");
	}

	@Override
	public void off() {
		System.out.println("LG OFF");
	}
	
	public void init() {
		System.out.println("LG init() === 초기화 작업");
	}
	public void destroy() {
		System.out.println("LG destroy() === 자원 반납");
	}
}
