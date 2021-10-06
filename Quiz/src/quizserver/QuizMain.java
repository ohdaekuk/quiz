package quizserver;

import java.io.IOException;

public class QuizMain {
	public static void main(String[] args) {
		try{
			MultiChatServer server = new MultiChatServer(7777);
			server.runServer();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
