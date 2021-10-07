package start;

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

public class StartImp implements StartDao{
	
	private static StartImp instance = new StartImp();
	
	private StartImp() {}
	
	public static StartImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private Start convertStart(ResultSet rs) throws SQLException {
		return new Start(rs.getInt("user_num"),
				rs.getTimestamp("start_time").toLocalDateTime(),
				rs.getString("user_nickName"));
	}
	
	

	@Override
	public int startInsert(Start start) throws SQLException, ClassNotFoundException {
		
		String sql = "insert into start value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, start.getStart_time().toString());
			pst.setString(2, start.getUser_nickName());
			
			return pst.executeUpdate();
		}
	}

	@Override
	public List<Start> findbyUserNum() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from start where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Start> startList = new ArrayList<Start>();
				
				while (rs.next()) {
					
					startList.add(new Start(rs.getInt("user_num"),
							rs.getTimestamp("start_time").toLocalDateTime(),
							rs.getString("user_nickName")));
					
				}
				return startList; 
			}
		}
	}

	@Override
	public List<Start> startFindAll() throws ClassNotFoundException, SQLException {
		
		String sql = "select * from start";
		
		try(Connection conn = QuizConn.getConn();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			
			List<Start> startList = new ArrayList<Start>();
			
			while (rs.next()) {
				startList.add(convertStart(rs));
			}
			return startList;
		}
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException { 
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from start where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public Start start() throws ClassNotFoundException, SQLException {
		
		try {
			
			Scanner scan = new Scanner(System.in);
			
			StartDao dao = StartImp.getInstance();
			
			String select = "";
			
			while (!select.equals("4")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 게임 시작하기 2. 시작시간 조회하기 3. 시작기록 삭제하기 4. 종료하기");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("게임 시작하기");
					
					while(true) {
						
						System.out.println("시작시간을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						Start start = new Start(0, LocalDateTime.now(), nickName);
						
						dao.startInsert(start);
						
						System.out.println("시작시간이 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("시작시간 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
//							 user_num 넣어서 조회하기
							
							List<Start> startfindbyUserNumList = dao.findbyUserNum();
							
							System.out.println(startfindbyUserNumList);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// 전체 시간 조회하기
							List<Start> startList = dao.startFindAll();
							
							System.out.println(startList);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						}
						break;
					}
				}
				if(select.equals("3")) {
					System.out.println("시작시간 삭제하기");
					
					while(true) {
						
						int delete = dao.delete();
						
						System.out.println(delete + "row가 삭제되었습니다.");
						
						dao.auto();
						
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
	public Start auto() throws ClassNotFoundException, SQLException {
		
		String sql = "alter table start auto_increment = 0";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.execute();
			
		}
		
		return null;
	}
}