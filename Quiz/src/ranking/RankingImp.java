package ranking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daekuk.QuizConn;

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

	@Override
	public Ranking start() throws ClassNotFoundException, SQLException {
		
		try {
			
			RankingDao dao = RankingImp.getInstance();
			
			Scanner scan = new Scanner(System.in);
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 랭킹 저장하기 2. 랭킹 조회하기 3. 랭킹 삭제하기 4. 랭킹 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("랭킹 저장하기");
					
					while(true) {
						
						System.out.println("최종 랭킹을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						System.out.println("최종 랭킹을 입력해주세요.");
						String finalRank = scan.nextLine();
						
						Ranking ranking = new Ranking(0, nickName, Integer.parseInt(finalRank));
						
						dao.insert(ranking);
						
						System.out.println("최종 점수가 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("랭킹 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. user_nickName으로 조회하기 3. 최종 랭킹으로 조회하기 4. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num 넣어서 조회하기
							
							List<Ranking> rankingList1 = dao.findByuserNum();
							
							System.out.println(rankingList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// user_nickName으로 조회하기
							List<Ranking> rankingList2 = dao.findByuserNickName();
							
							System.out.println(rankingList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 최종랭킹으로 조회하기
							List<Ranking> rankingList3 = dao.findByfinal_rank();
							
							System.out.println(rankingList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 전체 랭크 조회하기
							List<Ranking> rankingList4 = dao.rankingFindAll();
							
							System.out.println(rankingList4);
							
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
	public Ranking auto() throws ClassNotFoundException, SQLException {
		
		String sql = "alter table ranking auto_increment = 0";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.execute();
			
		}
		
		return null;
	}
}

