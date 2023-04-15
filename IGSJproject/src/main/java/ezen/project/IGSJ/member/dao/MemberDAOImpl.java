package ezen.project.IGSJ.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.member.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "mappers.memberMapper";

	// 회원가입 로직
	@Override
	public void signUpMember(MemberDTO memberDTO) throws Exception {

		logger.info("회원가입 실행 signUpMember - DAO");

		sqlSession.insert(NAME_SPACE + ".signUpMember", memberDTO);
	}

	// 로그인 기능 구현
	@Override
	public MemberDTO memberLogin(MemberDTO memberDTO) throws Exception {

		logger.info("로그인 DAO");

		return sqlSession.selectOne(NAME_SPACE + ".memberLogin", memberDTO);

	}

	// 카카오API 활용 로그인 : 1. 가입되어 있는지 확인(MemberDTO에 저장되어 있는 회원인지 확인)
	@Override
	public MemberDTO KakaoSignedMemberCheck(MemberDTO memberDTO) throws Exception {
		return sqlSession.selectOne(NAME_SPACE + ".KakaoSignedMemberCheck", memberDTO);
	} // KakaoSignedMemberCheck()

	// 카카오API 활용 로그인 : 3. 가입이 안되어 있으면 회원가입(MemberDTO에 회원 정보 저장)하고, 이어서 바로 로그인 실행
	@Override
	public MemberDTO KakaoSignUp(MemberDTO memberDTO) throws Exception {
		sqlSession.selectOne(NAME_SPACE + ".KakaoSignUp", memberDTO);
		return sqlSession.selectOne(NAME_SPACE + ".KakaoSignedMemberCheck", memberDTO);
	} // KakaoSignUp

	// 회원가입 아이디 중복 체크
	@Override
	public int memberIdCheck(MemberDTO memberDTO) throws Exception {

		logger.info("회원가입 아이디 중복 체크 memberIdCheck - DAO");

		return sqlSession.selectOne(NAME_SPACE + ".memberIdCheck", memberDTO);

	}

	// 회원정보 수정 로직
	@Override
	public void memberModify(MemberDTO memberDTO) throws Exception {

		logger.info("회원정보 수정 로직 memberModify - DAO");

		sqlSession.update(NAME_SPACE + ".memberModify", memberDTO);
	}

	// 회원 정보 찾기
	@Override
	public MemberDTO memberProfile(String userId) throws Exception {

		logger.info("회원 정보 찾기 memberProfile - DAO");

		return sqlSession.selectOne(NAME_SPACE + ".memberProfile", userId);
	}

	// 회원 탈퇴
	@Override
	public int removeMember(MemberDTO memberDTO) throws Exception {

		logger.info("회원 탈퇴 removeMember - DAO");
		
		sqlSession.delete(NAME_SPACE + ".removeAddress", memberDTO);
		
		return sqlSession.delete(NAME_SPACE + ".removeMember", memberDTO);
				
	}

	// 비밀번호 검증
	@Override
	public String getPwd(String userId) throws Exception {

		logger.info("비밀번호 검증 getPwd - DAO");

		return sqlSession.selectOne(NAME_SPACE + ".getPwd", userId);

	}
}
