package ezen.project.IGSJ.review.service;

import java.util.List;

import ezen.project.IGSJ.review.dto.ReviewDTO;

public interface ReviewService {

	// 리뷰 목록 불러오기
	public List<ReviewDTO> getReviewList(String pno) throws Exception;

	// 리뷰 작성하기
	public int writeReview(ReviewDTO reviewDTO) throws Exception;

	// 리뷰 수정하기
	public int modifyReview(ReviewDTO reviewDTO) throws Exception;

	// 리뷰 작성하기
	public int removeReview(ReviewDTO reviewDTO) throws Exception;
}
