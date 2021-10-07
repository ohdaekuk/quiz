package score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import user.QuizConn;


public class ScoreImp implements ScoreDao{
	
	private static ScoreImp instance = new ScoreImp();
	
	private ScoreImp() {}
	
	public static ScoreImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private Score convertScore(ResultSet rs) throws SQLException {
		return new Score(rs.getInt("user_num"),
				rs.getString("user_nickName"),
				rs.getInt("final_score"));
	}

	@Override
	public int insert(Score score) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into Score value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, score.getUser_nickName());
			pst.setInt(2, score.getFinal_score());
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public List<Score> findByuserNum() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from score where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Score> scoreList = new ArrayList<Score>();
				
				while (rs.next()) {
					
					scoreList.add(new Score(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_score")));
				}
				return scoreList;
			}
		}
	}

	@Override
	public List<Score> findByuserNickName() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_nickName을 입력해주세요.");
		String user_nickName = scan.next();
		
		String sql = "select * from score where user_nickName = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_nickName);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Score> scoreList = new ArrayList<Score>();
				
				while (rs.next()) {
					
					scoreList.add(new Score(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_score")));
				}
				return scoreList;
			}
			
		}
	}

	@Override
	public List<Score> scoreFindByFinal_score() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 final_score을 입력해주세요.");
		int final_score = scan.nextInt();
		
		String sql = "select * from score where final_score = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, final_score);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Score> scoreList = new ArrayList<Score>();
				
				while (rs.next()) {
					
					scoreList.add(new Score(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_score")));
					
				}
				return scoreList; 
			}
		}
	}

	@Override
	public List<Score> scoreFindAll() throws ClassNotFoundException, SQLException {
		
		String sql = "select * from score";
		
		try(Connection conn = QuizConn.getConn();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			
			List<Score> scoreList = new ArrayList<Score>();
			
			while (rs.next()) {
				scoreList.add(convertScore(rs));
			}
			return scoreList;
		}
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from score where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public int update() throws ClassNotFoundException, SQLException {
		
		System.out.println("변경 할 user_num을 입력하세요.");
		int userNum = scan.nextInt();
		System.out.println("변경 할 final_score값을 입력하세요.");
		int finalScore = scan.nextInt();
		
		String sql = "update score set final_score = ? where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, userNum);
			pst.setInt(2, finalScore);
			
			return pst.executeUpdate();
			
		}
	}
	
	@Override
	public Score start() throws ClassNotFoundException, SQLException {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			ScoreDao dao = ScoreImp.getInstance();
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 점수 저장하기 2. 점수 조회하기 3. 점수 삭제하기 4. 점수 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("점수 저장하기");
					
					while(true) {
						
						System.out.println("최종 점수를 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						System.out.println("최종 점수를 입력해주세요.");
						String finalScore = scan.nextLine();
						
						Score score = new Score(0, nickName, Integer.parseInt(finalScore));
						
						dao.insert(score);
						
						System.out.println("최종 점수가 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("점수 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 점수로 조회하기 4. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num 넣어서 조회하기
							
							List<Score> scoreList1 = dao.findByuserNum();
							
							System.out.println(scoreList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// user_nickName으로 조회하기
							List<Score> scoreList2 = dao.findByuserNickName();
							
							System.out.println(scoreList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 최종점수로 조회하기
							List<Score> scoreList3 = dao.scoreFindByFinal_score();
							
							System.out.println(scoreList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 전체 점수 조회하기
							List<Score> scoreList4 = dao.scoreFindAll();
							
							System.out.println(scoreList4);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						}
						break;
					}
				}
				if(select.equals("3")) {
					System.out.println("점수 삭제하기");
					
					while(true) {
						
						int delete = dao.delete();
						
						System.out.println(delete + "row가 삭제되었습니다.");
						
						dao.auto();
						
						break;
					}
				}if(select.equals("4")) {
					System.out.println("점수 수정하기");
					
					while(true) {
						
						dao.update();
						
						System.out.println("수정되었습니다.");
						break;
					}
				}
			}
			System.out.println("종료되었습니다.");
			
		}finally {
			
		}
		return null;
	}

	@Override
	public Score auto() throws ClassNotFoundException, SQLException {
		
		String sql = "alter table score auto_increment = 0";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.execute();
			
		}
		
		return null;
	}
}

