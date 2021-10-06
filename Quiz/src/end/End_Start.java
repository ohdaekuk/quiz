package end;

import java.sql.SQLException;

public class End_Start {
	public static void main(String[] args) {
		
		EndDao dao = EndImp.getInstance();
		
		try {
			dao.start();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
