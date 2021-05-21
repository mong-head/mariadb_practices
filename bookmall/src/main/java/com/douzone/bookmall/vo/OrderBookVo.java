package com.douzone.bookmall.vo;

public class OrderBookVo {
	private BookVo bookVo;
	private OrderVo orderVo;
	private long price;
	private int num;
	
	public OrderBookVo() {
		
	}
	
	
	public OrderBookVo(BookVo bookVo, OrderVo orderVo, long price, int num) {
		super();
		this.bookVo = bookVo;
		this.orderVo = orderVo;
		this.price = price;
		this.num = num;
	}


	public BookVo getBookVo() {
		return bookVo;
	}
	public void setBookVo(BookVo bookVo) {
		this.bookVo = bookVo;
	}
	public OrderVo getOrderVo() {
		return orderVo;
	}
	public void setOrderVo(OrderVo orderVo) {
		this.orderVo = orderVo;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "OrderBookVo [bookVo=" + bookVo + ", orderVo=" + orderVo + ", price=" + price + ", num=" + num + "]";
	}
}
