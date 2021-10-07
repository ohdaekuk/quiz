package quizserver;

import java.sql.SQLException;
import java.util.Scanner;

import user.User;
import user.UserImp;

public class Join {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("아이디를 입력해주세요");
		String id = scan.next();
		
		System.out.println("비밀번호를 입력해주세요");
		String password = scan.next();
		
		System.out.println("나이를 입력해주세요");
		int age = scan.nextInt();
		
		System.out.println("성별을 입력해주세요");
		String gender = scan.next();
		
		System.out.println("별명을 입력해주세요");
		String nickName = scan.next();
		
		User user = new User(0, id, password, age, gender, nickName);
		
		try {
			UserImp.getInstance().insert(user);
			
			System.out.println("회원가입 완료");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
