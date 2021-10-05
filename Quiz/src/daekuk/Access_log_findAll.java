package daekuk;

import java.sql.SQLException;
import java.util.List;

public class Access_log_findAll {
	public static void main(String[] args) {
		
		Access_logDao dao = Access_logImp.getInstance();
		
		try {
			
			List<Access_log> accessList = dao.Access_logFindAll();
			
			System.out.println(accessList);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
