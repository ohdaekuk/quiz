package ranking;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Ranking_All {
	public static void main(String[] args) {
		
		try {
			
			RankingDao dao = RankingImp.getInstance();
			
			Scanner scan = new Scanner(System.in);
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 랭킹 저장하기 2. 랭킹 조회하기 3. 랭킹 삭제하기 4. 랭킹 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("랭킹 저장하기");
					
					while(true) {
						
						System.out.println("최종 랭킹을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						System.out.println("최종 랭킹을 입력해주세요.");
						String finalRank = scan.nextLine();
						
						Ranking ranking = new Ranking(0, nickName, Integer.parseInt(finalRank));
						
						dao.insert(ranking);
						
						System.out.println("최종 점수가 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("랭킹 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 랭킹으로 조회하기 4. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num 넣어서 조회하기
							
							List<Ranking> rankingList1 = dao.findByuserNum();
							
							System.out.println(rankingList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// user_nickName으로 조회하기
							List<Ranking> rankingList2 = dao.findByuserNickName();
							
							System.out.println(rankingList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 최종랭킹으로 조회하기
							List<Ranking> rankingList3 = dao.findByfinal_rank();
							
							System.out.println(rankingList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 전체 랭크 조회하기
							List<Ranking> rankingList4 = dao.rankingFindAll();
							
							System.out.println(rankingList4);
							
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
