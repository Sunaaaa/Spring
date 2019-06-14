package spring.biz.user.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import mappers.UserMapper;
import spring.biz.user.vo.UserVO;

@Repository("mybatis")
public class UserDAO_MyBatis implements UserDAO{

	/*
	 * @Autowired
	 * 
	 * @Qualifier("sqlSession")
	 */	
	@Inject
	SqlSession sqlsession = null;
	
	UserMapper ui = null;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlsession = sqlSession;
		ui = sqlSession.getMapper(UserMapper.class);
	}
	/*
	 * public void getSqlSession() { return sqlSession; }
	 */
	

	/*
	 * @Override public UserVO login(String id, String pw) {
	 * System.out.println("UserDAO_MyBatis 연동 ");
	 * 
	 * UserVO user = new UserVO(); user.setUserid(id); user.setUserpwd(pw);
	 * 
	 * 
	 * return sqlsession.selectOne("user.login",user); }
	 */
	@Override
	public UserVO login(String id, String pw) {
		System.out.println("UserDAO_MyBatis 연동 ");
		
		UserVO user = new UserVO();
		user.setUserid(id);
		user.setUserpwd(pw);
		
		ui = sqlsession.getMapper(UserMapper.class);
		System.out.println("mybatis 인터페이스 기반 호출");
		return ui.login(user);
	}

	@Override
	public int addUser(UserVO user) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlsession.insert("user.adduser", user);
	}

	@Override
	public UserVO getUser(String userid) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlsession.selectOne("user.getuser", userid);
	}
	/*
	 * @Override public List<UserVO> getlistUser() {
	 * System.out.println("UserDAO_MyBatis 연동 ");
	 * 
	 * return sqlsession.selectList("user.getuserlist"); }
	 */
	

	@Override
	public List<UserVO> getlistUser() {
		System.out.println("UserDAO_MyBatis 연동 ");
		ui = sqlsession.getMapper(UserMapper.class);
		return ui.getuserlist();
	}
	

	@Override
	public int updateUser(UserVO user) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlsession.update("user.updateuser", user);
		
	}

	@Override
	public int remove(String userid) {
		System.out.println("UserDAO_MyBatis 연동 ");
		return sqlsession.delete("user.deleteuser", userid);
	}

	@Override
	public List<UserVO> search(String condition, String keyword) {
		System.out.println("UserDAO_MyBatis 연동 ");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(condition, keyword);
		return sqlsession.selectList("user.search",map);
	}

	
}
