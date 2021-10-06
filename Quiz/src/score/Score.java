package score;

public class Score {
	
	private int user_num;
	private String user_nickName;
	private int final_score;
	
	public Score(int user_num, String user_nickName, int final_score) {
		this.user_num = user_num;
		this.user_nickName = user_nickName;
		this.final_score = final_score;
	}

	public Score() {
		
	}
	
	public int getUser_num() {
		return user_num;
	}
 
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setuser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public int getFinal_score() {
		return final_score;
	}

	public void setFinal_score(int final_score) {
		this.final_score = final_score;
	}

	@Override
	public String toString() {
		return "Score [user_num=" + user_num + ", user_nickName=" + user_nickName + ", final_score=" + final_score
				+ "]";
	}
	
}
