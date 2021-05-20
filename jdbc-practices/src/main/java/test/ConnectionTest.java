package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. JDBC driver loading
			// 		loading 후 Driver Class가 DriverManager만듦 - 우리가 new로 drivermanager만들 필요없음
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. DriverManager - get connection
			String url = "jdbc:mysql://192.168.254.40:3307/webdb?chareset=utf8"; //linux server ip
			conn	   = DriverManager.getConnection(url,"webdb","webdb");
			conn.createStatement();
			
			// 3. success connection 
			System.out.println("ok:"+conn);
		} catch (ClassNotFoundException e) {
			// 1. 관련 : pom에 dependency로 jdbc driver 안해놓음
			System.out.println("fail driver loading:"+e);
		} catch (SQLException e) {
			// 2. 관련 : linux 꺼져있을 때 등등 connection안될 때 
			System.out.println("error :"+e);
		} finally {
			try {
				if(conn != null) {
					conn.close();					
				}
			} catch (SQLException e) {
				System.out.println("connection close error:"+e);
			}
		}
		
	}

}
