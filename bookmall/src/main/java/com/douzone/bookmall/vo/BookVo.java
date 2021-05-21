package com.douzone.bookmall.vo;

public class BookVo {
	private long no;
	private String title;
	private long price;
	private CategoryVo categoryVo;
	
	public BookVo() {
		
	}
	public BookVo(long no) {
		this.no = no;
	}
	public BookVo(long no, String title, long price, CategoryVo categoryVo) {
		this.no = no;
		this.title = title;
		this.price = price;
		this.categoryVo = categoryVo;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public CategoryVo getCategoryVo() {
		return categoryVo;
	}
	public void setCategoryVo(CategoryVo categoryVo) {
		this.categoryVo = categoryVo;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", categoryVo=" + categoryVo + "]";
	}
}
