package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RankingImp implements RankingDao{
	
	private static RankingImp instance = new RankingImp();
	
	private RankingImp() {}
	
	public static RankingImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private Ranking convertRanking(ResultSet rs) throws SQLException {
		return new Ranking(rs.getInt("user_num"),
				rs.getString("user_nickName"),
				rs.getInt("final_rank"));
	}
	
	@Override
	public int rankInsert(Ranking ranking) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into Ranking value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, ranking.getUser_nickName());
			pst.setInt(2, ranking.getFinal_rank());
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public Ranking rankingFindByuserNum() {
		return null;
	}

	@Override
	public Ranking rankingFindByuserNickName() {
		return null;
	}

	@Override
	public List<Ranking> rankingFindAll() { 
		return null;
	}

	@Override
	public int delete() {
		return 0;
	}

}
