package daekuk;

import java.sql.SQLException;

public class User_insert {
	public static void main(String[] args) {
		
		UserDao dao = UserImp.getInstance();
		
		User user = new User(0, "dheornr0322", "dkssud12", 27, "남", "오뎅");
		
		try {
			dao.insert(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
