package daekuk;

import java.sql.SQLException;
import java.util.List;

public class Ranking_rankingFindAll {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		try {
			List<Ranking> rankingList = dao.rankingFindAll();
			
			System.out.println(rankingList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
