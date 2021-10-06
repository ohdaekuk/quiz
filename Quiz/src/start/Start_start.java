package start;

import java.sql.SQLException;

public class Start_start {
	public static void main(String[] args) {
		
		StartDao dao = StartImp.getInstance();
		
		try {
			dao.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
