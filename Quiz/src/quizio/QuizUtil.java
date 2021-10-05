package quizio;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class QuizUtil implements InputQuiz, OutputQuiz{
	
	private static QuizUtil quizUtil = new QuizUtil();
	
	public static QuizUtil getUtil() {
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
	public String delQuiz(String quiz) throws IOException, NullPointerException {

		File file = new File("Quiz.txt");
		List<String> quizList =  new ArrayList<String>();
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file));){
			String line = "";
			String deleted = "";
			
			while((line = br.readLine())!=null) {
				if(!line.contains(quiz)) {
					quizList.add(line);
				}else {
					deleted = line;
				}
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
				for(String s : quizList) {
					bw.write(s+"\n");
				}
			}
			
			
			System.out.println(quizList.toString());
			
			
			return deleted;
		}
	}

	@Override
	public String toUserQuiz() throws FileNotFoundException, IOException {
		quizToServer();
		return null;
	}

	@Override
	public Map quizToServer() throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("Quiz.txt"))) {
			
			String temp = "";
			StringBuffer sb = new StringBuffer();
			Map<String, String> qna = new HashMap<String, String>();
			//문제와 정답을 넣기 위한 해쉬 맵.
			while((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			char[] buff = new char[sb.length()];
			sb.getChars(0, sb.length(), buff, 0);
			String buffStr = new String(buff);
			String[] stringArr = buffStr.split("\n");
			
			//문제들을 한 문장 별로 나눔. 
			for(int i = 0; i < stringArr.length; i++) {
				String [] keyValue = stringArr[i].split("%");
				qna.put(keyValue[0], keyValue[1]);
			}
			//해쉬맵에 문제와 정답을 문제 = key, 정답 = value 형태로 넣음.
			
			
			
			
			return null;
		}
	}

	@Override
	public String saveLog() {
		
		return null;
	}

	@Override
	public List<String> loadQuiz() throws FileNotFoundException, IOException {
		File file = new File("Quiz.txt");
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			List<String> quizList =  new ArrayList<String>();
			String line = "";
			while((line = br.readLine())!=null) {
				System.out.println(line);
				quizList.add(line);
			}
			return quizList;
		}
	}
//
}
