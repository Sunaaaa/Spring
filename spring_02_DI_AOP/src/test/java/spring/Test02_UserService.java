package spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.biz.board.service.BoardService;
import spring.biz.board.vo.BoardVO;
import spring.biz.user.service.UserService;
import spring.biz.user.vo.UserVO;

public class Test02_UserService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] config = {"applicationContext.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(config);
		
		BoardService service = (BoardService)context.getBean("boardservice");	
		UserService uservice = (UserService)context.getBean("userservice");
		
		UserVO uvo = uservice.login("java01", "1234");

		System.out.println(uvo.getUsername());
		BoardVO vo = new BoardVO();
/*
		vo.setTitle("임시제목");
		vo.setWriter("홍길동");
		vo.setContent("임시 내용-----");
		vo.setUserid("java01");
		service.insertBoard(vo);
		
		List<BoardVO> blist = service.getBoardList(vo);
		for (BoardVO b : blist) {
			System.out.println("--> " + b.getTitle());
		}
		
		vo.setSeq(5);
		service.deleteBoard(vo);

		vo.setSeq(1);
		BoardVO b = service.getBoard(vo);
		System.out.println(b.toString());
			
		vo.setContent("바뀐 Context");
		vo.setTitle("바뀐 Title");
		vo.setSeq(1);
		service.updateBoard(vo);
*/
		vo.setTitle("임시제목");
		vo.setWriter(uvo.getUsername());
		vo.setContent("임시 내용-----");
		vo.setUserid(uvo.getUserid());
		
		service.insertBoard(vo);
		
	}

}
