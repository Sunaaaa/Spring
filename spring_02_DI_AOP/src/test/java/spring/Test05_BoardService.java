package spring;

import static org.junit.Assert.*;

import java.security.Provider.Service;

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
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class Test05_BoardService {

	@Autowired
	BoardService service;

	@Test 
	public void name() { 
		BoardVO board = new BoardVO();
		for (BoardVO vo : service.getBoardList(board)) {
			System.out.println(vo);
		}
	}

//	@Test
	public void insert() {
		BoardVO board = new BoardVO();
		board.setUserid("java02");
		board.setContent("으아아아아`~~~~~~~~");
		board.setTitle("이름");
		board.setWriter("길동씨");

		service.insertBoard(board);
		System.out.println("insert 수행");
	} 
	
	// @Test
	public void update() {

		BoardVO board = service.getBoard(new BoardVO());

		board.setTitle("바뀐 title");
		board.setContent("바뀐 Content");
		service.updateBoard(board);

	}

//	@Test
	public void remove() {
	//	int row = service.remove("java02");
	//	System.out.println("delete ==> " + row);
	}

}
