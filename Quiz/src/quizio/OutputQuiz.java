package quizio;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OutputQuiz {
	
	String mkQuiz(String quiz, String answer) throws IOException;
	
	String delQuiz(String quiz) throws IOException;
	
	String toUserQuiz() throws FileNotFoundException, IOException;
	
	void saveLog(String chat) throws IOException;

}
