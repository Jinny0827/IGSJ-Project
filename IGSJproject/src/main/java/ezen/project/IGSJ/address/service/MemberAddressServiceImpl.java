package ezen.project.IGSJ.address.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.project.IGSJ.address.dao.MemberAddressDAO;
import ezen.project.IGSJ.address.domain.MemberAddressDTO;

@Service
public class MemberAddressServiceImpl implements MemberAddressService {

	private static final Logger logger = LoggerFactory.getLogger(MemberAddressServiceImpl.class);
	
	@Autowired
	private MemberAddressDAO memberAddressDAO;
	
	//회원 주소 가입
	@Override
	public void signUpAddressMember(MemberAddressDTO memberAddressDTO) throws Exception {
		
		logger.info("회원주소 추가 signUpMember - Service");
		
		memberAddressDAO.signUpAddressMember(memberAddressDTO);
	}
	//회원 주소 수정
	@Override
	public void memberAddressModify(MemberAddressDTO memberAddressDTO) throws Exception {
		
		logger.info("회원 주소 수정 memberAddressModify - Service");
		
		memberAddressDAO.memberAddressModify(memberAddressDTO);
	}
	
	//회원 정보 찾기
	@Override
	public MemberAddressDTO memberAddressProfile(String userId) throws Exception {
		
		logger.info("회원 정보 찾기 memberAddressProfile - Service");
		
		return memberAddressDAO.memberAddressProfile(userId);
	}
	

}
