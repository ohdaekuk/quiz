package daekuk;

import java.sql.SQLException;
import java.util.List;

public class EndImp implements EndDao{

	@Override
	public int endInsert(End end) {
		return 0;
	}

	@Override
	public List<End> findbyUserNum(int user_num) {
		return null;
	}

	@Override
	public List<End> endFindAll() {
		return null; 
	}

	@Override
	public int delete(int user_num) {
		return 0;
	}

}
