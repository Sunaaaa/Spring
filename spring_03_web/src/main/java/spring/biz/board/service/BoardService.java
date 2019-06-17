package spring.biz.board.service;

import java.util.List;

import spring.biz.board.vo.BoardVO;

public interface BoardService {

	void insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo);
	
	List<BoardVO> search(String condition, String keyword);
	

}
