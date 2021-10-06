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

import end.End;
import end.EndImp;
import quizio.QuizUtil;
import ranking.Ranking;
import score.Score;
import score.ScoreImp;
import start.Start;
import start.StartImp;
import user.User;
import user.UserImp;


public class MultiChatServer {
	private List<MultiChatServer.Client> clientList;
	//접속자
	private ServerSocket serverSocket;
	private List<User> userList;
	//DB에 저장된 유저.
	
	//QuizUtill: 퀴즈 관련 입출력 있는 메서드 모음 클래스.
	private QuizUtil util = QuizUtil.getUtil();
	private Map<String, String> quizList = util.quizToServer();
	int gameCount = 0;
	int connCount = 0;
	private LocalDateTime startTime;
	private LocalDateTime finishTime;
	
	
	public MultiChatServer(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		clientList = new ArrayList<MultiChatServer.Client>();
		userList = new ArrayList<User>();
		
		
	}
	
	public void runServer() throws IOException{
		while(true) {
			System.out.println("접속 대기중 ...");
			Socket socket = serverSocket.accept();
			System.out.println("접속: " + socket.getInetAddress() + "- " + socket.getPort());
			Client client = new Client(socket);
			clientList.add(client);
			client.start();
			
		}
	}

		
		
	class Client extends Thread{
		private String clientName;
		private Socket socket;
		private BufferedWriter bw;
		private String clientPassword;
		private String clientId;
		private int clientScore;
		//준비 된 상태 1, 준비 안된 상태 0
		int ready=0;
		//ready를 바꿀 수 있는 상태 1, 바꿀 수 없는 상태 0
		int changeableReady = 1;
		
		public Client(Socket socket) throws IOException{
			this.socket = socket;
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		


		
		
		//내부 클래스를 써서 데이터 베이스와 연결된 User객체와 이 객체 둘다 쓰는 것이 좋은지 아니면, 
		//내부 클래스를 없애고 데이터 베이스와 연결된 User객체 하나만 쓰는 것이 좋은지 
		
		
		//문제를 불러오는 메서드 
		//로그를 텍스트에 쓰는 메서드 
		@Override
		public void run() {
			User user = new User();
			int clientScore = 0;
			//User의 필드 [num, id, pass, age, gender, nickname], score는 스코어 필드에.
			Iterator<String> keys = quizList.keySet().iterator();
			//넥스트 한번 돌때마다 게임 카운트 숫자 올라감.
			String question = keys.next();
			gameCount++;
			//키 값으로 문제 정답 받기.
			String answer = quizList.get(question);
			
			
			BufferedReader br = null;
			
			try {
				System.out.println("---퀴즈 프로그램 실행---");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				
				
				//로그인 로직 시작.
				while(true) {
					
					bw.write("아이디를 입력하세요");
					bw.flush();
					this.clientId = br.readLine();
					
					bw.write("비밀번호를 입력하세요");
					bw.flush();
					this.clientPassword = br.readLine();
					
					List<User> dbUserList = UserImp.getInstance().userFindAll();
					//여기 나중에 한 폴더로 옮겨서 보기.
					
					

					
					for(User u : dbUserList) {
						user.setUser_Id(u.getUser_Id());
						//반복문 탈출하기 위해 최종 기록된 userId값을 저장.
						user.setUser_Password(u.getUser_Password());
						//반복문 탈출하기 위해 최종 기록된 userPassword를 저장.
						
						if(user.getUser_Id().equals(clientId)&&user.getUser_Password().equals(clientPassword)) {
							bw.write("로그인되었습니다.");
							bw.flush();
							//아이디와 비밀번호에 해당되는 user의 정보가 위에서 선언한 User의 객체에 저장됨.
							user = u;
							userList.add(user);
							break;
						}
					}
					
					if(user.getUser_Id().equals(clientId)&&user.getUser_Password().equals(clientPassword)) {
						break;
					}else {
						bw.write("아이디 또는 비밀번호가 옳바르지 않습니다.");
						bw.flush();
					}
				}
				connCount++;
				//접속 성공하면 유저 카운트 올라감.
				
				
				
				
				
				for(Client client : clientList) {
						client.bw.write(user.getUser_nickName()+"님이 접속하였습니다.");
						client.bw.newLine();
						client.bw.flush();
				}
				
				
				
				String msg = null;
				
				//조건 문이 실행 가능 상태일때는 1 아니면 0. 
				int startOnOff = 1;
				int endOnOff = 1;
				
				while(true) {
					
					msg = br.readLine();
					//사용자가 보낸 메세지 읽고 
					util.saveLog("["+user.getUser_nickName()+"] : " + msg);
					//바로 저장한다.
					for(Client client :clientList) {
						if(this != clientList) {
							client.bw.write("["+user.getUser_nickName()+"] : " + msg);
							client.bw.newLine();
							client.bw.flush();
						}
					}
					if(msg == null) {
						throw new Exception();
					}
					
					//모든 사람에게 메세지 보내기.
					
					
					boolean checkReady = ServerMethodImpliment.getInstance().checkReady(clientList);
					//레디 상태 받기. 
					
					//사용자에게 콘솔 입력 받아서 레디 상태 확인.
					if(msg.equals("/ready") && changeableReady == 1) {
						ready = 1;
					}
					
					if(connCount == 3 && startOnOff == 1 && checkReady) {
						for(Client client : clientList) {
							LocalDateTime startTime = LocalDateTime.now();
							client.bw.write("[게임을 시작하겠습니다.]");
							client.bw.write(question);
							//if문 다시 시작하지 않기 위해서 0으로 값바꿈.
							startOnOff = 0;
							//시작한 후에는 레디상태 변경 불가.
							changeableReady = 0;
							//게임을 시작 했으므로 게임끝낼수 있도록 설정. 
							endOnOff = 1;
							}
					}
					
					
					//정답을 맞출때 로직 
					if(msg.equals(quizList.get(msg))) {
						//이터레이터로 키의 다음 값 문제 받기 
						question = keys.next();
						//맵에서 퀴즈 문제 받아서 다음 정답 세팅.
						quizList.get(question);
						//맞춘 사람 점수 올리기.
						clientScore += 10;
						//게임 숫자 카운트.
						gameCount++;
						//문제 모두에게 보내기.
						for(Client client : clientList) {
							client.bw.write("["+user.getUser_nickName()+"] : " + "이 정답을 맞추었습니다.");
							client.bw.newLine();
							client.bw.flush();
							
							
							client.bw.write("다음문제는.. ");
							client.bw.flush();
							client.bw.write(question);
							client.bw.flush();
						}
					}
					
										
					
					//게임 종료 시점
					
					if(gameCount == 4 && endOnOff == 1) {
						//게임 종료시 게임 시작 시간 기록하기 위해 변수 선언.
						LocalDateTime endTime = LocalDateTime.now();
						//게임이 종료 되었으므로 다시 게임이 시작 될 수 있음.
						startOnOff = 1;
						//게임 수는 0으로 초기화
						gameCount = 0;
						//게임이 끝났으므로 다시 레디를 받기 위해 
						changeableReady = 1;
						//게임을 끝날 수 없는 상태로 들음. 
						endOnOff = 0;
						//ready 상태도 초기화.
						ready = 0;
						
						for(Client client : clientList) {
							client.bw.write("게임이 종료되었습니다.");
							client.bw.newLine();
							client.bw.write("게임을 다시 시작하시려면 /ready입력해주세요.");
							client.bw.newLine();
							client.bw.flush();
						}
						
						Start dbStart = new Start(user.getUser_num(), startTime, user.getUser_nickName());
						StartImp.getInstance().startInsert(dbStart);
						End dbEnd = new End(user.getUser_num(), endTime, user.getUser_nickName());
						EndImp.getInstance().endInsert(dbEnd);
						Score dbScore = new Score(user.getUser_num(), user.getUser_nickName(), clientScore);
						ScoreImp.getInstance().insert(dbScore);
						Ranking dbRaking = new Ranking(user.getUser_num(), user.getUser_nickName(), 3);
						
						//종료 되었으므로 게임 진행하며 저장된 사항 모두 저장
					}
					
					
					
					
					  
				}
			}catch(Exception e) {
				clientList.remove(this);
				try {
					connCount--;
					//접속 종료하면 유저 수 감소함. 
					
					for(Client client : clientList) {
						client.bw.write(user.getUser_nickName() + "님이 방을 나갔습니다."); 
						client.bw.newLine();
						client.bw.flush();
					}
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

