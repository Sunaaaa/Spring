package spring.biz.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import spring.biz.board.vo.BoardVO;
import util.JDBCUtil;

@Repository("b_jdbc")
public class BoardDAO_JDBC implements BoardDAO {

	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("UserDAO_jdbc 연동 ");

		String sql = "insert into board (seq, title, writer, content, userid) values ((select NVL(max(seq),0)+1 from board), ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getWriter());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getUserid());
			row = ps.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}

	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("UserDAO_jdbc 연동 ");
		String sql = "update board set title=?,content=? where seq =?";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getSeq());
			row = ps.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}

	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("UserDAO_jdbc 연동 ");
		String sql = "delete from board where seq  = ? ";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getSeq());
			row = ps.executeUpdate();
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("UserDAO_jdbc 연동 ");
		String sql = "select * from board where seq  = ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		BoardVO board=null;
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getSeq());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));				
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}

		return board;
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("UserDAO_jdbc 연동 ");
		String sql = "select * from board order by seq DESC";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int row = 0;
		List<BoardVO> boards = new ArrayList<BoardVO>();
		
		try {
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));				
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				boards.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(con, ps, rs);
		}
		return boards;
	}

}
