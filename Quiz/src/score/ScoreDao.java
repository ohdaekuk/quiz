package score;

import java.sql.SQLException;
import java.util.List;

import end.End;

public interface ScoreDao {
	
	// 점수 저장
	public int insert(Score score) throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 조회하는 메소드
	public List<Score> findByuserNum() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_nickName으로 조회하는 메소드
	public List<Score> findByuserNickName() throws ClassNotFoundException, SQLException;
	
	// DB에서 final_score으로 조회하는 메소드
	public List<Score> scoreFindByFinal_score() throws ClassNotFoundException, SQLException;
	
	// DB에서 전체 조회하는 메소드
	public List<Score> scoreFindAll() throws ClassNotFoundException, SQLException;
	
	// DB에 user_nickName으로 삭제하는 메소드
	public int delete() throws ClassNotFoundException, SQLException;
	
	// DB에서 user_num으로 수정하는 메소드
	public int update() throws ClassNotFoundException, SQLException;
	
	// EndAll
	public Score start()  throws ClassNotFoundException, SQLException;
	
	// auto_increment 초기화
	public Score auto() throws ClassNotFoundException, SQLException;

} 
