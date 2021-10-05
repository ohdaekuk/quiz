package daekuk;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {
	
	// DB에 User 객체를 삽입
	public int insert(User user) throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 조회하는 메소드
	public List<User> userFindByUser_num() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_id로 조회하는 메소드
	public List<User> userFindByUser_Id() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_id로 조회하는 메소드
	public List<User> userFindByUser_nickName() throws ClassNotFoundException, SQLException;
	
	// DB에서 age로 조회하는 메소드
	public List<User> userFindByAge() throws ClassNotFoundException, SQLException;
	
	// DB에서 gender로 조회하는 메소드
	public List<User> userFindByGender() throws ClassNotFoundException, SQLException;
	
	// DB에서 전체 조회하는 메소드
	public List<User> userFindAll() throws ClassNotFoundException, SQLException;
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete() throws ClassNotFoundException, SQLException;
	
	// Db에 user를 수정하는 메소드 
	public int update();
	
}
