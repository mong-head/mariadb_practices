package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CategoryVo;

public class BookDaoTest {

	public static void main(String[] args) {
		//insert(5,"과학세상",15000,1L);
		findAll();

	}

	private static void insert(String title,Long price, Long category_no) {
		BookVo vo = null;
		
		vo = new BookVo(1 ,title,price,new CategoryVo(category_no));
		new BookDao().insert(vo);
	}
	
	private static void findAll() {
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		
	}

}
