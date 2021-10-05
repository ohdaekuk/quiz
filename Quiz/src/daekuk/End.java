package daekuk;

import java.time.LocalDateTime;

public class End {
	
	private int user_num;
	private LocalDateTime end_time;
	
	public End(int user_num, LocalDateTime end_time) {
		this.user_num = user_num;
		this.end_time = end_time;
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

	@Override
	public String toString() {
		return "End [user_num=" + user_num + ", end_time=" + end_time + "]";
	}
	
	
}
