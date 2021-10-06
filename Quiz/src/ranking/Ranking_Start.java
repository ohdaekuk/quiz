package ranking;

import java.sql.SQLException;

public class Ranking_Start {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		try {
			dao.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
