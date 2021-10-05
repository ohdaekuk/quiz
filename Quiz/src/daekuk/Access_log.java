package daekuk;

import java.time.LocalTime;

public class Access_log {
	
	private int user_num;
	private LocalTime access_time;
	
	public Access_log(int user_num, LocalTime access_time) {
		this.user_num = user_num;
		this.access_time = access_time;
	} 
	
	public Access_log() {
		
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public LocalTime getAccess_time() {
		return access_time;
	}

	public void setAccess_time(LocalTime access_time) {
		this.access_time = access_time;
	}

	@Override
	public String toString() {
		return "Access_log [user_num=" + user_num + ", access_time=" + access_time + "]";
	}
	
	

}
