package spring.biz.user.service;
import java.util.List;

import spring.biz.user.vo.UserVO;

public interface UserService {
	
	UserVO login(String id, String pw);
	
	int addUser(UserVO user);
	
	UserVO getUser(String userid);
	
	List<UserVO> getlistUser();
	
	int updateUser(UserVO user);
	
	int remove(String userid);
	
	List<UserVO> search(String condition, String keyword);

}
