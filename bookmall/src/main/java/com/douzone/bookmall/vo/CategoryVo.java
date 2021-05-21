package com.douzone.bookmall.vo;

public class CategoryVo {
	private long no;
	private String name;
	
	public CategoryVo() {
		
	}
	public CategoryVo(Long no) {
		this.no = no;
	}
	public CategoryVo(Long no, String name) {
		this.no = no;
		this.name = name;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + "]";
	}
}
