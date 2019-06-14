package spring.biz.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import spring.biz.user.dao.UserDAO;
import spring.biz.user.vo.UserVO;

//@Component("userservice")
@Service("userservice")
public class UserServiceImpl implements UserService{
	
	//Service에서 직접 DB연동 하지 않음,,,  DAO를 통해 DB연동 수행-> 어떤 방법으로 DB연동을 할지 결정됨
	//set, 생성자를 통해 주소를 지정한다.
	
//	@Autowired
//	@Qualifier("jdbc")
	@Resource(name="jdbc")
	UserDAO dao;

	public UserServiceImpl() {	}
	
	public UserServiceImpl(UserDAO dao) {
		super();
		this.dao = dao;
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@Override
	public UserVO login(String id, String pw) {
		return dao.login(id, pw);
	}

	@Override
	public int addUser(UserVO user) {
		return dao.addUser(user);
	}

	@Override
	public UserVO getUser(String userid) {
		return dao.getUser(userid);
	}

	@Override
	public List<UserVO> getlistUser() {
		return dao.getlistUser();
	}

	@Override
	public int updateUser(UserVO user) {
		return dao.updateUser(user);
	}

	@Override
	public int remove(String userid) {
		return dao.remove(userid);
	}

	@Override
	public List<UserVO> search(String condition, String keyword) {
		return dao.search(condition, keyword);
	}
	
	
	
}
