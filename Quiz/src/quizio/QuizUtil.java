package quizio;

public class QuizUtil implements InputQuiz, OutputQuiz{
	
	private static QuizUtil quizUtil = new QuizUtil();
	
	public static QuizUtil getManager() {
		return quizUtil;
	}

}
