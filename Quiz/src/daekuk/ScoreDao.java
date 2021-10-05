package daekuk;

import java.util.List;

public interface ScoreDao {
	
	// 점수 저장
	public int scoreInsert(Score score);
	
	// DB에서 user_num으로 조회하는 메소드
	public Score scoreFindByuserNum(int user_num);
	
	// DB에서 user_nickName으로 조회하는 메소드
	public Score scoreFindByuserNickName(int user_nickName);
	
	// DB에서 final_score으로 조회하는 메소드
	public Score scoreFindByFinal_score(int final_score);
	
	// DB에서 전체 조회하는 메소드
	public List<Score> rankingFindAll();
	
	// DB에 user_nickName으로 삭제하는 메소드
	public int delete(String user_nickName);

}
