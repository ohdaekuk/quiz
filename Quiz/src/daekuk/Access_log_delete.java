package daekuk;

import java.sql.SQLException;

public class Access_log_delete {
	public static void main(String[] args) {
		
		Access_logDao dao = Access_logImp.getInstance();
		
		try {
			int delete = dao.delete();
			
			if (delete > 0) {
				System.out.println("삭제 완료");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
