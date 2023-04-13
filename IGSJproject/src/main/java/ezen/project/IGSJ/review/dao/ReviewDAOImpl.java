package ezen.project.IGSJ.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.review.dto.ReviewDTO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	@Autowired(required = false)
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "mappers.reviewMapper";

	private static final Logger logger = LoggerFactory.getLogger(ReviewDAOImpl.class);

	// 리뷰 목록 불러오기
	@Override
	public List<ReviewDTO> getReviewList(String pno) throws Exception {

		logger.info("리뷰 목록 불러오기 dao");

		return sqlSession.selectList(NAME_SPACE + ".getReviewList", pno);
	}

	// 리뷰 작성하기
	@Override
	public int writeReview(ReviewDTO reviewDTO) throws Exception {

		logger.info("리뷰 작성하기 dao");

		return sqlSession.insert(NAME_SPACE + ".writeReview", reviewDTO);
	}

	// 리뷰 수정하기
	@Override
	public int modifyReview(ReviewDTO reviewDTO) throws Exception {

		logger.info("리뷰 수정하기 dao");

		return sqlSession.update(NAME_SPACE + ".modifyReview", reviewDTO);
	}

	// 리뷰 삭제하기
	@Override
	public int removeReview(ReviewDTO reviewDTO) throws Exception {

		logger.info("리뷰 삭제하기 dao");

		return sqlSession.delete(NAME_SPACE + ".removeReview", reviewDTO);
	}

}
