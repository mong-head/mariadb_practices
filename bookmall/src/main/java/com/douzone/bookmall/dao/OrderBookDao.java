package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.CustomerVo;
import com.douzone.bookmall.vo.OrderBookVo;
import com.douzone.bookmall.vo.OrderVo;

public class OrderBookDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC driver loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. DriverManager - get connection
			String url = "jdbc:mysql://192.168.254.40:3307/bookmall?chareset=utf8"; //linux server ip
			conn	   = DriverManager.getConnection(url,"bookmall","bookmall");
			conn.createStatement();
			//System.out.println("success connection");
		} catch (ClassNotFoundException e) {
			System.out.println("fail to load driver:"+e);
		}
		return conn;	

	}
	
	public boolean insert(OrderBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			
			// 3. prepare Statement 
			String sql = "insert into order_book values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL binging
			pstmt.setLong(1, vo.getBookVo().getNo()); // parameter mapping
			pstmt.setLong(2, vo.getOrderVo().getNo());
			pstmt.setLong(3, vo.getPrice());
			pstmt.setInt(4, vo.getNum());
			
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
	
	public List<OrderBookVo> findAll() {
		List<OrderBookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select ob.price, ob.num, b.no, b.title, b.price, b.category_no , o.no, o.order_info, o.payment, o.address, o.customer_no"
					+ " from order_book ob"
					+ " join book b on b.no = ob.book_no"
					+ " join order_ o on o.no = ob.order_no";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL실행 ( no binding )
			rs = pstmt.executeQuery();
			
			// 6. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				long ob_price = rs.getLong(1);
				int ob_num = rs.getInt(2);
				
				long b_no = rs.getLong(3);
				String b_title = rs.getString(4);
				long b_price = rs.getLong(5);
				long b_category_no = rs.getLong(6);
				
				long o_no = rs.getLong(7);
				String o_order_info = rs.getString(8);
				long o_payment = rs.getLong(9);
				String o_address = rs.getString(10);
				long o_customer_no = rs.getLong(11);
				
				// mapping
				OrderBookVo vo = new OrderBookVo(new BookVo(b_no,b_title,b_price,new CategoryVo(b_category_no)),
											new OrderVo(o_no,o_order_info, o_payment, o_address, new CustomerVo(o_customer_no)),
											ob_price,ob_num);
				
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
