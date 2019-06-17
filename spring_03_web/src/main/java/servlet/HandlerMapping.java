package servlet;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

public class HandlerMapping {
	
	HashMap<String, Controller> map = new HashMap<String, Controller>();
	
	public HandlerMapping() {
		map.put("/list.do", new ListController());
		map.put("/login.do", new LoginController());
		map.put("/home.do", new HomeController());
	}
	
	public Controller getController(String path) {
		return map.get(path);
	}
	
}
