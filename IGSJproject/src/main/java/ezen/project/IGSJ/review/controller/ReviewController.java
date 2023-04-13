package ezen.project.IGSJ.review.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ezen.project.IGSJ.review.dto.ReviewDTO;
import ezen.project.IGSJ.review.service.ReviewService;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired(required = false)
	private ReviewService reviewService;

	// 리뷰 목록 불러오기
	@ResponseBody
	@GetMapping("/review/getReviewList/{pno}")
	public List<ReviewDTO> getReviewList(@PathVariable String pno) throws Exception {
		logger.info("리뷰 목록 불러오기 controller");
		return reviewService.getReviewList(pno);
	} // getReviewList()

	// 리뷰 작성하기
	@ResponseBody
	@RequestMapping(value = "/review/writeReview", method = RequestMethod.POST)
	public boolean writeReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
		logger.info("리뷰 작성 시작 controller");
		int writeResult = reviewService.writeReview(reviewDTO);
		return writeResult == 1 ? true : false;
	} // writeReview()

	// 리뷰 수정하기
	@ResponseBody
	@RequestMapping(value = "/review/modifyReview", method = RequestMethod.POST)
	public boolean modifyReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
		logger.info("리뷰 수정 시작 controller");
		int modifyResult = reviewService.modifyReview(reviewDTO);
		return modifyResult == 1 ? true : false;
	} // modifyReview()

	// 리뷰 삭제하기
	@ResponseBody
	@RequestMapping(value = "/review/removeReview", method = RequestMethod.POST)
	public boolean removeReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
		logger.info("리뷰 삭제 시작 controller");
		int removeResult = reviewService.removeReview(reviewDTO);
		return removeResult == 1 ? true : false;
	} // removeReview()

}
