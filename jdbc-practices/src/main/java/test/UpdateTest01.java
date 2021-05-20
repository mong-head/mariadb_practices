package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {

	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(4L);
		vo.setName("전략기획팀");
		Boolean result = updateDepartment(vo);
		if(result) {
			System.out.println("성공적으로 update");
		}
	}
	public static Boolean updateDepartment(DeptVo vo){
		Connection conn = null;
		Statement stmt = null;
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
			stmt = conn.createStatement();
			
			// 4. SQL실행
			String sql = "update dept set name = '"+ vo.getName() + "' where no = " + vo.getNo();
			int count = stmt.executeUpdate(sql);
			
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
				if(stmt != null) {
					stmt.close();
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
