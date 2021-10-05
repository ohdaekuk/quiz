package daekuk;

public class User {

	private int user_num;
	private String user_id;
	private String user_password;
	private int age;
	private String gender;
	private String user_nickName;
	
	public User(int user_num, String user_id, String user_password, int age, String gender, String user_nickName) {
		this.user_num = user_num;
		this.user_id = user_id;
		this.user_password = user_password;
		this.age = age;
		this.gender = gender;
		this.user_nickName = user_nickName;
	}
	
	public User() { 
		
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public String getUser_Id() {
		return user_id;
	}

	public void setUser_Id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_Password() {
		return user_password;
	}

	public void setUser_Password(String user_password) {
		this.user_password = user_password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getUser_nickName() {
		return user_nickName;
	}
	
	public void setUser_nickName(String user_nickName) {
		this.user_nickName = user_nickName;
	}

	@Override
	public String toString() {
		return "User [user_num=" + user_num + ", user_id=" + user_id + ", user_password=" + user_password + ", age=" + age + ", gender="
				+ gender + ", user_nickName=" + user_nickName + "]";
	}
	
	
	
}
