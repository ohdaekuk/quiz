package end;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class End_All {
	public static void main(String[] args) {
		
		try {
			Scanner scan = new Scanner(System.in);
			
			EndDao dao = EndImp.getInstance();
			
			String select = "";
			
			while (!select.equals("4")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 게임 종료하기 2. 종료시간 조회하기 3. 종료시간기록 삭제하기 4. 종료하기");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("게임 종료하기");
					
					while(true) {
						
						System.out.println("종료시간을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						End end = new End(0, LocalDateTime.now(), nickName);
						
						dao.endInsert(end);
						
						System.out.println("종료시간이 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("종료시간 조회하기");
					
					while(true) {
						
						
						// user_num 넣어서 조회하기
//						List<End> endfindbyUserNumList = dao.findbyUserNum();
//						
//						System.out.println(endfindbyUserNumList);
						
						// 전체 시간 조회하기
						List<End> endList = dao.endFindAll();
						
						System.out.println(endList);
						
						System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
						
						select = scan.nextLine();
						
						break;
						
					}
				}
				if(select.equals("3")) {
					System.out.println("종료시간 조회하기");
					
					while(true) {
						
						int delete = dao.delete();
						
						System.out.println(delete + "row가 삭제되었습니다.");
						break;
					}
				}
			}
			
			System.out.println("종료되었습니다.");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
