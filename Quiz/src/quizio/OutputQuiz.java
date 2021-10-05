package quizio;

import java.io.IOException;

public interface OutputQuiz {
	String mkQuiz(String quiz, String answer) throws IOException;
	
	String delQuiz(String quiz) throws IOException;
	
	String toUserQuiz(String quiz);

}
