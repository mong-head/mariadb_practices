package test;

// 완결되지 않은 sql -> prepare() - binding : sql + value

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest02 {

	public static void main(String[] args) {
		insertDepartment("영업");
		insertDepartment("총무");
	}
	public static boolean insertDepartment(String name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		boolean result = false;
		try {
			// 1. JDBC driver loading
			// 		loading 후 Driver Class가 DriverManager만듦 - 우리가 new로 drivermanager만들 필요없음
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. DriverManager - get connection
			String url = "jdbc:mysql://192.168.254.40:3307/employees?chareset=utf8"; //linux server ip
			conn	   = DriverManager.getConnection(url,"hr","hr");
			conn.createStatement();
			System.out.println("success connection");
			
			// 3. prepare Statement 
			String sql = "insert into dept values (null , ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL binging
			pstmt.setString(1, name);
			
			// 5. SQL execute
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			// 1. 관련 : pom에 dependency로 jdbc driver 안해놓음
			System.out.println("fail driver loading:"+e);
		} catch (SQLException e) {
			// 2. 관련 : linux 꺼져있을 때 등등 connection안될 때 
			System.out.println("error :"+e);
		} finally {
			//clean-up; 자원정리는 만들어진 순서 거꾸로 하기
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();					
				}
				
			} catch (SQLException e) {
				System.out.println("connection close error:"+e);
			}
		}
		return result;
	}
}
