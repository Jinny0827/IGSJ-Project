package ezen.project.IGSJ.seller.controller;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.member.domain.MemberDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;
import ezen.project.IGSJ.seller.domain.OrderVO;
import ezen.project.IGSJ.seller.service.SellerService;
import ezen.project.IGSJ.utils.AwsS3;
import ezen.project.IGSJ.utils.pagination.PageIngredient;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/seller/*")
public class SellerController {

	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

	@Inject
	private SellerService sellerService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	// 메인페이지
	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public void getMain() throws Exception {
	}

	// 상품 등록 페이지 접속
	@RequestMapping(value = "/productRegister", method = RequestMethod.GET)
	public void sellerRegisterPage() throws Exception {

		logger.info("판매자 상품 등록 페이지 접속");
	}

	// 카테고리 불러오기 ajax
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public JSONArray getRegister() throws Exception {

		List<CategoryDTO> category = null;
		category = sellerService.getCategory();
		/* model.addAttribute("category", JSONArray.fromObject(category)); */

		return JSONArray.fromObject(category);
	}

	// aws s3 상품 업로드
	@RequestMapping(value = "/productRegister", method = RequestMethod.POST)
	public String postRegister(ProductDTO product, ProductFileDTO productFile,
			@RequestParam("product_img") MultipartFile file, HttpServletRequest request) throws Exception {

		AwsS3 awsS3 = AwsS3.getInstance();
		String s3ObjectUrl = null;
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		UUID uuid = UUID.randomUUID();

		try {
			// S3 bucket에 파일 업로드
			String fileName = "/igsjproject/images/" + uuid.toString().substring(0, 4) + file.getOriginalFilename();
			InputStream is = file.getInputStream();

			s3ObjectUrl = awsS3.upload(is, fileName, file.getContentType(), file.getSize());

			// Set the file properties
			String rs = RandomStringUtils.randomAlphanumeric(20);
			product.setPno(rs);
			productFile.setPno(rs);
			product.setUserId(member.getUserId());
			productFile.setOriginalFileName(fileName);
			productFile.setStoredFileRootName(s3ObjectUrl);

			// Call the sellerService to save the product and file information
			sellerService.postRegister(product, productFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/seller/productlist?pageNum=1";
	}

	// --------------------------------------------------------------------------
	// ck 에디터에서 파일 업로드
	// --------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/register/ckUpload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile) throws IOException {

		// Json 객체 생성
		JsonObject json = new JsonObject();
		
		// Json 객체를 출력하기 위해 PrintWriter 생성
		PrintWriter printWriter = null;
		OutputStream out = null;
		
		// 파일을 가져오기 위해 MultipartHttpServletRequest 의 getFile 메서드 사용
		MultipartFile file = multiFile.getFile("upload");
		
		// S3 서버로 이미지를 보내기 위해 인스턴스 생성
		AwsS3 awsS3 = AwsS3.getInstance();
		String uploadPath = null;
		UUID uuid = UUID.randomUUID();

		// 파일이 비어있지 않고(비어 있다면 null 반환)
		if (file != null) {
			// 파일 사이즈가 0보다 크고, 파일이름이 공백이 아닐때
			if (file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if (file.getContentType().toLowerCase().startsWith("image/")) {

					try {
						// 파일 이름 설정
						String fileName = "/igsjproject/ckUpload/" + uuid.toString().substring(0, 4)
								+ file.getOriginalFilename();
						InputStream is = file.getInputStream();

						// 파일이 실제로 저장되는 경로
						uploadPath = awsS3.upload(is, fileName, file.getContentType(), file.getSize());

						// 저장되는 파일에 경로 설정
						File uploadFile = new File(uploadPath);
						if (!uploadFile.exists()) {
							uploadFile.mkdirs();
						}

						// 클라이언트에 이벤트 추가
						printWriter = response.getWriter();
						response.setContentType("text/html");

						// 파일이 연결되는 Url 주소 설정
						String fileUrl = uploadPath;

						// 생성된 json 객체를 이용해 파일 업로드 + 이름 + 주소를 CkEditor에 전송
						json.addProperty("uploaded", 1);
						json.addProperty("fileName", fileName);
						json.addProperty("url", fileUrl);
						printWriter.println(json);

					} catch (IOException e) {
						e.printStackTrace();

					} finally {
						if (out != null) {
							out.close();
						}
						if (printWriter != null) {
							printWriter.close();
						}
					}
				}
			}
		}
		return null;
	}

	// 전체 상품 목록 불러오기
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public void getProductList(@RequestParam("pageNum") int pageNum,
			@RequestParam(value = "searchType", required = false, defaultValue = "product_name") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, PageIngredient page,
			Model model, HttpServletRequest request, String userId) throws Exception {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		userId = member.getUserId();
		logger.info("userId===============================>>>>>>" + userId);
		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(5, 5, 5);

		page.setPageNum(pageNum);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		page.setSearchTypeAndKeyword(searchType, keyword);

		// 게시글 총 갯수를 구한다. 단 검색타입과 키워드에 맞춘 결과에 대한 총 갯수를 출력해야한다.
		page.setTotalContent(sellerService.searchProduct(searchType, keyword, userId));

		List<ProductDTO> sellerProductList = null;
		sellerProductList = sellerService.getProductList(page.getSelectContent(), page.getContentNum(), searchType,
				keyword, userId);
		model.addAttribute("sellerProductList", sellerProductList);
		model.addAttribute("page", page);
		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);

	}

	// 관리자 상품 정보 조회
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public String adminProductViewPage(@RequestParam("pno") String pno, ProductDTO productDTO, Model model)
			throws Exception {

		logger.info("관리자 회원 정보 수정 페이지 접속");

		productDTO = sellerService.sellerProductViewPage(pno);

		model.addAttribute("productInfo", productDTO);

		return "/seller/productdetail";
	}

	// 관리자 상품 정보 수정 페이지 진입
	@RequestMapping(value = "/productmodify", method = RequestMethod.GET)
	public String adminProductModifyPage(@RequestParam("pno") String pno, ProductDTO productDTO, Model model)
			throws Exception {

		logger.info("관리자 상품 정보 수정 페이지 접속");

		List<CategoryDTO> category = null;

		category = sellerService.getCategory();

		model.addAttribute("category", JSONArray.fromObject(category));

		productDTO = sellerService.sellerProductViewPage(pno);

		model.addAttribute("productInfo", productDTO);

		return "/seller/productmodify";
	}

	// 관리자 상품 정보 수정
	@RequestMapping(value = "/productmodify", method = RequestMethod.POST)
	public String sellerProductModify(ProductDTO productDTO, ProductFileDTO productFile,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

		logger.info("관리자 상품 정보 수정 시작 controller");
		AwsS3 awsS3 = AwsS3.getInstance();
		String s3ObjectUrl = null;

		try {
			// Upload file to S3 bucket
			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();

			s3ObjectUrl = awsS3.upload(is, fileName, file.getContentType(), file.getSize());

			logger.info("파일 업로드 위치 : {}", s3ObjectUrl);

			if (productDTO.getOriginalFileName() != s3ObjectUrl) {
				awsS3.delete(productDTO.getOriginalFileName());
			}
			productFile.setOriginalFileName(fileName);
			productFile.setStoredFileRootName(s3ObjectUrl);

			sellerService.sellerProductModify(productDTO, productFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("제품 이미지까지 수정 완료");

		return "redirect:/seller/productDetail?pno=" + productDTO.getPno();
	}

	// 상품 정보 삭제
	@ResponseBody
	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
	public boolean sellerProductDelete(@RequestParam("pno") String pno) throws Exception {

		logger.info("pno=>" + pno);
		// 삭제를 위한 객체 생성
		AwsS3 awsS3 = AwsS3.getInstance();

		// 삭제 시 삭제할 이미지 경로를 얻기위한 메서드.
		ProductDTO product = sellerService.sellerProductViewPage(pno);

		// 상품 삭제 시작
		int Result = sellerService.sellerRemoveProduct(pno);

		// 상품 삭제가 완료되면 s3서버에 있는 이미지도 삭제한다.
		if (Result == 1) {
			awsS3.delete(product.getOriginalFileName());

		}

		return true;
	}

	// 주문배송조회페이지
	@RequestMapping(value="/orderlist", method= RequestMethod.GET)
	public void getOrder( @RequestParam("pageNum") int pageNum, PageIngredient page,
			Model model, HttpServletRequest request, String userId) throws Exception{
		
		// 판매자가 등록한 상품에 대한 정보만 나와야 되기때문에 userId 값을 불러서 넣어준다
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		userId = member.getUserId();
		
		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(5, 5, 5);
		page.setPageNum(pageNum);
		page.setTotalContent(sellerService.searchOrder(userId));
		
		List<OrderVO> order = null;
		 order = sellerService.getOrderList(userId, page.getContentNum(),page.getSelectContent());
		model.addAttribute("orderList", order);
		model.addAttribute("page", page);
		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);
		
	}
	
	// 주문 배송상태 변경
	@ResponseBody
	@RequestMapping(value="/changePaymentStatus",method=RequestMethod.POST)
	public int changePaymentStatus(@RequestParam int odNum, @RequestParam String paymentStatus, OrderVO order) throws Exception{
		order.setOdNum(odNum);
		order.setPaymentStatus(paymentStatus);
		
		return sellerService.changePaymentStatus(order);
	}
	
	
	
}