package daekuk;

import java.time.LocalDateTime;

public class Start {
	
	private int user_num;
	private LocalDateTime start_time;
	
	public Start(int user_num, LocalDateTime start_time) {
		this.user_num = user_num;
		this.start_time = start_time;
	}
	
	public Start() {
		
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
		return "Start [user_num=" + user_num + ", start_time=" + start_time + "]";
	}
	
	
}
