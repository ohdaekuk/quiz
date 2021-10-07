package quizserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;


public class ClientReceiver extends Thread{
	private Socket socket;
	public ClientReceiver(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try(BufferedReader br = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()))){
			
			String msg = br.readLine(); 
			
			while(true) {
				System.out.println(msg);
			}
		} catch (IOException e) {
			System.out.println("서버와의 연결을 종료합니다.");
		}
	}
}
