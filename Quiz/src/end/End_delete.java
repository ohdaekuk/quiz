package end;

import java.sql.SQLException;

public class End_delete {
	public static void main(String[] args) {
		
		EndDao dao = EndImp.getInstance();
		
		try {
			int delete = dao.delete();
			
			if(delete > 0) {
				System.out.println("삭제 성공!");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}	
