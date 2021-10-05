package daekuk;

import java.sql.SQLException;
import java.util.List;

public interface Access_logDao {
	
	// 접속 기록 저장
	public int accessInsert(Access_log access) throws ClassNotFoundException, SQLException;
	
	// user_num으로 접속 기록 조회 
	public List<Access_log> findbyUserNum() throws ClassNotFoundException, SQLException;
	
	// DB에서 전체 조회하는 메소드
	public List<Access_log> Access_logFindAll() throws ClassNotFoundException, SQLException;
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete() throws ClassNotFoundException, SQLException;
}
