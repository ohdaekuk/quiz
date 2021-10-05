package quizio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InputQuiz {
	Map<String, String> quizToServer() throws FileNotFoundException, IOException;
	
	List<String> loadQuiz() throws FileNotFoundException, IOException;
}
