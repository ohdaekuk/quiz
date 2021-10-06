package quizio;

import java.io.IOException;
import java.util.Scanner;

public class QuizManager {
	public static void main(String[] args) throws IOException {
		
	
		QuizUtil util= QuizUtil.getUtil();
		Scanner scan = new Scanner(System.in);
		String button = "";
		while(!button.equals("4")) {
			System.out.println("퀴즈 관리자 매니저를 시작합니다.");
			System.out.println("1:[퀴즈 등록하기] 2: [퀴즈 삭제하기] 3: [퀴즈목록] 4: [종료하기]");
			button = scan.nextLine();
			
			if(button.equals("1")) {
				//퀴즈 등록하기.
				System.out.println("=>퀴즈 등록하기");
				while(true) {
					System.out.println("[문제를 입력하세요].");
					String question = scan.nextLine();
					System.out.println("문제 ["+question+"]가 맞습니까 1: [yes] 아니면 2: [no]");
					button = scan.nextLine();
					if(button.equals("1")) {
						System.out.println("문제가 추가 되었습니다.");
						while(true) {
							System.out.println("[정답을 등록해주세요.]");
							String answer = scan.nextLine();
							System.out.println("정답 ["+ answer+"]가 맞습니까? 1: [yes] 2: [no]");
							button = scan.nextLine();
							if(button.equals("1")) {
								util.mkQuiz(question, answer);
								System.out.println("문제와 정답이 추가되었습니다.");
								button = "";
								break;
							}
						}
						button = "";
						break;
					}
				}
			}
			//
			
			
			if(button.equals("2")) {
				System.out.println("=>퀴즈 삭제하기");
				while(true) {
					System.out.println("1: [삭제할 퀴즈 확인] 2: [삭제할 퀴즈 문제 적기] 3: [종료하기]");
					button = scan.nextLine();
					if(button.equals("1")) {
						//퀴즈 확인 메서드.
						util.loadQuiz();
					}
					if(button.equals("2")) {
						System.out.println("[삭제할 퀴즈를 적어주세요.]");
						String delQuiz = scan.nextLine();
						String delQuiz2 = util.delQuiz(delQuiz);
						System.out.println(delQuiz2 + ": 삭제되었습니다.");
					}
					if(button.equals("3")) {
						button = "";
						break;
					}
				}
			}
			
			if(button.equals("3")) {
				System.out.println("퀴즈 목록을 확인하시겠습니까? 1: [yes] 2: [menu]");
				button = scan.nextLine();
				if(button.equals("1")) {
					util.loadQuiz();
				}
			}
			
		}
		System.out.println("종료 되었습니다.");
		
		
		
		
		
		
		
		
		
	}
}
