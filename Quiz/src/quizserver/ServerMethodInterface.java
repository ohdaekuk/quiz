package quizserver;

import java.util.List;

public interface ServerMethodInterface {
	boolean checkReady(List<MultiChatServer.Client> clientList);
}
