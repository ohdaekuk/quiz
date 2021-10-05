package daekuk;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Start_insert {
	public static void main(String[] args) {
		
		StartDao dao = StartImp.getInstance();
		
		Start start = new Start(0, LocalDateTime.now(), "피카츄");
		
		try {
			dao.startInsert(start);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
