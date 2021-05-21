package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CustomerVo;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDao {
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
	
	public boolean insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = getConnection();
			
			// 3. prepare Statement 
			String sql = "insert into order_ values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL binging
			pstmt.setLong(1, vo.getNo()); // parameter mapping
			pstmt.setString(2, vo.getOrder_info());
			pstmt.setLong(3, vo.getPayment());
			pstmt.setString(4, vo.getAddress());
			pstmt.setLong(5, vo.getCustomerVo().getNo());
			
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
	
	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select o.no,order_info,payment,address,customer_no,c.name, c.email, c.phone, c.password"
					+ " from order_ o"
					+ " join customer c on c.no = o.customer_no";
			pstmt = conn.prepareStatement(sql);
			
			// 4. SQL실행 ( no binding )
			rs = pstmt.executeQuery();
			
			// 6. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				long no = rs.getLong(1);
				String order_info = rs.getString(2);
				long payment = rs.getLong(3);
				String address = rs.getString(4);
				
				long customer_no = rs.getLong(5);
				String customer_name = rs.getString(6);
				String customer_email = rs.getString(7);
				String customer_phone = rs.getString(8);
				String customer_password = rs.getString(9);
				
				// mapping
				OrderVo vo = new OrderVo(no,order_info,payment,address,
							new CustomerVo(customer_no,customer_name,customer_email,customer_phone,customer_password));
				
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
