package com.douzone.bookmall.vo;

public class CustomerVo {
	private long no;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	public CustomerVo() {
		
	}
	
	public CustomerVo(long no) {
		this.no = no;
	}
	
	public CustomerVo(long no, String name, String email, String phone, String password) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "CustomerVo [no=" + no + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password="
				+ password + "]";
	}
}
