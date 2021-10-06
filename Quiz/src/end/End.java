package end;

import java.time.LocalDateTime;

public class End {
	
	private int user_num;
	private LocalDateTime end_time;
	private String user_nickName;
	
	public End(int user_num, LocalDateTime end_time, String user_nickName) {
		this.user_num = user_num;
		this.end_time = end_time;
		this.user_nickName = user_nickName;
	}
	
	public End() {
		 
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}
	
	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}
		
	@Override
	public String toString() {
		return "End [user_num=" + user_num + ", end_time=" + end_time + ", user_nickName=" + user_nickName + "]";
	}
	
	
}
