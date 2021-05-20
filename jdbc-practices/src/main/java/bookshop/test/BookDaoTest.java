package bookshop.test;

import java.util.List;

import bookshop.dao.AuthorDao;
import bookshop.dao.BookDao;
import bookshop.vo.AuthorVo;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		//insertTest();
		//findAllTest();
		updateTest();
		findAllTest();

	}
	
	private static void updateTest() {
		new BookDao().update(1,"대여가능");
		
	}

	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		
	}
	public static void insertTest() {
		BookVo vo = null;
		
		vo = new BookVo();
		vo.setAuthorNo(3L);
		vo.setStatus("대여가능");
		vo.setTitle("트와일라잇");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(3L);
		vo.setStatus("대여가능");
		vo.setTitle("뉴문");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(3L);
		vo.setStatus("대여가능");
		vo.setTitle("이클립스");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(3L);
		vo.setStatus("대여가능");
		vo.setTitle("브레이킹던");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(2L);
		vo.setStatus("대여가능");
		vo.setTitle("아리랑");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(4L);
		vo.setStatus("대여가능");
		vo.setTitle("젊은그들");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(6L);
		vo.setStatus("대여가능");
		vo.setTitle("귀천");
		new BookDao().insert(vo);
		
		vo = new BookVo();
		vo.setAuthorNo(2L);
		vo.setStatus("대여가능");
		vo.setTitle("태백산맥");
		new BookDao().insert(vo);
		
//		vo = new BookVo();
//		vo.setAuthorNo(1L);
//		vo.setStatus("대여가능");
//		vo.setTitle("풀하우스");
//		new BookDao().insert(vo);
	}

}
