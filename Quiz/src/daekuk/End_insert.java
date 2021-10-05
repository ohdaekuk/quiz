package daekuk;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class End_insert {
	public static void main(String[] args) {
		
		EndDao dao = EndImp.getInstance();
		
		End end = new End(0, LocalDateTime.now(), "피카츄");
		
		try {
			int insert = dao.endInsert(end);
			
			if (insert > 0) {
				System.out.println("삽입 완료");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
