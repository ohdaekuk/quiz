package quizserver;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("121.131.36.216", 7777);
			System.out.println("[퀴즈 프로그램에 접속하였습니다.]");
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientSender clientSender = new ClientSender(socket);
			clientReceiver.start();
			clientSender.start();
			clientSender.join();
			System.out.println("[프로그램이 종료됩니다.]");
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
