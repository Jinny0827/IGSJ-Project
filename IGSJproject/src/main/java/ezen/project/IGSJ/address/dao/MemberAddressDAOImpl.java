package ezen.project.IGSJ.address.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.address.domain.MemberAddressDTO;

@Repository
public class MemberAddressDAOImpl implements MemberAddressDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberAddressDAOImpl.class);
	
	private static final String NAME_SPACE = "mappers.memberAddressMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원 가입(주소)
	@Override
	public void signUpAddressMember(MemberAddressDTO memberAddressDTO) throws Exception {
		
		logger.info("회원 주소 추가 signUpMember - DAO");
		
		sqlSession.insert(NAME_SPACE + ".signUpAddressMember", memberAddressDTO);
		
	}
	//회원 수정(주소)
	@Override
	public void memberAddressModify(MemberAddressDTO memberAddressDTO) throws Exception {

		logger.info("회원 주소 추가 signUpMember - DAO");
		
		sqlSession.update(NAME_SPACE + ".memberAddressModify", memberAddressDTO);
	}
	
	//회원 정보 찾기(주소)
	@Override
	public MemberAddressDTO memberAddressProfile(String userId) throws Exception {
		
		logger.info("회원 정보 찾기 memberAddressProfile - DAO");
		
		return sqlSession.selectOne(NAME_SPACE + ".memberAddressProfile", userId);
	}
	
}
