package daekuk;

import java.time.LocalTime;

public class Access_log { 
	
	private int user_num;
	private LocalTime access_time;
	private String user_nickName;
	
	public Access_log(int user_num, LocalTime access_time, String user_nickName) {
		this.user_num = user_num;
		this.access_time = access_time;
		this.user_nickName = user_nickName;
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
	
	public String getUser_nickName() {
		return user_nickName;
	}
	
	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	@Override
	public String toString() {
		return "Access_log [user_num=" + user_num + ", access_time=" + access_time + ", user_nickName=" + user_nickName + "]";
	}
	
	

}
