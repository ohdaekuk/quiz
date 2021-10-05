package end;

import java.sql.SQLException;
import java.util.List;

public interface EndDao {
	
	// 종료시간 저장
	public int endInsert(End end) throws ClassNotFoundException, SQLException;
	
	// user_num으로 종료시간 조회
	public List<End> findbyUserNum() throws ClassNotFoundException, SQLException;
	
	// DB에서 전체 조회하는 메소드
	public List<End> endFindAll() throws ClassNotFoundException, SQLException;
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete() throws ClassNotFoundException, SQLException; 
	
}
