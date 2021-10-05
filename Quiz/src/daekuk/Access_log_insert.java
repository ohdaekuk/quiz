package daekuk;

import java.sql.SQLException;
import java.time.LocalTime;

public class Access_log_insert {
	public static void main(String[] args) {
		
		Access_logDao dao = Access_logImp.getInstance();
		
		Access_log access = new Access_log(0, LocalTime.now(), "피카츄");
		
		try {
			
			int insert = dao.accessInsert(access);
			
			if(insert > 0) { 
				
				System.out.println("삽입 완료");
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
