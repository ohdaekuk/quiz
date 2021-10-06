package quizserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import daekuk.RankingImp;
import daekuk.UserImp;
import quizio.QuizUtil;


public class MultiChatServer {
	private List<MultiChatServer.User> userList;
	private ServerSocket serverSocket;
	
	//QuizUtill: 퀴즈 관련 입출력 있는 메서드 모음 클래스.
	private QuizUtil util = QuizUtil.getUtil();
	private Map<String, String> quizList = util.quizToServer();
	
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
	
	
	public MultiChatServer(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		userList = new ArrayList<MultiChatServer.User>();
	}
	
	public void runServer() throws IOException{
		while(true) {
			System.out.println("접속 대기중 ...");
			Socket socket = serverSocket.accept();
			System.out.println("접속: " + socket.getInetAddress() + "- " + socket.getPort());
			User user = new User(socket);
			userList.add(user);
			user.start();
			
		}
	}

		
		
	class User extends Thread{
		private String name;
		private Socket socket;
		private BufferedWriter bw;
		private String password;
		private String id;
		private int score;
		
		public User(Socket socket) throws IOException{
			this.socket = socket;
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		
		String thisUserId = "";
		String thisUserPassword = "";
		String thisUserNickName = "";
		int thisUserNumber = 0;
		int thisUserScore = 0;
		int gameCount = 0;
		int userCount = 0;
		//여기 있는 변수 위에 있는 클래스랑 합치기.  
		
		
		//내부 클래스를 써서 데이터 베이스와 연결된 User객체와 이 객체 둘다 쓰는 것이 좋은지 아니면, 
		//내부 클래스를 없애고 데이터 베이스와 연결된 User객체 하나만 쓰는 것이 좋은지 
		
		
		//문제를 불러오는 메서드 
		//로그를 텍스트에 쓰는 메서드 
		@Override
		public void run() {
			Iterator<String> keys = quizList.keySet().iterator();
			//넥스트 한번 돌때마다 게임 카운트 숫자 올라감.
			String question = keys.next();
			gameCount++;
			//키 값으로 문제 정답 받기.
			String answer = quizList.get(question);
			
			daekuk.User thisUser = null;
			
			BufferedReader br = null;
			
			try {
				System.out.println("---퀴즈 프로그램 실행---");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				
				
				//로그인 로직 시작.
				while(true) {
					bw.write("아이디를 입력하세요");
					bw.flush();
					this.id = br.readLine();
					
					bw.write("비밀번호를 입력하세요");
					bw.flush();
					this.password = br.readLine();
					
					List<daekuk.User> dbUserList = UserImp.getInstance().userFindAll();
					//여기 나중에 한 폴더로 옮겨서 보기.
					
					

					
					for(daekuk.User u : dbUserList) {
						thisUserId = u.getUser_Id();
						//반복문 탈출하기 위해 최종 기록된 userId값을 저장.
						thisUserPassword = u.getUser_Password();
						//반복문 탈출하기 위해 최종 기록된 userPassword를 저장.
						
						if(thisUserId.equals(id)&&thisUserPassword.equals(password)) {
							bw.write("로그인되었습니다.");
							bw.flush();
							thisUserNumber = u.getUser_num();
							break;
						}
					}
					
					if(thisUserId.equals(id)&&thisUserPassword.equals(password)) {
						break;
					}else {
						bw.write("아이디 또는 비밀번호가 옳바르지 않습니다.");
						bw.flush();
					}
				}
				userCount++;
				//접속 성공하면 유저 카운트 올라감.
				
				
				
				
				
				for(User user : userList) {
						user.bw.write(thisUserNickName+"님이 접속하였습니다.");
						user.bw.newLine();
						user.bw.flush();
				}
				
				
				
				String msg = null;
				
				
				
				while(true) {
					
					if(userCount == 3) {
						for(User user : userList) {
							LocalDateTime startTime = LocalDateTime.now();
							user.bw.write("[게임을 시작하겠습니다.]");
							user.bw.write(question);						
							}
					}
					
					msg = br.readLine();
					//사용자가 보낸 메세지 읽고 
					util.saveLog(msg);
					//바로 저장한다.
					for(User user :userList) {
						if(this != user) {
							user.bw.write("["+thisUserNickName+"] : " + msg);
							user.bw.newLine();
							user.bw.flush();
						}
					}
					//모든 사람에게 메세지 보내기.

					
					if(msg == null) {
						throw new Exception();
					}
					
					
					//정답을 맞출때 로직 
					if(msg.equals(quizList.get(msg))) {
						//이터레이터로 키의 다음 값 문제 받기 
						question = keys.next();
						//맵에서 퀴즈 문제 받아서 다음 정답 세팅.
						quizList.get(question);
						//맞춘 사람 점수 올리기.
						thisUserScore += 10;
						//게임 숫자 카운트.
						gameCount++;
						//문제 모두에게 보내기.
						for(User user : userList) {
							user.bw.write("["+thisUserNickName+"] : " + "이 정답을 맞추었습니다.");
							user.bw.newLine();
							user.bw.flush();
							
							
							user.bw.write("다음문제는.. ");
							user.bw.flush();
							user.bw.write(question);
							
						}
					}
					
										
					
					//게임 종료 시점
					if(gameCount == 4) {
						//게임 종료시 게임 시작 시간 기록하기 위해 변수 선언.
						LocalDateTime endTime = LocalDateTime.now();
						for(User user : userList) {
							user.bw.write("게임이 종료되었습니다.");
							user.bw.newLine();
							user.bw.flush();
						}
						break;
						//일단 게임은 끝나는 걸로 해놓음. 
						//종료 되었으므로 게임 진행하며 저장된 사항 모두 저장
						//게임은 계속 진행하도록 할 것인지 아니면 찬반 투표를 할 것인지? 
					}
					
					
					
					
					  
				}
			}catch(Exception e) {
				userList.remove(this);
				try {
					userCount--;
					//접속 종료하면 유저 수 감소함. 
					
					for(User user : userList) {
						user.bw.write(thisUserNickName + "님이 방을 나갔습니다.");
						user.bw.newLine();
						user.bw.flush();
					}
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

