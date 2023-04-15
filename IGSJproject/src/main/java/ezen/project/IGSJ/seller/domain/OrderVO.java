package ezen.project.IGSJ.seller.domain;

import java.sql.Date;

public class OrderVO {

	private int odNum; // 주문 생성 번호
	private String orderNum; // 주문번호
	private Date order_date; // 주문일자
	private String pno; // 상품번호
	private int productCnt; // 주문수량
	private int productPrice; // 주문가격
	private String paymentStatus; // 배송상태
	private String recipient; // 수령인
	private String recipient_phone; // 수령인 핸드폰 번호

	public OrderVO() {
	}

	public OrderVO(int odNum, String orderNum, Date order_date, String pno, int productCnt, int productPrice,
			String paymentStatus, String recipient, String recipient_phone) {
		super();
		this.odNum = odNum;
		this.orderNum = orderNum;
		this.order_date = order_date;
		this.pno = pno;
		this.productCnt = productCnt;
		this.productPrice = productPrice;
		this.paymentStatus = paymentStatus;
		this.recipient = recipient;
		this.recipient_phone = recipient_phone;
	}

	public int getOdNum() {
		return odNum;
	}

	public void setOdNum(int odNum) {
		this.odNum = odNum;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public int getProductCnt() {
		return productCnt;
	}

	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipient_phone() {
		return recipient_phone;
	}

	public void setRecipient_phone(String recipient_phone) {
		this.recipient_phone = recipient_phone;
	}

	@Override
	public String toString() {
		return "OrderVO [odNum=" + odNum + ", orderNum=" + orderNum + ", order_date=" + order_date + ", pno=" + pno
				+ ", productCnt=" + productCnt + ", productPrice=" + productPrice + ", paymentStatus=" + paymentStatus
				+ ", recipient=" + recipient + ", recipient_phone=" + recipient_phone + "]";
	}

}
