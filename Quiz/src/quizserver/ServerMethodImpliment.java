package quizserver;

import java.util.List;

import quizserver.MultiChatServer.Client;
import user.UserImp;

public class ServerMethodImpliment implements ServerMethodInterface {
	
	private static ServerMethodImpliment instance = new ServerMethodImpliment();
	
	private ServerMethodImpliment() {};
	
	public static ServerMethodImpliment getInstance() {
		return instance;
	}
	
	@Override
	public boolean checkReady(List<MultiChatServer.Client> clientList) {
		int readyAll = 1;
		
		for(Client c : clientList) {
			readyAll *= c.ready;
		}
		if(readyAll == 1) {
			return true;
		}else {
			return false;
		}
	}
	
}
