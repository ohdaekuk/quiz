package daekuk;

import java.sql.SQLException;
import java.util.List;

public interface RankingDao {
	
	// 랭킹 저장
	public int rankInsert(Ranking ranking) throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 조회하는 메소드
	public Ranking rankingFindByuserNum();
	
	// DB에서 user_nickName으로 조회하는 메소드
	public Ranking rankingFindByuserNickName();
	
	// DB에서 전체 조회하는 메소드
	public List<Ranking> rankingFindAll();
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete();
	 
}
