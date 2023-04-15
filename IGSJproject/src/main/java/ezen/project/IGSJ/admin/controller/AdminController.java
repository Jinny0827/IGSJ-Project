package ezen.project.IGSJ.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.admin.service.AdminService;
import ezen.project.IGSJ.category.domain.CategoryDTO;
import ezen.project.IGSJ.member.domain.MemberDTO;
import ezen.project.IGSJ.product.domain.ProductDTO;
import ezen.project.IGSJ.productFile.domain.ProductFileDTO;
import ezen.project.IGSJ.seller.service.SellerService;
import ezen.project.IGSJ.utils.AwsS3;
import ezen.project.IGSJ.utils.pagination.PageIngredient;
import net.sf.json.JSONArray;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private SellerService sellerService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	// 관리자 페이지 입장
	@RequestMapping(value = "/admin/mainpage", method = RequestMethod.GET)
	public void adminMainPage(HttpServletRequest req) throws Exception {

		logger.info("관리자 페이지 입장");

	}

	// 관리자 페이지 입장
	@RequestMapping(value = "/admin/admindetail", method = RequestMethod.GET)
	public void adminDetailPage(HttpServletRequest req) throws Exception {

		logger.info("관리자 메뉴 페이지 입장");

	}

	// 전체 회원 불러오기
	@RequestMapping(value = "/admin/memberlist", method = RequestMethod.GET)
	public void getAllUsers(@RequestParam("pageNum") int pageNum,
			@RequestParam(value = "searchType", required = false, defaultValue = "userId") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, PageIngredient page, Model model) throws Exception {

		logger.info("관리자 페이지 - 회원 목록 출력 getAllUsers - controller");

		logger.info("관리자 페이지 - 회원 목록 출력 검색타입: {} , 검색어 : {} ", searchType, keyword);

		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(7,7,7);

		page.setPageNum(pageNum);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		page.setSearchTypeAndKeyword(searchType, keyword);

		// 게시글 총 갯수를 구한다. 단 검색타입과 키워드에 맞춘 결과에 대한 총 갯수를 출력해야한다.
		page.setTotalContent(adminService.searchMember(searchType, keyword));

		List<MemberDTO> adminMemberList = null;
		adminMemberList = adminService.getAllUsers(page.getSelectContent(), page.getContentNum(), searchType, keyword);
		model.addAttribute("adminMemberList", adminMemberList);
		model.addAttribute("page", page);

		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);

	}

	// 관리자 회원 정보 수정 페이지 진입
	@RequestMapping(value = "/admin/membermodifypage", method = RequestMethod.GET)
	public String adminMemberModifyPage(@RequestParam("userId") String userId, MemberDTO memberDTO, MemberAddressDTO memberAddressDTO, Model model)
			throws Exception {

		logger.info("관리자 회원 정보 수정 페이지 접속");

		memberDTO = adminService.adminSelectMember(userId);

		memberAddressDTO = adminService.adminSelectAddress(userId);

		model.addAttribute("userInfo", memberDTO);
		model.addAttribute("userAddressInfo", memberAddressDTO);

		return "/admin/membermodify";
	}

	// 관리자 회원 정보 수정 실행
	@RequestMapping(value = "/admin/membermodify", method = RequestMethod.POST)
	public String adminMemberModify(MemberDTO memberDTO, MemberAddressDTO memberAddressDTO, HttpSession session) throws Exception {

		logger.info("관리자 회원 정보 수정 시작");

		adminService.adminMemberModify(memberDTO, memberAddressDTO);

		return "redirect:/admin/memberlist?pageNum=1";
	}

	// 관리자 회원 삭제 ajax
	@ResponseBody
	@RequestMapping(value = "/admin/removeMember", method = RequestMethod.POST)
	public boolean adminRemoveMember(@RequestParam("userId") String userId) throws Exception {

		logger.info("관리자 회원 삭제 시작 controller");

		adminService.adminRemoveMember(userId);

		return true;
	}

	// 관리자 인증번호 생성하기
	@ResponseBody
	@RequestMapping(value = "/admin/removeMember", method = RequestMethod.GET)
	public String generateAdminAuthPwd(@RequestParam("tempAdminPwd") String tempAdminPwd) throws Exception {

		tempAdminPwd = RandomStringUtils.randomAlphanumeric(20);

		System.out.println("관리자 인증번호 생성 완료 : " + tempAdminPwd);

		return tempAdminPwd;

	}

	// 전체 상품 목록 불러오기
	@RequestMapping(value = "/admin/productlist", method = RequestMethod.GET)
	public void getProductList(@RequestParam("pageNum") int pageNum,
			@RequestParam(value = "searchType", required = false, defaultValue = "product_name") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, PageIngredient page, Model model) throws Exception {

		logger.info("관리자 페이지 - 상품 목록 출력 getProductList - controller");

		logger.info("관리자 페이지 - 상품 목록 출력 검색타입: {} , 검색어 : {} ", searchType, keyword);

		// 파라미터 순서 int contentNum , int maxPageNum, int selectContent
		page = new PageIngredient(5, 5, 5);

		page.setPageNum(pageNum);
		page.setSearchType(searchType);
		page.setKeyword(keyword);
		page.setSearchTypeAndKeyword(searchType, keyword);

		// 게시글 총 갯수를 구한다. 단 검색타입과 키워드에 맞춘 결과에 대한 총 갯수를 출력해야한다.
		page.setTotalContent(adminService.searchProduct(searchType, keyword));

		List<ProductDTO> adminProductList = null;
		adminProductList = adminService.getProductList(page.getSelectContent(), page.getContentNum(), searchType, keyword);
		model.addAttribute("adminProductList", adminProductList);
		model.addAttribute("page", page);

		// 현재 페이지가 몇페이지인지 쉽게 구분하기위한 구분자를 넘겨주자
		model.addAttribute("selectedPageNum", pageNum);

	}

	// 관리자 상품 정보 조회
	@RequestMapping(value = "/admin/productDetail", method = RequestMethod.GET)
	public String adminProductViewPage(@RequestParam("pno") String pno, ProductDTO productDTO, Model model) throws Exception {

		logger.info("관리자 회원 정보 수정 페이지 접속");

		productDTO = adminService.adminProductViewPage(pno);

		model.addAttribute("productInfo", productDTO);

		return "/admin/productdetail";
	}

	// 관리자 상품 정보 수정 페이지 진입
	@RequestMapping(value = "/admin/productmodify", method = RequestMethod.GET)
	public String adminProductModifyPage(@RequestParam("pno") String pno, ProductDTO productDTO, Model model) throws Exception {

		logger.info("관리자 상품 정보 수정 페이지 접속");

		List<CategoryDTO> category = null;

		category = sellerService.getCategory();

		model.addAttribute("category", JSONArray.fromObject(category));

		productDTO = adminService.adminProductViewPage(pno);

		model.addAttribute("productInfo", productDTO);

		return "/admin/productmodify";
	}

	// 관리자 상품 정보 수정
	@RequestMapping(value = "/admin/productmodify", method = RequestMethod.POST)
	public String adminProductModify(ProductDTO productDTO, ProductFileDTO productFile, @RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {

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

			adminService.adminProductModify(productDTO, productFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("제품 이미지 수정 완료");

		return "redirect:/admin/productDetail?pno=" + productDTO.getPno();
	}

	// 관리자, 판매자 로그인 페이지 진입
	@RequestMapping(value = "/managerLoginPage", method = RequestMethod.GET)
	public void managerLoginPage() throws Exception {

		logger.info("매니저 로그인 페이지 접속");

	}

	// 관리자, 판매자 로그인 메소드
	@RequestMapping(value = "/managerLogin", method = RequestMethod.POST)
	public String managerLogin(MemberDTO memberDTO, RedirectAttributes rda, HttpServletRequest req) throws Exception {

		logger.info("매니저 로그인 페이지 접속 : {}", memberDTO.getUserId());

		MemberDTO member = adminService.managerLogin(memberDTO);
		HttpSession session = req.getSession();

		if (member == null) {

			rda.addFlashAttribute("managerLoginFalse", false);
			logger.info("관리자 로그인 실패");

			return "redirect:/admin/managerLoginPage";

		} else {
			if (member.getUserVerify() == 128) {
				session.setAttribute("member", member);
				return "redirect:/admin/mainpage";

			} else if (member.getUserVerify() == 5) {
				session.setAttribute("member", member);
				return "redirect:/seller/mainpage";
			} else {
				logger.info("일반 회원 로그인 차단");

				rda.addFlashAttribute("blockNomalMember", false);

				return "redirect:/";
			}

		}
	}

	// 로그아웃 로직
	@RequestMapping(value = "/manager/managerLogout", method = RequestMethod.GET)
	public String memberLogout(HttpSession session) throws Exception {

		logger.info("유저 로그아웃, 로그아웃 계정 : {}", session.getAttribute("member").toString());

		session.invalidate();

		return "redirect:/";
	}

}
