package daekuk;

import java.time.LocalDateTime;

public class Start {
	
	private int user_num;
	private LocalDateTime start_time;
	private String user_nickName;
	
	public Start(int user_num, LocalDateTime start_time, String user_nickName) {
		this.user_num = user_num;
		this.start_time = start_time;
		this.user_nickName = user_nickName;
	}
	
	public Start() {
		
	}

	public String getUser_nickName() {
		return user_nickName;
	}

	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	} 

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	@Override
	public String toString() {
		return "Start [user_num=" + user_num + ", start_time=" + start_time + ", user_nickName=" + user_nickName + "]";
	}
	
	
}
