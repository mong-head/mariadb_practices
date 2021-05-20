package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	
	//refactoring; 중복되는 코드 없애기 위함 (유지보수 good)
	// 중복 코드 : driver, connection부분
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC driver loading
			// 		loading 후 Driver Class가 DriverManager만듦 - 우리가 new로 drivermanager만들 필요없음
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. DriverManager - get connection
			String url = "jdbc:mysql://192.168.254.40:3307/employees?chareset=utf8"; //linux server ip
			conn	   = DriverManager.getConnection(url,"hr","hr");
			conn.createStatement();
			System.out.println("success connection");
		} catch (ClassNotFoundException e) {
			System.out.println("fail to load driver:"+e);
		}
		return conn;	

	}
	
	public List<EmployeeVo> findBySalary(int minSalary, int maxSalary){
		List<EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1,2 : driver loading, connection
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select e.emp_no, e.first_name, e.last_name, s.salary "
					+ " from employees e, salaries s "
					+ " where s.to_date > current_date "
					+ " and e.emp_no = s.emp_no "
					+ " and s.salary between ? and ? "
					+ " order by s.salary asc";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);
			
			// 5. SQL실행
			rs = pstmt.executeQuery();
			
			// 6. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				// type 잘 맞춰서 적어야함
				long empNo = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				int salary = rs.getInt(4);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstname);
				vo.setLastName(lastname);
				vo.setFullName(firstname+" "+lastname);
				vo.setSalary(salary);
				
				System.out.println("emp num : "+vo.getEmpNo()+" , full name : "+vo.getFullName()+" , salary : "+vo.getSalary());
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

	public List<EmployeeVo> findByName(String name) {
		List<EmployeeVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			// 3. prepare sql statement
			String sql = "select emp_no, first_name,last_name,date_format(hire_date,'%Y-%m-%d')"
					+ " from employees"
					+ " where first_name like ? "
					+ " and last_name like ?";
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1, "%"+name+"%");
			pstmt.setString(2, "%"+name+"%");
			
			// 4. SQL실행
			rs = pstmt.executeQuery();
			
			// 5. result 가져오기
			while(rs.next() /*각각의 행 가지고 오기*/) {
				// type 잘 맞춰서 적어야함
				long empNo = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String birthDate = rs.getString(4); //date는 string으로 받으면 됨
				
				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstname);
				vo.setLastName(lastname);
				vo.setBirthDate(birthDate);
				vo.setFullName(firstname+" "+lastname);
				
				System.out.println("emp num : "+vo.getEmpNo()+" ,birth : "+vo.getBirthDate()+", first name : "+vo.getFirstName());
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
