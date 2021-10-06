package ranking;

import java.sql.SQLException;
import java.util.List;

import score.Score;

public interface RankingDao {
	
	// 랭킹 저장
	public int insert(Ranking ranking) throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 조회하는 메소드
	public List<Ranking> findByuserNum() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_nickName으로 조회하는 메소드
	public List<Ranking> findByuserNickName() throws ClassNotFoundException, SQLException;
	
	// DB에서 final_rank으로 조회하는 메소드
	public List<Ranking> findByfinal_rank() throws ClassNotFoundException, SQLException;
	
	// DB에서 전체 조회하는 메소드
	public List<Ranking> rankingFindAll() throws ClassNotFoundException, SQLException;
	
	// DB에 user_num으로 삭제하는 메소드
	public int delete() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 수정하는 메소드
	public int update() throws ClassNotFoundException, SQLException;
	
	// EndAll
	public Ranking start()  throws ClassNotFoundException, SQLException;
	
	// auto_increment 초기화
	public Ranking auto() throws ClassNotFoundException, SQLException;
	
}
