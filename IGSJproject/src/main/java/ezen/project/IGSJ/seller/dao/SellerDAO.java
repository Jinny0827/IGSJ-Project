package ezen.project.IGSJ.seller.dao;

import java.util.List;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;

public interface SellerDAO {
	
	// 상품 등록 카테고리 불러오기
	public List<CategoryDTO> getCategory()throws Exception;
	
	// 상품 등록
	public void postRegister(ProductDTO product) throws Exception;
	public void postImgRegister(ProductFileDTO productFile) throws Exception;
	
	// 전체 상품 불러오기
	public List<ProductDTO> getProductList(int displayTotalContent, int pageContent, String searchType, String keyword,String userId) throws Exception;
	// 검색 결과에 따른 상품 출력
	public int searchProduct(String searchType, String keyword, String userId) throws Exception;
	
	// 판매자 상품 조회
		public ProductDTO sellerProductViewPage(String pno) throws Exception;
		
	// 판매자 상품 수정
	public void sellerProductModify(ProductDTO productDTO, ProductFileDTO productFileDTO) throws Exception;
	
	// 판매자 상품 삭제
	public int sellerRemoveProduct(String pno) throws Exception;

}
