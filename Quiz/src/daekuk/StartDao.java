package daekuk;

import java.util.List;

public interface StartDao {
	
	// 시작시간 저장
	public int startInsert(Start start);
	
	// user_num으로 시작시간 조회
	public List<Start> findbyUserNum(int user_num);
	
	// DB에서 전체 조회하는 메소드
	public List<Start> endFindAll();
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete(int user_num);
	
	 
	
}
