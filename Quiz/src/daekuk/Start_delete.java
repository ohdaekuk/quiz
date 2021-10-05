package daekuk;

import java.sql.SQLException;

public class Start_delete {
	public static void main(String[] args) {
		
		StartDao dao = StartImp.getInstance();
		
		try {
			dao.delete();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
