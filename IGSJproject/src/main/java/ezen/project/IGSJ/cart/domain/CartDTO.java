package ezen.project.IGSJ.cart.domain;

import org.springframework.stereotype.Component;

@Component
public class CartDTO {
	/*
	 	cartNum	varchar(100)	NO	PRI
		userId	varchar(20)	NO	
		pno	varchar(200)	NO	
		productCnt	int	NO	
	 */
	private String cartNum;
	private String userId;
	private String pno;
	private int productCnt;
	private String size;

	// 조인용 변수
	private String storedFileRootName;
	private String product_name;
	private int product_price;

	public CartDTO() {
	}

	public CartDTO(String cartNum, String userId, String pno, int productCnt, String size) {
		this.cartNum = cartNum;
		this.userId = userId;
		this.pno = pno;
		this.productCnt = productCnt;
		this.size = size;
	}

	public String getCartNum() {
		return cartNum;
	}

	public void setCartNum(String cartNum) {
		this.cartNum = cartNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	

	public String getStoredFileRootName() {
		return storedFileRootName;
	}

	public void setStoredFileRootName(String storedFileRootName) {
		this.storedFileRootName = storedFileRootName;
	}

	@Override
	public String toString() {
		return "CartDTO [cartNum=" + cartNum + ", userId=" + userId + ", pno=" + pno + ", productCnt=" + productCnt
				+ ", size=" + size + ", storedFileRootName=" + storedFileRootName + ", product_name=" + product_name
				+ ", product_price=" + product_price + "]";
	}

	
}
