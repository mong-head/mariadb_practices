package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.CategoryVo;


public class CategoryDaoTest {

	public static void main(String[] args) {
		//insert(1, "소설");
		findAll();

	}

	private static void insert(Long no, String name) {
		CategoryVo vo = null;
		
		vo = new CategoryVo();
		vo.setNo(no);
		vo.setName(name);
		new CategoryDao().insert(vo);
	}
	
	private static void findAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
		
	}
}
