package daekuk;

import java.sql.SQLException;

public class Ranking_update {
	public static void main(String[] args) {
		
		RankingDao dao = RankingImp.getInstance();
		
		try {
			int update =  dao.update();
			
			if (update > 0) {
				System.out.println("수정 성공!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}	
