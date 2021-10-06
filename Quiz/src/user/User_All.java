package user;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import ranking.Ranking;
import ranking.RankingDao;
import ranking.RankingImp;

public class User_All {
	public static void main(String[] args) {
		
		try {
			
			UserDao dao = UserImp.getInstance();
			
			Scanner scan = new Scanner(System.in);
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 회원가입 2. 정보 조회하기 3. 정보 삭제하기 4. 정보 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("회원가입");
					
					while(true) {
						
						System.out.println("아이디를 입력해주세요.");
						String id = scan.next();
						
						System.out.println("비밀번호를 입력해주세요.");
						String password = scan.next();
						
						System.out.println("나이를 입력해주세요.");
						int age = scan.nextInt();
						
						System.out.println("성별을 입력해주세요.");
						String gender = scan.next();
						
						System.out.println("닉네임을 입력해주세요.");
						String nickName = scan.next();
						
						User user = new User(0, id, password, age, gender, nickName);
						
						dao.insert(user);
						
						System.out.println("회원가입을 완료했습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("정보 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. id로 조회하기 3. 나이로 조회하기 4. 성별로 조회하기 5. 닉네임으로 조회하기 6. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num으로 조회하기
							
							List<User> userList1 = dao.userFindByUser_num();
							
							System.out.println(userList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// id로 조회하기
							List<User> userList2 = dao.userFindByUser_Id();
							
							System.out.println(userList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 나이로 조회하기
							List<User> userList3 = dao.userFindByAge();
							
							System.out.println(userList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 성별로 조회하기
							List<User> userList4 = dao.userFindByGender();
							
							System.out.println(userList4);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("5")) {
							
							// 닉네임으로 조회하기
							List<User> userList5 = dao.userFindByUser_nickName();
							
							System.out.println(userList5);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("6")) {
							
							// 전체 정보 조회하기
							List<User> userList6 = dao.userFindAll();
							
							System.out.println(userList6);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						}
						break;
					}
				}
				if(select.equals("3")) {
					System.out.println("점수 삭제하기");
					
					while(true) {
						
						int delete = dao.delete();
						
						System.out.println(delete + "row가 삭제되었습니다.");
						
						dao.auto();
						
						break;
					}
				}if(select.equals("4")) {
					System.out.println("점수 수정하기");
					
					while(true) {
						
						dao.update();
						
						System.out.println("수정되었습니다.");
						break;
					}
				}
			}
			System.out.println("종료되었습니다.");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

