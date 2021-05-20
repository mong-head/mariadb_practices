package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
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
			String sql = "select emp_no,date_format(birth_date,'%Y-%m-%d'),first_name from employees where first_name like 'pat%'";
			rs = stmt.executeQuery(sql);
			
			// 5. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				// type 잘 맞춰서 적어야함
				long empNo = rs.getLong(1);
				String birthDate = rs.getString(2); //date는 string으로 받으면 됨
				String firstname = rs.getString(3);
				
				System.out.printf("emp num : %ld, birth : %s, first name : %s\n");
			}
			
		} catch (ClassNotFoundException e) {
			// 1. 관련 : pom에 dependency로 jdbc driver 안해놓음
			System.out.println("fail driver loading:"+e);
		} catch (SQLException e) {
			// 2. 관련 : linux 꺼져있을 때 등등 connection안될 때 
			System.out.println("error :"+e);
		} finally {
			//clean-up; 자원정리는 만들어진 순서 거꾸로 하기
			try {
				if(rs != null) {
					rs.close();
				}
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
		
	}

}
