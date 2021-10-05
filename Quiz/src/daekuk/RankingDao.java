package daekuk;

import java.util.List;

public interface RankingDao {
	
	// 랭킹 저장
	public int rankInsert(Ranking ranking);
	
	// DB에서 user_num으로 조회하는 메소드
	public Ranking rankingFindByuserNum(int user_num);
	
	// DB에서 user_nickName으로 조회하는 메소드
	public Ranking rankingFindByuserNickName(int user_nickName);
	
	// DB에서 전체 조회하는 메소드
	public List<Ranking> rankingFindAll();
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete(int user_num);
	
}
