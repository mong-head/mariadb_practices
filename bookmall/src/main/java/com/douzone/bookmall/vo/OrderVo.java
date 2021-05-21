package com.douzone.bookmall.vo;

public class OrderVo {
	private long no;
	private String order_info; //주문 번호
	private long payment;
	private String address;
	private CustomerVo customerVo;
	
	public OrderVo() {
		
	}
	public OrderVo(long no) {
		this.no = no;
	}
	public OrderVo(long no, String order_info, long payment, String address, CustomerVo customerVo) {
		super();
		this.no = no;
		this.order_info = order_info;
		this.payment = payment;
		this.address = address;
		this.customerVo = customerVo;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getOrder_info() {
		return order_info;
	}
	public void setOrder_info(String order_info) {
		this.order_info = order_info;
	}
	public long getPayment() {
		return payment;
	}
	public void setPayment(long payment) {
		this.payment = payment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CustomerVo getCustomerVo() {
		return customerVo;
	}
	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}
	
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", order_info=" + order_info + ", payment=" + payment + ", address=" + address
				+ ", customerVo=" + customerVo + "]";
	}
}
