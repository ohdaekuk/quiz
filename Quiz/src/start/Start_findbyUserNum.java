package start;

import java.sql.SQLException;
import java.util.List;

public class Start_findbyUserNum {
	public static void main(String[] args) {
		
		StartDao dao = StartImp.getInstance();
		
		try {
			List<Start> startList = dao.findbyUserNum();
			
			System.out.println(startList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
