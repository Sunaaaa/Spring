package spring.biz.user.dao;

import java.util.List;

import spring.biz.user.vo.UserVO;

public interface UserDAO {
	
	public UserVO login(String id, String pw);
	
	public int addUser(UserVO user);
	
	public UserVO getUser(String userid);
	
	public List<UserVO> getlistUser();
	
	public int updateUser(UserVO user);
	
	public int remove(String userid);
	
	public List<UserVO> search(String condition, String keyword);

}
