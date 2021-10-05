package quizio;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class QuizUtil implements InputQuiz, OutputQuiz{
	
	private static QuizUtil quizUtil = new QuizUtil();
	
	public static QuizUtil getManager() {
		return quizUtil;
	}

	@Override
	public String mkQuiz(String quiz, String answer) throws IOException {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("Quiz.txt", true));){
			//버퍼라이터 불러옴.
			
			bw.write(quiz+"%");
			//질문과 정답 구분자 "%"
			bw.write(answer);
			bw.newLine();
			
			return quiz+"%"+"\n";
			//어떻게 적었는 지 return
		}
	
	}

	@Override
	public String delQuiz(String quiz) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("Quiz.txt", false));
				//append 하지 않고 처음부터 다 쓰기.
				BufferedReader br = new BufferedReader(new FileReader("Quiz.txt"));){
			
			StringBuffer sb = new StringBuffer();
			String quizLine= "";
			String deletedLine = "";
			while((quizLine = br.readLine()) != null) {
				if (!quizLine.contains(quiz+"%")) {
					sb.append(quizLine);
					sb.append("\n");
				}else {
					deletedLine = quizLine;
				}
			}
			//읽어서 StringBuffer 객체에 저장.
			
			char[] buff = new char[sb.length()];
			sb.getChars(0, sb.length()-1, buff, 0);
			//BufferWriter에 파라미터로 StringBuffer 못 받으므로 char로 변경해줌.
			
			
			bw.write(buff);
			
			//한번에 다 쓰기.
			
			return deletedLine;
			//삭제한 String 반환.
		}
	}

	@Override
	public String toUserQuiz(String quiz) {
		return null;
	}

	@Override
	public String quizToServer(String quiz) {
		return null;
	}

}
