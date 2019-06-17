package web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.biz.user.service.UserService;
import spring.biz.user.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	@Qualifier(value="userservice")
	UserService service;

	@RequestMapping("/user/list.do")
	public ModelAndView getUserList() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", service.getlistUser());
		mav.setViewName("/user/user_list");

		return mav;
	}

	/*
	 * @RequestMapping("/user/add.do") public ModelAndView addUser() { ModelAndView
	 * mav = new ModelAndView();
	 * 
	 * mav.setViewName("/user/user_write"); return mav; }
	 */
	@RequestMapping(value = "/user/add.do", method = RequestMethod.GET)
	public String addUser() {
		return "/user/user_write";
	}

	@RequestMapping(value = "/user/add.do", method = RequestMethod.POST)
	public String addUserProc( UserVO vo ,HttpServletRequest request) {

		int row = 0;
		
		row = service.addUser(vo);
		
		return "redirect:/user/list.do";
	}

	/*
	 * 
	 * @RequestMapping("user/view.do") public String getView(HttpServletRequest
	 * request) {
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * // mav.setViewName("/user/user_view");
	 * 
	 * return "/user/user_view"; }
	 */	
	@RequestMapping("/user/view.do")
	public ModelAndView getView(@RequestParam("userid") String id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", service.getUser(id));
		mav.setViewName("/user/user_view");
		return mav;
	}

	@RequestMapping("/user/remove.do")
	public String remove(String userid) {
		service.remove(userid);
		return "redirect:/user/list.do";
	}

	@RequestMapping("/user/modify.do")
	public ModelAndView modify(String userid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", service.getUser(userid));
		mav.setViewName("/user/user_modify");
		return mav;
	}

	@RequestMapping("/user/update.do")
	public ModelAndView update(@ModelAttribute("user") UserVO vo) {
		System.out.println(vo);
		ModelAndView mav = new ModelAndView();
		service.updateUser(vo);
		mav.addObject("user", service.getUser(vo.getUserid()));
		mav.setViewName("/user/user_view");
		return mav;
	}

	@RequestMapping("/user/search.do")
	public ModelAndView search(String searchCondition, String searchKeyword) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", service.search(searchCondition, searchKeyword));
		mav.setViewName("/user/user_list");
		
		return mav;	
	}
	
	@ExceptionHandler(Exception.class)
	public String Ex(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "error";
	}
	
	
}


