package daekuk;

import java.util.List;

public class ScoreImp implements ScoreDao{

	@Override
	public int scoreInsert(Score score) {
		return 0;
	}

	@Override
	public Score scoreFindByuserNum(int user_num) {
		return null;
	}

	@Override
	public Score scoreFindByuserNickName(int user_nickName) {
		return null;
	}

	@Override
	public Score scoreFindByFinal_score(int final_score) {
		return null;
	}

	@Override
	public List<Score> rankingFindAll() {
		return null;
	}

	@Override
	public int delete(String user_nickName) {
		return 0;
	}

}
