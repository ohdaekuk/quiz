package chatserver;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127,0.0.1", 7777);
			System.out.println("클라이언트 실행");
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientSender clientSender = new ClientSender(socket);
			clientReceiver.start();
			clientSender.start();
			clientSender.join();
			System.out.println("프로그램이 종료됩니다.");
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
