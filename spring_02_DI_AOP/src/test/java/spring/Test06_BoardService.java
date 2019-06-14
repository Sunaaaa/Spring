package spring;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import javax.annotation.Resource;
import javax.swing.Spring;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.biz.board.service.BoardService;
import spring.biz.board.vo.BoardVO;
import spring.biz.user.dao.UserDAO;
import spring.biz.user.dao.UserDAO_JDBC;
import spring.biz.user.service.UserService;
import spring.biz.user.service.UserServiceImpl;
import spring.biz.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test06_BoardService {

	@Resource(name="boardservice")
	BoardService service ;

	@Resource(name="userservice")
	UserService uservice;
	
//	@Test
	public void name() {
		BoardVO b = new BoardVO();
		for (BoardVO vo : service.getBoardList(b)) {
			System.out.println(vo);
		}		
	}

	@Test
	public void add() {
		BoardVO vo = new BoardVO();
		UserVO uvo = uservice.login("admin", "a1234");
				
		vo.setTitle("또또 임시제목");
		vo.setWriter(uvo.getUsername());
		vo.setContent("또또 임시 내용-----");
		vo.setUserid(uvo.getUserid());
		
		service.insertBoard(vo);		
		System.out.println("insert ==> ");
	}
//	@Test	
	public void update() {
		
		BoardVO vo = new BoardVO();
		vo.setContent("또또 바뀐 Context");
		vo.setTitle("또또 바뀐 Title");
		vo.setSeq(1);
		service.updateBoard(vo);

		System.out.println("update ==> " );
	}
//	@Test
	public void getUser() {
		
		BoardVO vo = new BoardVO();
		vo.setSeq(1);
		BoardVO b = service.getBoard(vo);
		System.out.println(b.toString());
	}


//	@Test
	public void delete() {
		
		BoardVO vo = new BoardVO();
		vo.setSeq(10);
		service.deleteBoard(vo);

	}
}
