package user;

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


public class UserImp implements UserDao{
	
	private static UserImp instance = new UserImp();
	
	private UserImp() {}
	
	public static UserImp getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	
	private User convertUser(ResultSet rs) throws SQLException {
		return new User(rs.getInt("user_num"),
				rs.getString("id"),
				rs.getString("password"),
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
		
		String sql = "select * from user where id = ?";
		
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
	public int update() throws ClassNotFoundException, SQLException {
		
		String sql = "update user set id = ?, password = ?, age = ?, gender = ?, user_nickName = ? where user_num = ?";
		
		System.out.println("수정 할 user_num을 입력해주세요.");
		int user_num = scan.nextInt();
		
		System.out.println("수정 할 user_id의 값을 입력해주세요.");
		String user_id = scan.next();
		
		System.out.println("수정 할 user_password의 값을 입력해주세요.");
		String user_password = scan.next();
		
		System.out.println("수정 할 age의 값을 입력해주세요.");
		int age = scan.nextInt();
		
		System.out.println("수정 할 gender의 값을 입력해주세요.");
		String gender = scan.next();
		
		System.out.println("수정 할 user_nickName의 값을 입력해주세요.");
		String user_nickName = scan.next();
		
		try(Connection conn = QuizConn.getConn();
			PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.setString(1, user_id);
			pst.setString(2, user_password);
			pst.setInt(3, age);
			pst.setString(4, gender);
			pst.setString(5, user_nickName);
			pst.setInt(6, user_num);
			
			
			return pst.executeUpdate();
		}
	}

	@Override
	public User start() throws ClassNotFoundException, SQLException {
		
		try {
			
			UserDao dao = UserImp.getInstance();
			
			Scanner scan = new Scanner(System.in);
			
			String select = "";
			
			while (!select.equals("5")) {	
				
				System.out.println("어떤 작업을 하시겠습니까?");
				System.out.println("1. 회원가입 2. 정보 조회하기 3. 정보 삭제하기 4. 정보 수정하기 5. 종료하기 ");
				select = scan.nextLine();
				
				if(select.equals("1")) {
					System.out.println("회원가입");
					
					while(true) {
						
						System.out.println("아이디를 입력해주세요.");
						String id = scan.next();
						
						System.out.println("비밀번호를 입력해주세요.");
						String password = scan.next();
						
						System.out.println("나이를 입력해주세요.");
						int age = scan.nextInt();
						
						System.out.println("성별을 입력해주세요.");
						String gender = scan.next();
						
						System.out.println("닉네임을 입력해주세요.");
						String nickName = scan.next();
						
						User user = new User(0, id, password, age, gender, nickName);
						
						dao.insert(user);
						
						System.out.println("회원가입을 완료했습니다.");
						
						break;
						
					}
				}
				
				if(select.equals("2")) {
					System.out.println("정보 조회하기");
					
					while(true) {
						System.out.println("1. user_num으로 조회하기 2. id로 조회하기 3. 나이로 조회하기 4. 성별로 조회하기 5. 닉네임으로 조회하기 6. 전체 조회하기");
						select = scan.nextLine();
						if(select.equals("1")) {
							
							// user_num으로 조회하기
							
							List<User> userList1 = dao.userFindByUser_num();
							
							System.out.println(userList1);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("2")) {
							
							// id로 조회하기
							List<User> userList2 = dao.userFindByUser_Id();
							
							System.out.println(userList2);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("3")) {
							
							// 나이로 조회하기
							List<User> userList3 = dao.userFindByAge();
							
							System.out.println(userList3);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("4")) {
							
							// 성별로 조회하기
							List<User> userList4 = dao.userFindByGender();
							
							System.out.println(userList4);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("5")) {
							
							// 닉네임으로 조회하기
							List<User> userList5 = dao.userFindByUser_nickName();
							
							System.out.println(userList5);
							
							System.out.println("조회를 완료하셨으면 엔터를 눌러주세요.");
							
							select = scan.nextLine();
							
						} if(select.equals("6")) {
							
							// 전체 정보 조회하기
							List<User> userList6 = dao.userFindAll();
							
							System.out.println(userList6);
							
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
	public User auto() throws ClassNotFoundException, SQLException {
		
		String sql = "alter table user auto_increment = 0";
		
		try(Connection conn = QuizConn.getConn();
				PreparedStatement pst = conn.prepareStatement(sql)){
			
			pst.execute();
			
		}
		
		return null;
	}
}

