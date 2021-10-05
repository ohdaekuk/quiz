package daekuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Access_logImp implements Access_logDao{
	
	private static Access_logImp instance = new Access_logImp();
	
	private Access_logImp() {}
	
	public static Access_logImp getInstance() {
		return instance;
	}
	
	private Access_log convertAccess(ResultSet rs) throws SQLException {
		return new Access_log(rs.getInt("user_num"),
				rs.getTime("access_time").toLocalTime(),
				rs.getString("user_nickName"));
	}
	
	Scanner scan = new Scanner(System.in);

	@Override
	public int accessInsert(Access_log access) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into access_log value(0, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, access.getAccess_time().toString());
			pst.setString(2, access.getUser_nickName());
			
			return pst.executeUpdate();
			
		}
		
	}

	@Override
	public List<Access_log> findbyUserNum() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from access_log where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<Access_log> accessList = new ArrayList<Access_log>();
				
				while (rs.next()) {
					
					accessList.add(new Access_log(rs.getInt("user_num"),
							rs.getTime("access_time").toLocalTime(),
							rs.getString("user_nickName")));
					
				}
				return accessList; 
			}
			
		}
		
	}

	@Override
	public List<Access_log> Access_logFindAll() throws ClassNotFoundException, SQLException {
		
		String sql = "select * from access_log";
		
		try(Connection conn = QuizConn.getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			
			List<Access_log> accessList = new ArrayList<Access_log>();
			
			while (rs.next()) {
				accessList.add(convertAccess(rs));
			}
			return accessList;
		}
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from access_log where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}
}
