package spring.biz.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import spring.biz.board.dao.BoardDAO;
import spring.biz.board.dao.BoardDAO_JDBC;
import spring.biz.board.vo.BoardVO;

@Service("boardservice")
public class BoardServiceImpl implements BoardService{

	@Resource(name="b_mybatis")
	BoardDAO dao;
	
	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}



	@Override
	public void insertBoard(BoardVO vo) {
		dao.insertBoard(vo);		
	}

	@Override
	public void updateBoard(BoardVO vo) {
		dao.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		dao.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		return dao.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.getBoardList(vo);
	}
	
	@Override
	public List<BoardVO> search(String condition, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
