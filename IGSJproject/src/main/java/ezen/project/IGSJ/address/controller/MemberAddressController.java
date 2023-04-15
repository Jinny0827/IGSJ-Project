package ezen.project.IGSJ.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;
import ezen.project.IGSJ.address.service.MemberAddressService;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/address")
public class MemberAddressController {

	private static final Logger logger = LoggerFactory.getLogger(MemberAddressController.class);
	
	@Autowired
	private MemberAddressService memberAddressService;
	
	//회원 주소 가입
	@PostMapping("/memberAddressSignUp")
	public void signUpAddressMember(@RequestBody MemberAddressDTO memberAddressDTO) throws Exception {
		
		logger.info("회원 주소 가입 memberAddressDTO ==> {}", memberAddressDTO.toString());
		
		memberAddressService.signUpAddressMember(memberAddressDTO);
	}
	//회원 주소 수정
	@PostMapping("/memberAddressModify")
	public void memberAddressModify(@RequestBody MemberAddressDTO memberAddressDTO) throws Exception {
		
		logger.info("회원 주소 수정 memberAddressDTO ==> {}", memberAddressDTO.toString());
		
		memberAddressService.memberAddressModify(memberAddressDTO);
	}
	//회원 정보 찾기(주소)
	@ResponseBody
	@GetMapping("/memberAddressProfile/{userId}")
	public MemberAddressDTO memberAddressProfile(@PathVariable String userId) throws Exception {
		
		logger.info("회원 정보 찾기 memberAddressProfile - Controller");
		
		MemberAddressDTO memberAddressDTO = memberAddressService.memberAddressProfile(userId);
	
		return memberAddressDTO;
	}
	
}
