package daekuk;

import java.sql.SQLException;

public class Ranking_insert {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		Ranking ranking = new Ranking(0, "피카츄", 0);
		
		try {
			dao.rankInsert(ranking);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
