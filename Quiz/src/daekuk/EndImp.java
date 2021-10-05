package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EndImp implements EndDao{
	
	private static EndImp instance = new EndImp();
	
	private EndImp() {}
	
	public static EndImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private End convertEnd(ResultSet rs) throws SQLException {
		return new End(rs.getInt("user_num"),
				rs.getTimestamp("access_time").toLocalDateTime(),
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
}
