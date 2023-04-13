package ezen.project.IGSJ.review.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ezen.project.IGSJ.review.dao.ReviewDAO;
import ezen.project.IGSJ.review.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired(required = false)
	private ReviewDAO reviewDAO;

	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

	// 리뷰 목록 불러오기
	@Override
	public List<ReviewDTO> getReviewList(String pno) throws Exception {
		
		logger.info("리뷰 목록 불러오기 service");

		return reviewDAO.getReviewList(pno);
	}

	// 리뷰 작성하기
	@Override
	public int writeReview(ReviewDTO reviewDTO) throws Exception {

		logger.info("리뷰 작성 service");

		return reviewDAO.writeReview(reviewDTO);
	}

	// 리뷰 수정하기
	@Override
	public int modifyReview(ReviewDTO reviewDTO) throws Exception {
		
		logger.info("리뷰 수정하기 service");
		
		return reviewDAO.modifyReview(reviewDTO);
	}
	
	// 리뷰 삭제하기
	@Override
	public int removeReview(ReviewDTO reviewDTO) throws Exception {
		
		logger.info("리뷰 삭제하기 service");
		
		return reviewDAO.removeReview(reviewDTO);
	}

}
