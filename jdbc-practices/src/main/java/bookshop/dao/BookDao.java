package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
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
	
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			
			// 3. prepare Statement 
			String sql = "insert into book values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL binging
			pstmt.setString(1, vo.getTitle()); // parameter mapping
			pstmt.setString(2, vo.getStatus());
			pstmt.setLong(3, vo.getAuthorNo());
			
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

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select b.no,title,status,a.no, name"
					+ " from book b"
					+ " join author a on b.author_no = a.no";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL실행 ( no binding )
			rs = pstmt.executeQuery();
			
			// 6. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				long author_no = rs.getLong(4);
				String name = rs.getString(5);
				
				// mapping
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorNo(author_no);
				vo.setAuthorName(name);
				
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

	public boolean update(int no, String status) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. Statement 생성 - query 실행위함
			String sql = "update book set status = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1,status);
			pstmt.setLong(2,no);
			
			// 5. SQL실행
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
}
