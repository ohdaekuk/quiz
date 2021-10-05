package daekuk;

import java.sql.SQLException;
import java.util.List;

public class End_findbyUserNum {
	public static void main(String[] args) {
		
		EndDao dao = EndImp.getInstance();
		
		try {
			List<End> endList = dao.findbyUserNum();
			
			System.out.println(endList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
