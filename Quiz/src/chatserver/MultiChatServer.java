package chatserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MultiChatServer {
	private ServerSocket serverSocket;
	private List<MultiChatServer.User> userList;
	
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
		
		public User(Socket socket) throws IOException{
			this.socket = socket;
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		
		@Override
		public void run() {
			BufferedReader br = null;
			try {
				System.out.println("---서버 스레드 실행---");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw.write("사용할 이름을 입력하세요. \n");
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
						break;
					}
					//모든 사람에게 뿌려준다.
					for(User user :userList) {
						if(this != user) {
							user.bw.write("["+name+"] : " + msg);
							user.bw.newLine();
							user.bw.flush();
						}
					}
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

