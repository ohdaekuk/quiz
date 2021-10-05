package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RankingImp implements RankingDao{
	
	private static RankingImp instance = new RankingImp();
	
	private RankingImp() {}
	
	public static RankingImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private Ranking convertRanking(ResultSet rs) throws SQLException {
		return new Ranking(rs.getInt("user_num"),
				rs.getString("user_nickName"),
				rs.getInt("final_rank"));
	}
	
	@Override
	public int insert(Ranking ranking) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into Ranking value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, ranking.getUser_nickName());
			pst.setInt(2, ranking.getFinal_rank());
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public List<Ranking> findByuserNum() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from ranking where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Ranking> rankingList = new ArrayList<Ranking>();
				
				while (rs.next()) {
					
					rankingList.add(new Ranking(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_rank")));
				}
				return rankingList;
			}
		}
	}
	

	@Override
	public List<Ranking> findByuserNickName() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_nickName을 입력해주세요.");
		String user_nickName = scan.next();
		
		String sql = "select * from ranking where user_nickName = ?";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_nickName);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Ranking> rankingList = new ArrayList<Ranking>();
				
				while (rs.next()) {
					
					rankingList.add(new Ranking(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_rank")));
					
				}
				return rankingList; 
			}
			
		}
	}

	@Override
	public List<Ranking> rankingFindAll() throws ClassNotFoundException, SQLException { 
		
		String sql = "select * from ranking";
		
		try(Connection conn = QuizConn.getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			List<Ranking> rankingList = new ArrayList<Ranking>();
			
			while (rs.next()) {
				rankingList.add(convertRanking(rs));
			}
			return rankingList;
		}
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from ranking where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}
	
	@Override
	public List<Ranking> findByfinal_rank() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 final_rank을 입력해주세요.");
		int final_rank = scan.nextInt();
		
		String sql = "select * from ranking where final_rank = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, final_rank);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Ranking> rankingList = new ArrayList<Ranking>();
				
				while (rs.next()) {
					
					rankingList.add(new Ranking(rs.getInt("user_num"),
							rs.getString("user_nickName"),
							rs.getInt("final_rank")));
					
				}
				return rankingList; 
			}
			
		}
	}

	@Override
	public int update() throws ClassNotFoundException, SQLException {
		
		System.out.println("변경 할 user_num을 입력하세요.");
		int userNum = scan.nextInt();
		System.out.println("변경 할 final_rank값을 입력하세요.");
		int finalRank = scan.nextInt();
		
		String sql = "update ranking set final_rank = ? where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, userNum);
			pst.setInt(2, finalRank);
			
			return pst.executeUpdate();
			
		}
	}
}

