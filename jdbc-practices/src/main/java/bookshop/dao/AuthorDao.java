package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;
import hr.EmployeeVo;

public class AuthorDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC driver loading
			// 		loading 후 Driver Class가 DriverManager만듦 - 우리가 new로 drivermanager만들 필요없음
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. DriverManager - get connection
			String url = "jdbc:mysql://192.168.254.40:3307/webdb?chareset=utf8"; //linux server ip
			conn	   = DriverManager.getConnection(url,"webdb","webdb");
			conn.createStatement();
			System.out.println("success connection");
		} catch (ClassNotFoundException e) {
			System.out.println("fail to load driver:"+e);
		}
		return conn;	

	}

	public boolean insert(AuthorVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			
			// 3. prepare Statement 
			String sql = "insert into author values(null,?);";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL binging
			pstmt.setString(1, vo.getName()); // parameter mapping
			
			// 5. SQL execute
			int count = pstmt.executeUpdate();
			result = count == 1;
			
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

	public List<AuthorVo> findAll() {
		List<AuthorVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select no,name from author";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL실행 ( no binding )
			rs = pstmt.executeQuery();
			
			// 6. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				
				// mapping
				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			// 2. 관련 : linux 꺼져있을 때 등등 connection안될 때 
			System.out.println("error :"+e);
		} finally {
			//clean-up; 자원정리는 만들어진 순서 거꾸로 하기
			try {
				if(rs != null) {
					rs.close();
				}
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
