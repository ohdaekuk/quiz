package score;

import java.sql.SQLException;

public class Score_Start {
	public static void main(String[] args) {
		
		ScoreDao dao = ScoreImp.getInstance();
		
		try {
			dao.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
