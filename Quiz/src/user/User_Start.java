package user;

import java.sql.SQLException;

public class User_Start {
	public static void main(String[] args) {
		
		UserDao dao = UserImp.getInstance();
		
		try {
			dao.start();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}	
