package chatserver;

import java.io.IOException;

public class ChatMain {
	public static void main(String[] args) {
		try{
			MultiChatServer server = new MultiChatServer(7777);
			server.runServer();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
