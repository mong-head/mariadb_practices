package hr;

// findBySalary

import java.util.List;
import java.util.Scanner;

public class HRMain2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("min salary > ");
		int minSalary = scanner.nextInt();
		
		System.out.print("max salary > ");
		int maxSalary = scanner.nextInt();
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.findBySalary(minSalary, maxSalary);
		
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
		
		scanner.close();
	}

}
