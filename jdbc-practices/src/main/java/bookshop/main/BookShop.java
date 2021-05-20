package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		
		displayBookInfo();

		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		int no = scanner.nextInt();
		
		// (1) 입력된 번호에 맞는 책을 찾아 대여 되었음(상태코드=0)을 체크 합니다.
		new BookDao().update(no,"대여중");
		// (2) Book 객체의 정보를 출력
		displayBookInfo();
		scanner.close();
	}
	
	public static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo :list) {
			System.out.printf("[%d] 책 제목 : %s, 작가 : %s, 대여 유무 : %s\n",vo.getNo(),vo.getTitle(),vo.getAuthorName(),vo.getStatus());
		}
	}
}