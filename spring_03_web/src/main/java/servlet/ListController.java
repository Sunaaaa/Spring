/**
 * 
 */
package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.biz.user.dao.UserDAO;
import spring.biz.user.dao.UserDAO_JDBC;
import spring.biz.user.service.UserService;
import spring.biz.user.service.UserServiceImpl;
import spring.biz.user.vo.UserVO;

//list.do의 요청에 반응할 클래스
public class ListController implements Controller{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		UserDAO dao = new UserDAO_JDBC();
		UserService service = new UserServiceImpl(dao);
		List<UserVO> list = service.getlistUser();
		
		request.setAttribute("users", list);

		return "/list.jsp";
	}

}

//login.do의 요청에 반응할 클래스
class LoginController implements Controller{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {

		return "/login.jsp";
	}

}

//home.do의 요청에 반응할 클래스
class HomeController implements Controller{

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {

		return "/index.jsp";
	}

}