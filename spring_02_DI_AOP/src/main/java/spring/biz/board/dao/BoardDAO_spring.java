package spring.biz.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.biz.board.vo.BoardVO;

@Repository("b_spring")
public class BoardDAO_spring implements BoardDAO {

	@Autowired
	JdbcTemplate template;

	public BoardDAO_spring() {
		
	}
	
	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "insert into board (seq, title, writer, content, userid) values ((select NVL(max(seq),0)+1 from board), ?, ?, ?,?)";
		template.update(sql, new Object[] {vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getUserid()});
	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "update board set title=?,content=? where seq =?";
		template.update(sql, new Object[] {vo.getTitle(), vo.getContent(), vo.getSeq()});
		
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "delete from board where seq  = ? ";
		template.update(sql, new Object[] {vo.getSeq()});
		
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "select * from board where seq  = ? ";
		return template.queryForObject(sql, new Object[] {vo.getSeq()}, new BoardRowMapper());
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "select * from board order by seq DESC";
		return template.query(sql, new BoardRowMapper());
	}

}

//결과값 핸들링
class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO b = new BoardVO();
		
		b.setCnt(rs.getInt("cnt"));
		b.setContent(rs.getString("content"));
		b.setTitle(rs.getString("title"));
		b.setUserid(rs.getString("userid"));
		b.setWriter(rs.getString("writer"));
		b.setSeq(rs.getInt("seq"));
		b.setRegdate(rs.getDate("regdate"));
		
		return b;
	}

}


