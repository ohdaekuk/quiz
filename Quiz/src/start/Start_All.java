package start;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import end.End;

public class Start_All {
	public static void main(String[] args) {
		
		
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			StartDao dao = StartImp.getInstance();
			
			String select = "";
			
			while (!select.equals("4")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 게임 시작하기 2. 시작시간 조회하기 3. 시작기록 삭제하기 4. 종료하기");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("게임 시작하기");
					
					while(true) {
						
						System.out.println("시작시간을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						Start start = new Start(0, LocalDateTime.now(), nickName);
						
						dao.startInsert(start);
						
						System.out.println("시작시간이 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("시작시간 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
//							 user_num 넣어서 조회하기
							
							List<Start> startfindbyUserNumList = dao.findbyUserNum();
							
							System.out.println(startfindbyUserNumList);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// 전체 시간 조회하기
							List<Start> startList = dao.startFindAll();
							
							System.out.println(startList);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						}
						break;
					}
				}
				if(select.equals("3")) {
					System.out.println("시작시간 삭제하기");
					
					while(true) {
						
						int delete = dao.delete();
						
						System.out.println(delete + "row가 삭제되었습니다.");
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
