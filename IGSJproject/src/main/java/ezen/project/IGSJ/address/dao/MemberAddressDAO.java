package ezen.project.IGSJ.address.dao;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;

public interface MemberAddressDAO {

	//회원 가입(주소)
	public void signUpAddressMember(MemberAddressDTO memberAddressDTO) throws Exception;

	//회원 수정(주소)
	public void memberAddressModify(MemberAddressDTO memberAddressDTO) throws Exception;

	//회원 정보 찾기(주소)
	public MemberAddressDTO memberAddressProfile(String userId) throws Exception;
	
}
