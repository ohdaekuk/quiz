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


public class UserImp implements UserDao{
	
	private static UserImp instance = new UserImp();
	
	private UserImp() {}
	
	public static UserImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private User convertUser(ResultSet rs) throws SQLException {
		return new User(rs.getInt("user_num"),
				rs.getString("user_id"),
				rs.getString("user_password"),
				rs.getInt("age"),
				rs.getString("gender"),
				rs.getString("user_nickName"));
	}

	@Override
	public int insert(User user) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into user value(0, ?, ?, ?, ?, ?)";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user.getUser_Id());
			pst.setString(2, user.getUser_Password());
			pst.setInt(3, user.getAge());
			pst.setString(4, user.getGender());
			pst.setString(5, user.getUser_nickName());
			
			return pst.executeUpdate();
			
		}
	}

	@Override
	public List<User> userFindByUser_num() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		String sql = "select * from user where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<User> userList = new ArrayList<User>();
				
				while (rs.next()) {
					
					userList.add(convertUser(rs));
					
				}
				return userList; 
			}
		}
	}

	@Override
	public List<User> userFindByUser_Id() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_id을 입력해주세요.");
		String user_id = scan.next();
		
		String sql = "select * from user where user_id = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_id);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<User> userList = new ArrayList<User>();
				
				while (rs.next()) {
					
					userList.add(convertUser(rs));
					
				}
				return userList; 
			}
		}
	}
	
	@Override
	public List<User> userFindByUser_nickName() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 user_nickName을 입력해주세요.");
		String user_nickName = scan.next();
		
		String sql = "select * from user where user_nickName = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_nickName);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<User> userList = new ArrayList<User>();
				
				while (rs.next()) {
					
					userList.add(convertUser(rs));
					
				}
				return userList; 
			}
		}
	}
	
	@Override
	public List<User> userFindByAge() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 age을 입력해주세요.");
		int age = scan.nextInt();
		
		String sql = "select * from user where age = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, age);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<User> userList = new ArrayList<User>();
				
				while (rs.next()) {
					
					userList.add(convertUser(rs));
					
				}
				return userList; 
			}
		}
	}
	
	@Override
	public List<User> userFindByGender() throws ClassNotFoundException, SQLException {
		
		System.out.println("조회 할 gender을 입력해주세요.");
		String gender = scan.next();
		
		String sql = "select * from user where gender = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, gender);
			
			try(ResultSet rs = pst.executeQuery()){
				
				List<User> userList = new ArrayList<User>();
				
				while (rs.next()) {
					
					userList.add(convertUser(rs));
					
				}
				return userList; 
			}
		}
	}

	@Override
	public List<User> userFindAll() throws ClassNotFoundException, SQLException {
		
		String sql = "select * from user"; 
		
		try(Connection conn = QuizConn.getConn();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			
			List<User> userList = new ArrayList<User>();
			
			while (rs.next()) {
				
				userList.add(convertUser(rs));
			}
			return userList; 
		}
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		
		System.out.println("삭제할 user_num을 입력해주세요.");
		
		int user_num = scan.nextInt();
		
		String sql = "delete from user where user_num = ?";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setInt(1, user_num);
			
			return pst.executeUpdate();
			
		}
	}
	

	@Override
	public int update() {
		
		return 0;
		
	}
	
}