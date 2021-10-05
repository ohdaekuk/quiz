package daekuk;

import java.util.List;


public interface UserDao {
	
	// DB에 User 객체를 삽입
	public int insert(User user);
	
	// DB에서 user_num으로 조회하는 메소드
	public User userFindById(int user_num);
	
	// DB에서 user_id로 조회하는 메소드
	public List<User> userFindByUser_Id(String user_id);
	
	// DB에서 전체 조회하는 메소드
	public List<User> userFindAll();
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete(int user_num);
	
	// Db에 employee를 수정하는 메소드
	public int update(User user);
	
}
