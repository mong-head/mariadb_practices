package com.douzone.bookmall.vo;

public class CartVo {
	private CustomerVo customerVo;
	private BookVo bookVo;
	private int num;
	
	public CartVo() {
		
	}
	
	
	public CartVo(CustomerVo customerVo, BookVo bookVo, int num) {
		this.customerVo = customerVo;
		this.bookVo = bookVo;
		this.num = num;
	}


	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	public BookVo getBookVo() {
		return bookVo;
	}
	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "CartVo [customerVo=" + customerVo + ", bookVo=" + bookVo + ", num=" + num + "]";
	}
	
}
