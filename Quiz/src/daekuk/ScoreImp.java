package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
		
		String sql = "select * from score where final_rank = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, final_score);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Score> scoreList = new ArrayList<Score>();
				
				while (rs.next()) {
					
					scoreList.add(new Score(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_rank")));
					
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
}

