package quizserver;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import end.End;
import end.EndImp;
import ranking.Ranking;
import ranking.RankingImp;
import score.Score;
import score.ScoreImp;
import start.Start;
import start.StartImp;
import user.User;
import user.UserDao;
import user.UserImp;

public class Manager {
	public static void main(String[] args) {
		
		try {
			Scanner scan = new Scanner(System.in);
			
			String select = "";
			
			while (!select.equals("4")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 정보 조회하기 2. 정보 삭제하기 3. 정보 수정하기 4. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					while(true) {
						
					System.out.println("어떤 정보를 조회하시겠습니까?");
					System.out.println("1. 회원정보 2. 시작시간 3. 종료시간 3. 점수 4. 랭킹");
					select = scan.nextLine();
					
					if(select.equals("1")) {
						System.out.println("1. user_num으로 조회하기 2. id로 조회하기 3. 나이로 조회하기 4. 성별로 조회하기 5. 닉네임으로 조회하기 6. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num으로 조회하기
							
							List<User> userList1 = UserImp.getInstance().userFindByUser_num();
							
							System.out.println(userList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// id로 조회하기
							List<User> userList2 = UserImp.getInstance().userFindByUser_Id();
							
							System.out.println(userList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 나이로 조회하기
							List<User> userList3 = UserImp.getInstance().userFindByAge();
							
							System.out.println(userList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 성별로 조회하기
							List<User> userList4 = UserImp.getInstance().userFindByGender();
							
							System.out.println(userList4);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("5")) {
							
							// 닉네임으로 조회하기
							List<User> userList5 = UserImp.getInstance().userFindByUser_nickName();
							
							System.out.println(userList5);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("6")) {
							
							// 전체 정보 조회하기
							List<User> userList6 = UserImp.getInstance().userFindAll();
							
							System.out.println(userList6);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						}
					}
					
					
					if(select.equals("2")) {
						System.out.println("시작시간 조회하기");
						
						while(true) {
							System.out.println("1. user_num으로 조회하기 2. 전체 조회하기");
							select = scan.nextLine();
							if(select.equals("1")) {
								
//								 user_num 넣어서 조회하기
								
								List<Start> startfindbyUserNumList = StartImp.getInstance().findbyUserNum();
								
								System.out.println(startfindbyUserNumList);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("2")) {
								
								// 전체 시간 조회하기
								List<Start> startList = StartImp.getInstance().startFindAll();
								
								System.out.println(startList);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							}
						}
						
					}
					if(select.equals("3")) {
						System.out.println("종료시간 조회하기");
						
						while(true) {
							System.out.println("1. user_num으로 조회하기 2. 전체 조회하기");
							select = scan.nextLine();
							if(select.equals("1")) {
								
//									 user_num 넣어서 조회하기
								
								List<End> endfindbyUserNumList = EndImp.getInstance().findbyUserNum();
								
								System.out.println(endfindbyUserNumList);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("2")) {
								
								// 전체 시간 조회하기
								List<End> endList = EndImp.getInstance().endFindAll();
								
								System.out.println(endList);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							}
						}
						
					}
					if(select.equals("4")) {
						System.out.println("점수 조회하기");
						
						while(true) {
							System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 점수로 조회하기 4. 전체 조회하기");
							select = scan.nextLine();
							if(select.equals("1")) {
								
								// user_num 넣어서 조회하기
								
								List<Score> scoreList1 = ScoreImp.getInstance().findByuserNum();
								
								System.out.println(scoreList1);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("2")) {
								
								// user_nickName으로 조회하기
								List<Score> scoreList2 = ScoreImp.getInstance().findByuserNickName();
								
								System.out.println(scoreList2);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("3")) {
								
								// 최종점수로 조회하기
								List<Score> scoreList3 = ScoreImp.getInstance().scoreFindByFinal_score();
								
								System.out.println(scoreList3);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("4")) {
								
								// 전체 시간 조회하기
								List<Score> scoreList4 = ScoreImp.getInstance().scoreFindAll();
								
								System.out.println(scoreList4);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							}
						}
						
					}
					if(select.equals("5")) {
						System.out.println("랭킹 조회하기");
						
						while(true) {
							System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 랭킹으로 조회하기 4. 전체 조회하기");
							select = scan.nextLine();
							if(select.equals("1")) {
								
								// user_num 넣어서 조회하기
								
								List<Ranking> rankingList1 = RankingImp.getInstance().findByuserNum();
								
								System.out.println(rankingList1);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("2")) {
								
								// user_nickName으로 조회하기
								List<Ranking> rankingList2 = RankingImp.getInstance().findByuserNickName();
								
								System.out.println(rankingList2);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("3")) {
								
								// 최종랭킹으로 조회하기
								List<Ranking> rankingList3 = RankingImp.getInstance().findByfinal_rank();
								
								System.out.println(rankingList3);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							} if(select.equals("4")) {
								
								// 전체 랭크 조회하기
								List<Ranking> rankingList4 = RankingImp.getInstance().rankingFindAll();
								
								System.out.println(rankingList4);
								
								System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
								
								select = scan.nextLine();
								
							}
							
						}
						
					}
					break;
					}
				}if(select.equals("2")) {
					System.out.println("어떤 정보를 삭제하시겠습니까?");
					System.out.println("1. 회원정보 2. 시작시간 3. 종료시간 3. 점수 4. 랭킹");
					select = scan.nextLine();
					
					if(select.equals("1")) {
						System.out.println("회원정보 삭제하기");
						
						while(true) {
							
							int delete = UserImp.getInstance().delete();
							
							System.out.println(delete + "row가 삭제되었습니다.");
							
							UserImp.getInstance().auto();
							
							break;
						}
						
					}
					if(select.equals("2")) {
						System.out.println("시작시간 삭제하기");
						
						while(true) {
							
							int delete = StartImp.getInstance().delete();
							
							System.out.println(delete + "row가 삭제되었습니다.");
							
							StartImp.getInstance().auto();
							
							break;
						}
						
					}
					if(select.equals("3")) {
						System.out.println("종료시간 삭제하기");
						
						while(true) {
							
							int delete = EndImp.getInstance().delete();
							
							System.out.println(delete + "row가 삭제되었습니다.");
							
							EndImp.getInstance().auto();
							
							break;
						}
						
					}
					if(select.equals("4")) {
						System.out.println("점수 삭제하기");
						
						while(true) {
							
							int delete = ScoreImp.getInstance().delete();
							
							System.out.println(delete + "row가 삭제되었습니다.");
							
							ScoreImp.getInstance().auto();
							
							break;
						}
						
					}
					if(select.equals("5")) {
						System.out.println("랭킹 삭제하기");
						
						while(true) {
							
							int delete = RankingImp.getInstance().delete();
							
							System.out.println(delete + "row가 삭제되었습니다.");
							
							RankingImp.getInstance().auto();
							
							break;
						}
					}
				}
				if(select.equals("3")) {
					System.out.println("정보 수정하기");
					System.out.println("1. 회원정보 2. 점수 3. 랭킹");
					select = scan.nextLine();
					
					if(select.equals("1")) {
						System.out.println("회원정보 수정하기");
						
						while(true) {
								
								UserImp.getInstance().update();
								
								System.out.println("수정되었습니다.");
								
								break;
						}
						
					}
					if(select.equals("2")) {
						System.out.println("점수 수정하기");
						
						while(true) {
							
							ScoreImp.getInstance().update();
							
							System.out.println("수정되었습니다.");
							
							break;
						}
						
					}
					if(select.equals("3")) {
						System.out.println("랭킹 수정하기");
						
						while(true) {
							
							RankingImp.getInstance().update();
							
							System.out.println("수정되었습니다.");
							
							break;
						}
						
					}
				}
				
			}
			
			System.out.println("종료되었습니다.");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}



			
			
