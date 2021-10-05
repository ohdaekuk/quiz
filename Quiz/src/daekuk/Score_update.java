package daekuk;

import java.sql.SQLException;

public class Score_update {
	public static void main(String[] args) {
		
		ScoreDao dao = ScoreImp.getInstance();
		
		try {
			int update = dao.update();
			
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
