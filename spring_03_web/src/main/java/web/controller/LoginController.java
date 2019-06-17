package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javafx.scene.control.Alert;
import spring.biz.user.service.UserService;
import spring.biz.user.vo.UserVO;

@Controller
public class LoginController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(name="/login.do", method = RequestMethod.GET)
	public	String login() {
		//view 페이지를 리턴해줌
			return "login";					
	}

	@RequestMapping(name="/login.do", method = RequestMethod.POST)
	public	String loginProc(UserVO vo, HttpServletRequest request) {
		
		UserVO user = service.login(vo.getUserid(), vo.getUserpwd());
		
		if (user != null) {
			
			request.getSession().setAttribute("User", user);
//			session.setAttribute("Userid", userid);
			
			request.getSession().setAttribute("Username", user.getUsername());
			request.getSession().setAttribute("Login", user);
			
			return "redirect:/user/list.do";
		}else {
			request.setAttribute("msg", "로그인 정보를 다시 입력하세요.");
			//view 페이지를 리턴해줌
			return "login";			

		}
		
	}

	/*
	 * @RequestMapping("/logout.do") public String logout(HttpServletRequest
	 * request) { request.getSession().invalidate(); request.setAttribute("msg",
	 * "로그아웃 되었습니다."); //view 페이지를 리턴해줌 return "login";
	 * 
	 * }
	 */

}
