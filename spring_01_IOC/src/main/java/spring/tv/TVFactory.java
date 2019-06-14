package spring.tv;

public class TVFactory {
	public static TV getLgTV(){
		return new LgTV();
	}

	public static TV getSamsungTV(){
		return new SamsungTV();
	}
	
	public static Object getBean(String beanName){
		if (beanName.equals("Samsung")) {
			return new SamsungTV();
		}else {
			return new LgTV();
		}
	}
	
}
