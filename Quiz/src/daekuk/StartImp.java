package daekuk;

import java.util.List;

public class StartImp implements StartDao{

	@Override
	public int startInsert(Start start) {
		return 0;
	}

	@Override
	public List<Start> findbyUserNum(int user_num) {
		return null;
	}

	@Override
	public List<Start> endFindAll() {
		return null;
	}

	@Override
	public int delete(int user_num) { 
		return 0;
	}

}
