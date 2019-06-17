package web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.biz.board.service.BoardService;
import spring.biz.board.vo.BoardVO;
import spring.biz.user.vo.UserVO;

@Controller
public class BoardController {

	@Autowired
	@Qualifier(value = "boardservice")
	BoardService service;

	@RequestMapping(value = "/board/add.do", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		UserVO user = (UserVO)request.getAttribute("User");
		
		mav.addObject("user", user);
		mav.setViewName("board/board_write");

		return mav;
	}

	@RequestMapping(value = "/board/add.do", method = RequestMethod.POST)
	public String addUserProc(BoardVO vo, HttpServletRequest request) {
		System.out.println(vo);

		service.insertBoard(vo);

		return "redirect:/board/list.do";
	}

	@RequestMapping("/board/list.do")
	public ModelAndView list(BoardVO vo) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("boards", service.getBoardList(vo));
		mav.setViewName("board/board_list");

		return mav;
	}

	@RequestMapping("/board/search.do")
	public ModelAndView search(String searchCondition, String searchKeyword) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("boards", service.search(searchCondition, searchKeyword));
		mav.setViewName("board/board_list");
		return mav;
	}

	@RequestMapping("/board/remove.do")
	public String remove(int seq) {
		BoardVO b = new BoardVO();
		b.setSeq(seq);
		BoardVO vo = service.getBoard(b);
		service.deleteBoard(vo);
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/board/view.do")
	public ModelAndView getView(int seq) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO b = new BoardVO();
		b.setSeq(seq);
		BoardVO vo = service.getBoard(b);
		
		System.out.println(seq);
		System.out.println(b);
		System.out.println(vo);
		
		mav.addObject("board", service.getBoard(vo));
		mav.setViewName("board/board_view");

		return mav;
	}
	
	@RequestMapping("/board/modify.do")
	public ModelAndView modify(int seq) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO b = new BoardVO();
		b.setSeq(seq);
		BoardVO vo = service.getBoard(b);
		
		mav.addObject("board", service.getBoard(vo));
		mav.setViewName("board/board_modify");

		return mav;
	}
	
	@RequestMapping("/board/update.do")
	public ModelAndView update(@ModelAttribute("board") BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		

		
		service.updateBoard(vo);
		mav.addObject("board", service.getBoard(vo));
		mav.setViewName("board/board_view");

		return mav;
	}

}
