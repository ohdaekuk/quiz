package score;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Score_All {
	public static void main(String[] args) {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			ScoreDao dao = ScoreImp.getInstance();
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 점수 저장하기 2. 점수 조회하기 3. 점수 삭제하기 4. 점수 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("점수 저장하기");
					
					while(true) {
						
						System.out.println("최종 점수를 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						System.out.println("최종 점수를 입력해주세요.");
						String finalScore = scan.nextLine();
						
						Score score = new Score(0, nickName, Integer.parseInt(finalScore));
						
						dao.insert(score);
						
						System.out.println("최종 점수가 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("점수 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 점수로 조회하기 4. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num 넣어서 조회하기
							
							List<Score> scoreList1 = dao.findByuserNum();
							
							System.out.println(scoreList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// user_nickName으로 조회하기
							List<Score> scoreList2 = dao.findByuserNickName();
							
							System.out.println(scoreList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 최종점수로 조회하기
							List<Score> scoreList3 = dao.scoreFindByFinal_score();
							
							System.out.println(scoreList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 전체 시간 조회하기
							List<Score> scoreList4 = dao.scoreFindAll();
							
							System.out.println(scoreList4);
							
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
