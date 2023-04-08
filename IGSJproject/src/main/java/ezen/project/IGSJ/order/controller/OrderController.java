package ezen.project.IGSJ.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ezen.project.IGSJ.order.domain.OrderDTO;
import ezen.project.IGSJ.order.domain.OrderDetailDTO;
import ezen.project.IGSJ.order.domain.PaymentDTO;
import ezen.project.IGSJ.order.service.OrderService;
import ezen.project.IGSJ.product.domain.ProductDTO;

@Controller("orderController")
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired(required = false)
	private OrderService orderService;

	// 주문 페이지 불러오기
	@ResponseBody
	@RequestMapping(value="/orderPage/{userId}", method=RequestMethod.GET)
	public OrderDTO orderPage(@PathVariable("userId") String userId) throws Exception{

		logger.info("주문 페이지 불러오기 orderPage - Controller");
		OrderDTO orderDTO = orderService.orderPage(userId);

		return orderDTO;
	}

	// 카트에 담긴 상품 정보 불러오기
	@ResponseBody
	@RequestMapping(value="/productOrderPage/{userId}", method=RequestMethod.GET)
	public List<OrderDTO> productOrderPage(@PathVariable("userId") String userId) throws Exception {

		logger.info("카트에 담긴 상품 정보 불러오기 productOrderPage - Controller");
		return orderService.productOrderPage(userId);

	}

	// orderNum 만들기
	@ResponseBody
	@RequestMapping(value="/orderNum", method=RequestMethod.POST)
	public String pay(@RequestBody OrderDTO orderDTO, OrderDetailDTO orderDetailDTO ,PaymentDTO paymentDTO) throws Exception{
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yymmdd");
        String str = format.format(date);
        int str2 = Integer.parseInt(str);

		String rn = RandomStringUtils.randomNumeric(6);
		int num6 = Integer.parseInt(rn);

		orderDTO.setOrderNum(str2 + "_" + num6);
		orderDetailDTO.setOrderNum(str2 + "_" + num6);

		List<ProductDTO> productList = orderDTO.getProductList();
		productList.equals("pno");


		String rnn = RandomStringUtils.randomNumeric(9);
		int num9 = Integer.parseInt(rnn);
		orderDetailDTO.setOrderDetailNum(str2 + num9);
		paymentDTO.setOrderDetailNum(str2 + num9);

        paymentDTO.setPaymentNum(str2 + num6);

        //orderDetailDTO
//        orderDetailDTO.setPno(productList.ge);
//        orderDetailDTO.setProductCnt(orderDTO.getProductCnt());
//        orderDetailDTO.setProductPrice(orderDTO.getProduct_price());
        orderDetailDTO.setPaymentStatus("1561");
        orderDetailDTO.setProductList(productList);

        //paymentDTO
        paymentDTO.setPaySet(orderDTO.getPaySet());
        paymentDTO.setPayCompany(orderDTO.getPayCompany());
        paymentDTO.setPayMoney(orderDTO.getPayMoney());
        paymentDTO.setPayRegDate(orderDTO.getPayRegDate());
        paymentDTO.setPayBank(orderDTO.getPayBank());

        orderService.pay(orderDTO, orderDetailDTO, paymentDTO);
		return "";
	}

	// 주문내역조회페이지 불러오기
	@ResponseBody
	@RequestMapping(value="/orderListPage/{userId}" , method=RequestMethod.GET)
	public List<OrderDTO> orderListPage(@PathVariable("userId") String userId) throws Exception{
		logger.info("주문내역조회페이지 불러오기 orderListPage - Controller");
		List<OrderDTO> orderDTO =  orderService.orderListPage(userId);
		return orderDTO;
	}

	// 주문상세내역조회페이지 불러오기
	@ResponseBody
	@RequestMapping(value="/orderDetailPage", method=RequestMethod.GET)
	public OrderDTO orderDetailPage(@RequestParam String orderNum) throws Exception{

		logger.info("주문상세내역조회페이지 불러오기 orderDetailPage - Controller");
		OrderDTO orderDTO = orderService.orderDetailPage(orderNum);
		return orderDTO;

	}

	// 결제완료페이지 불러오기
	@ResponseBody
	@RequestMapping(value="/orderFinishPage", method=RequestMethod.GET)
	public OrderDTO orderFinishPage(@RequestParam String orderNum) throws Exception{

		logger.info("결제완료페이지 불러오기 orderFinishPage - Controller");
		OrderDTO orderDTO = orderService.orderFinishPage(orderNum);
		return orderDTO;


	}

	// 카트에 담긴 상품 정보 불러오기
//		@ResponseBody
//		@RequestMapping(value="/productOrderPage/{userId}", method=RequestMethod.GET)
//		public List<OrderDTO> productOrderPage(@PathVariable("userId") String userId) throws Exception {
//
//			logger.info("카트에 담긴 상품 정보 불러오기 productOrderPage - Controller");
//			return orderService.productOrderPage(userId);
//
//		}


//	// orderDetailNum 만들기
//		@ResponseBody
//		@RequestMapping(value="/orderDetailNum", method=RequestMethod.POST)
//		public String writeProductInfo(OrderDetailDTO orderDetailDTO) throws Exception{
//
//			Date date = new Date();
//	        SimpleDateFormat format = new SimpleDateFormat("yymmdd");
//	        String str = format.format(date);
//	        int str2 = Integer.parseInt(str);
//
//			String rnn = RandomStringUtils.randomNumeric(10);
//			int num10 = Integer.parseInt(rnn);
//
//			orderDetailDTO.setOrderDetailNum(str2 + num10);
//
//			return "";
//		}


}


