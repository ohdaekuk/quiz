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
import java.util.List;
import java.util.Map;

import quizio.QuizUtil;


public class MultiChatServer {
	private List<MultiChatServer.User> userList;
	private ServerSocket serverSocket;
	private Map<String, String> quizList = QuizUtil.getUtil().quizToServer();
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
		//문제를 불러오는 메서드 
		//로그를 텍스트에 쓰는 메서드 
		@Override
		public void run() {
			BufferedReader br = null;
			try {
				System.out.println("---서버 스레드 실행---");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//bw.write("아이디를 입력하세요");
				//bw.flush
				//this.id = br.reaLine();
				//
				//bw.write("비밀번호를 입력하세요");
				//bw.flush
				//this.password = br.readLine();
				//
				//데이터 베이스 메소드 이용해서 입력받은 비밀번호와 일치한지 확인하기
				//if 일치한다면, System.out.println("접속 성공");
				//else (일치하지 않는다면) 계속해서 아이디와 비밀번호 입력받기.
				
				//회원가입을 여기에서 할 것인지? 아니면 다른 메인 메소드를 가진 클래스에서 할 것인지?  
				
				bw.write("사용할 닉네임을 입력하세요. \n");
				bw.flush();
				
				this.name = br.readLine();
				for(User user : userList) {
					if(this != user) {
						user.bw.write(name+"님이 접속하였습니다.");
						user.bw.newLine();
						user.bw.flush();
					}
				}
				//사용자가 입력했을 때 메세지를 보내기
				String msg = null;
				while(true) {
					//연결되어 있는 사람의 메세지를 읽고
					msg = br.readLine();
					if(msg == null) {
						throw new Exception();
					}
					//모든 사람에게 뿌려준다.
					for(User user :userList) {
						if(this != user) {
							user.bw.write("["+name+"] : " + msg);
							user.bw.newLine();
							user.bw.flush();
						}
					}
					//받은 메세지가 지금 낸 문제와 동일 하다면
					
					//정답 처리를 한다.
					
					//정답처리{ Map의 key = value인지 확인, userList중 정답을 맞춘 유저 에게 점수를 주고 User의 score 에 score += 점수 형태로 저장, 
					//정답입니다 메세지 보내기 }
					
					//다음 문제로 넘어간다.
					
					//다음 문제를 고르는 메서드 로직 구현.
					
					//마무리에서 점수를 데이터 베이스에 등록, 게임 시작 시작시간 마무리 시간 등 기록하기.  
				}
			}catch(Exception e) {
				userList.remove(this);
				try {
					for(User user : userList) {
						user.bw.write(name + "님이 방을 나갔습니다.");
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

