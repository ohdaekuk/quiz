package datebase;

import java.util.Scanner;

public class UserInfo {
	
	Scanner scan = new Scanner(System.in);
	
	String id = scan.next();
	String password = scan.next();
	int age = scan.nextInt();
	
	
	String sql = "insert into user values(0, ?, ?, ?, ?)";
	
}
