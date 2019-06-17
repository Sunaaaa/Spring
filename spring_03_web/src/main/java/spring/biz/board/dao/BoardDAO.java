package spring.biz.board.dao;

import java.util.List;

import spring.biz.board.vo.BoardVO;

public interface BoardDAO {
	public void insertBoard(BoardVO vo);

	public void updateBoard(BoardVO vo);

	public void deleteBoard(BoardVO vo);

	public BoardVO getBoard(BoardVO vo);

	public List<BoardVO> getBoardList(BoardVO vo);
	
	public List<BoardVO> search(String condition, String keyword);
}
