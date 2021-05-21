package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.CustomerDao;
import com.douzone.bookmall.dao.OrderBookDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.CustomerVo;
import com.douzone.bookmall.vo.OrderBookVo;
import com.douzone.bookmall.vo.OrderVo;

// MAIN

public class BookMall {

	public static void main(String[] args) {

		System.out.println("====================Category====================");
		CategoryDao categoryDao = new CategoryDao();
		//insert
		String[] name = {"소설","경제","컴퓨터/IT"};
		for(int i=0;i<3;i++) {
			if(categoryDao.insert(new CategoryVo(i+1L,name[i]))) {
				System.out.println("category 입력 성공");
			};
		}
		//select
		List<CategoryVo> categoryList = categoryDao.findAll();
		for(CategoryVo vo : categoryList) {
			System.out.println(vo);
		}
		System.out.println("============================================");

		System.out.println("====================Book====================");
		BookDao bookDao = new BookDao();
		//insert
		String[] book_title = {"소설1","경제1","경제2"};
		long[] book_price = {10000,20000,50000};
		long[] book_category_list = {1L,2L,2L};
		for(int i=0;i<3;i++) {
			if(bookDao.insert(new BookVo(i+1L,book_title[i],book_price[i],new CategoryVo(book_category_list[i])))) {
				System.out.println("book 입력 성공");
			};
		}
		//select
		List<BookVo> bookList = bookDao.findAll();
		for(BookVo vo : bookList) {
			System.out.println(vo);
		}
		System.out.println("============================================");

		System.out.println("====================Customer====================");
		CustomerDao customerDao = new CustomerDao();
		//insert
		String[] cus_name = {"고객1","고객2"};
		String[] cus_email = {"one@gmail.com","two@gmail.com"};
		String[] cus_phone = {"010-2222-2222","010-1122-3344"};
		String[] cus_password = {"1111*","3333&"};
		for(int i=0;i<2;i++) {
			if(customerDao.insert(new CustomerVo(i+1L,cus_name[i],cus_email[i],cus_phone[i],cus_password[i]))) {
				System.out.println("customer 입력 성공");
			};
		}
		//select
		List<CustomerVo> customerList = customerDao.findAll();
		for(CustomerVo vo : customerList) {
			System.out.println(vo);
		}
		System.out.println("============================================");

		System.out.println("====================Order====================");
		OrderDao orderDao = new OrderDao();
		// insert
		if (orderDao.insert(new OrderVo(1L,"20210909-123A",15000L,"부산시 남구 오륙도로85", new CustomerVo(1L)))) {
			System.out.println("order 입력 성공");
		}

		// select
		List<OrderVo> orderList = orderDao.findAll();
		for (OrderVo vo : orderList) {
			System.out.println(vo);
		}
		System.out.println("============================================");
		
		System.out.println("====================Cart====================");
		CartDao cartDao = new CartDao();
		//insert
		long[] cus_no = {2L,1L,2L};
		long[] book_no = {3L,1L,2L};
		int[] cart_num = {5,10,2};
		for(int i=0;i<3;i++) {
			if(cartDao.insert(new CartVo(new CustomerVo(cus_no[i]),new BookVo(book_no[i]),cart_num[i]))) {
				System.out.println("cart 입력 성공");
			};
		}

		// select
		List<CartVo> cartList = cartDao.findAll();
		for (CartVo vo : cartList) {
			System.out.println(vo);
		}
		System.out.println("============================================");
		
		System.out.println("====================OrderBook====================");
		OrderBookDao OrderBookDao = new OrderBookDao();
		//insert
		long[] book2_no = {3L,1L};
		long[] order2_no = {1L,1L};
		long[] ob_price = {5000L,8000L};
		int[] ob_num = {5,2};
		for(int i=0;i<2;i++) {
			if(OrderBookDao.insert(new OrderBookVo(new BookVo(book2_no[i]),new OrderVo(order2_no[i]),ob_price[i],ob_num[i]))) {
				System.out.println("OrderBook 입력 성공");
			};
		}

		// select
		List<OrderBookVo> obList = OrderBookDao.findAll();
		for (OrderBookVo vo : obList) {
			System.out.println(vo);
		}
		System.out.println("============================================");
		

	}

}
