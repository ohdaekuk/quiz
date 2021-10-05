package daekuk;

import java.sql.SQLException;
import java.util.List;

public class Ranking_findByuserNickName {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		try {
			
			List<Ranking> rankingList = dao.findByuserNickName();
			
			System.out.println(rankingList);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
