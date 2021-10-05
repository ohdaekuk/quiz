package daekuk;

import java.sql.SQLException;
import java.util.List;

public class Access_logImp implements Access_logDao{

	@Override
	public int accessInsert(Access_log access) {
		return 0;
	}

	@Override
	public List<Access_log> findbyUserNum(int user_num) {
		return null; 
	}

	@Override
	public List<Access_log> Access_logFindAll() {
		return null;
	}

	@Override
	public int delete(int user_num) throws ClassNotFoundException, SQLException {
		return 0;
	}

}
