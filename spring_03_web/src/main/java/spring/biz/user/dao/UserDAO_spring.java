package spring.biz.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import spring.biz.user.vo.UserVO;
import util.JDBCUtil;

@Repository("spring")
public class UserDAO_spring implements UserDAO {

	@Autowired
	private JdbcTemplate template;

	public UserDAO_spring() {
		// DateSource가 있어야 동작함을 확인하기 위해 추가한 코드 --NOT 중요
		// DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// this.spring = new JdbcTemplate(dataSource);
	}

	@Override
	public UserVO login(String id, String pw) {
		System.out.println("UserDAO_spring 연동 ");
		System.out.println("Jdbc Template : " + template);
		String sql = "select * from userinfo where userid=? and userpwd = ?";

		UserVO user = null;

		try {
			user = template.queryForObject(sql, new Object[] { id, pw }, new UserRowMapper());

		} catch (Exception e) {
		}

		return user;
	}

	@Override
	public int addUser(UserVO user) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "insert into userinfo (userid, username, userpwd, email, phone,address) values (?, ?, ?, ?, ?, ?)";

		int a = template.update(sql, new Object[] { user.getUserid(), user.getUsername(), user.getUserpwd(), user.getEmail(),
				user.getPhone(), user.getAddress() });
		return a;
	}

	@Override
	public UserVO getUser(String userid) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "select * from userinfo where userid = ?";

		UserVO user = null;
		try {
			user = template.queryForObject(sql, new Object[] { userid }, new UserRowMapper());

		} catch (Exception e) {
		}

		return user;
	}

	@Override
	public List<UserVO> getlistUser() {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "select * from userinfo ";

		List<UserVO> list = new ArrayList<UserVO>();
		list = template.query(sql, new UserRowMapper());

		return list;
	}

	@Override
	public int updateUser(UserVO user) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "update userinfo set email=?,phone=?,address=? where userid =?";
		
		int a = template.update(sql, new Object[] {user.getEmail(), user.getAddress(), user.getUserid()});

		return a;
	}

	@Override
	public int remove(String userid) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "delete from userinfo where  userid  = ? ";
		
//		int a = template.update(sql, userid);
		int b = template.update(sql, new Object[] {userid});
//		return a;
		return b;
	}

	@Override
	public List<UserVO> search(String condition, String keyword) {
		System.out.println("UserDAO_spring 연동 ");
		String sql = "select * from userinfo where " + condition + " like '%'||?||'%'";

		// 만약, %가 없으면
		// String sql = "select * from userinfo where "+condition +" like ?";
//		list = template.query(sql, new Object[] {"%", keyword, "%" }, new UserRowMapper());

		/*
		 * List<UserVO> list = null; list = template.query(sql, new Object[] {condition,
		 * keyword}, new UserRowMapper()); return list;
		 */
		return template.query(sql, new Object[] {keyword}, new UserRowMapper());
	}
}

// 결과값 핸들링
class UserRowMapper implements RowMapper<UserVO> {

	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO user = new UserVO();

		user.setUserid(rs.getString("userid"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setUsername(rs.getString("username"));
		user.setUserpwd(rs.getString("userpwd"));

		return user;
	}

}
