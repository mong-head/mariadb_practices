package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest02 {

	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(4L);
		vo.setName("기획");
		Boolean result = updateDepartment(vo);
		if(result) {
			System.out.println("성공적으로 update");
		}
	}
	public static Boolean updateDepartment(DeptVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			
			// 3. Statement 생성 - query 실행위함
			String sql = "update dept set name = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1,vo.getName());
			pstmt.setLong(2,vo.getNo());
			
			// 5. SQL실행
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
