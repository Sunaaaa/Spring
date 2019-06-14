package spring.biz.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import spring.biz.board.vo.BoardVO;

@Repository("b_mybatis")
public class BoardDAO_MyBatis implements BoardDAO {
	/*
	 * @Autowired
	 * 
	 * @Qualifier("sqlSession_Board")
	 */	
	@Inject
	SqlSession sqlSession;

	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("UserDAO_MyBatis 연동 ");
		sqlSession.insert("board.insertboard", vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("UserDAO_MyBatis 연동 ");
		sqlSession.update("board.updateboard", vo);
		
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("UserDAO_MyBatis 연동 ");
		sqlSession.delete("board.deleteboard", vo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlSession.selectOne("board.getboard", vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlSession.selectList("board.getboardlist", vo);
	}

}
