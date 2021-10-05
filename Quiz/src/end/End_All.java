package end;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class End_All {
	public static void main(String[] args) {
		
		EndDao dao = EndImp.getInstance();
		
		End end = new End(0, LocalDateTime.now(), "피카츄");
		
		try {
			
			dao.delete();
			
			List<End> endAllList = dao.endFindAll();
			
			dao.endInsert(end);
			
			List<End> endfindbyUserNumList = dao.findbyUserNum();
			
			System.out.println(endAllList);
			
			System.out.println(endfindbyUserNumList);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
}
