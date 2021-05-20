package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	public static void main(String[] args) {
		Connection conn;
		try {
			// 1. JDBC driver loading
			Class.forName("driver.MyDriver");
			
			// 2. connect
			String url = "jdbc:mydb://127.0.0.1:2202/webdb";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. success connection
			System.out.println("ok:"+conn);
			
		} catch (ClassNotFoundException e) {
			// 1. 관련
			System.out.println("fail to load driver:"+e);
		} catch (SQLException e) {
			// 2. 관련 : linux 꺼져있을 때 등등 connection안될 때 
			System.out.println("error :"+e);
		}

	}

}
