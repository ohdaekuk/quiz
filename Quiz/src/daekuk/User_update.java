package daekuk;

import java.sql.SQLException;

public class User_update {
	public static void main(String[] args) {
		
		UserDao dao = UserImp.getInstance();
		
		try {
			dao.update();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
