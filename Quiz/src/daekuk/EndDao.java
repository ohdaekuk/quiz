package daekuk;

import java.util.List;

public interface EndDao {
	
	// 종료시간 저장
	public int endInsert(End end);
	
	// user_num으로 종료시간 조회
	public List<End> findbyUserNum(int user_num);
	
	// DB에서 전체 조회하는 메소드
	public List<End> endFindAll();
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete(int user_num); 
	
}
