package daekuk;

import java.sql.SQLException;
import java.util.List;

public class Ranking_findByfinal_rank {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		try {
			
			List<Ranking> rankingList = dao.findByfinal_rank();
			
			System.out.println(rankingList);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
