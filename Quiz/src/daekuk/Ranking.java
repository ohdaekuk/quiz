package daekuk;

public class Ranking {
	
	private int user_num;
	private String user_nickName;
	private int final_rank;
	
	public Ranking(int user_num, String user_nickName, int final_rank) {
		this.user_num = user_num;
		this.user_nickName = user_nickName;
		this.final_rank = final_rank;
	}
	
	public Ranking() {
		
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

	public void setUser_nickname(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public int getFinal_rank() {
		return final_rank;
	}

	public void setFinal_rank(int final_rank) {
		this.final_rank = final_rank;
	}

	@Override
	public String toString() {
		return "Ranking [user_num=" + user_num + ", user_nickName=" + user_nickName + ", final_rank=" + final_rank
				+ "]";
	}
	
}
