package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartImp implements StartDao{
	
	private static StartImp instance = new StartImp();
	
	private StartImp() {}
	
	public static StartImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private Start convertStart(ResultSet rs) throws SQLException {
		return new Start(rs.getInt("user_num"),
				rs.getTimestamp("access_time").toLocalDateTime(),
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
							rs.getTimestamp("end_time").toLocalDateTime(),
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

}
