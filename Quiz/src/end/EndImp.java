package end;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import daekuk.QuizConn;

public class EndImp implements EndDao{
	
	private static EndImp instance = new EndImp();
	
	private EndImp() {}
	
	public static EndImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private End convertEnd(ResultSet rs) throws SQLException {
		return new End(rs.getInt("user_num"),
				rs.getTimestamp("end_time").toLocalDateTime(),
				rs.getString("user_nickName"));
	}
	
	@Override
	public int endInsert(End end) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into end value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, end.getEnd_time().toString());
			pst.setString(2, end.getUser_nickName());
			
			return pst.executeUpdate();
		}
	}

	@Override
	public List<End> findbyUserNum() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from end where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<End> endList = new ArrayList<End>();
				
				while (rs.next()) {
					
					endList.add(new End(rs.getInt("user_num"),
							rs.getTimestamp("end_time").toLocalDateTime(),
							rs.getString("user_nickName")));
					
				}
				return endList; 
			}
		}
	}

	@Override
	public List<End> endFindAll() throws ClassNotFoundException, SQLException {
		
		String sql = "select * from end";
		
		try(Connection conn = QuizConn.getConn();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			
			List<End> endList = new ArrayList<End>();
			
			while (rs.next()) {
				endList.add(convertEnd(rs));
			}
			return endList;
		}
	}
	
	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from end where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public End start() throws ClassNotFoundException, SQLException {
		try {
			Scanner scan = new Scanner(System.in);
			
			EndDao dao = EndImp.getInstance();
			
			String select = "";
			
			while (!select.equals("4")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 종료시간 저장하기 2. 종료시간 조회하기 3. 종료시간 삭제하기 4. 종료하기");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("종료시간 저장하기");
					
					while(true) {
						
						System.out.println("종료시간을 저장하기 위해 닉네임을 입력해주세요.");
						String nickName = scan.nextLine();
						End end = new End(0, LocalDateTime.now(), nickName);
						
						dao.endInsert(end);
						
						System.out.println("종료시간이 저장되었습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("종료시간 조회하기");
					
					while(true) {
						
						// user_num 넣어서 조회하기
//						List<End> endfindbyUserNumList = dao.findbyUserNum();
//						
//						System.out.println(endfindbyUserNumList);
						
						// 전체 시간 조회하기
						List<End> endList = dao.endFindAll();
						
						System.out.println(endList);
						
						System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
						
						select = scan.nextLine();
						
						break;
						
					}
				}
				if(select.equals("3")) {
					System.out.println("종료시간 삭제하기");
					
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
	public End auto() throws ClassNotFoundException, SQLException {
		
		String sql = "alter table end auto_increment = 0";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.execute();
			
		}
		
		return null;
	}
}



