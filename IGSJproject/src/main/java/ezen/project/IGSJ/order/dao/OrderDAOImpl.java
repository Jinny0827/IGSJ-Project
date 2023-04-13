package ezen.project.IGSJ.order.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ezen.project.IGSJ.order.domain.OrderDTO;
import ezen.project.IGSJ.order.domain.OrderDetailDTO;
import ezen.project.IGSJ.order.domain.PaymentDTO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Autowired
	private SqlSession sqlSession;

	private static final String NAME_SPACE = "mappers.orderMapper";

	// 주문 페이지 불러오기
	@Override
	public OrderDTO orderPage(String userId) throws Exception {

		logger.info("주문 페이지 불러오기 orderPage - OrderDAO");
		return sqlSession.selectOne(NAME_SPACE + ".getOrderPage", userId);
	}

	// 카트에 담긴 상품 정보 불러오기
	@Override
	public List<OrderDTO> productOrderPage(String userId) throws Exception {

		logger.info("카트에 담긴 상품 정보 불러오기 productOrderPage - OrderDAO");
		return sqlSession.selectList(NAME_SPACE + ".getProductOrderPage", userId);
	}
	
	// 주문상세내역조회페이지(selelctOne) 불러오기
	   @Override
	   public OrderDTO orderDetailOne(String orderNum) throws Exception {

	      logger.info("주문상세내역조회페이지(selelctOne) 불러오기 orderDetailOne - OrderDAO");

	      return sqlSession.selectOne(NAME_SPACE + ".orderDetailOne", orderNum);
	   }
	

	// 주문정보 등록하기(수령인정보)
	@Override
	public boolean pay(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails, PaymentDTO paymentDTO) throws Exception {

		int result1 = sqlSession.insert(NAME_SPACE + ".payOrder", orderDTO);

		for (OrderDetailDTO orderDetail : orderDetails) {

			sqlSession.insert(NAME_SPACE + ".payOrderDetail", orderDetail);
			sqlSession.update(NAME_SPACE+".countProduct",orderDetail);
		}

		int result3 = sqlSession.insert(NAME_SPACE + ".payPayment", paymentDTO);

		if (result1 == 1 && result3 == 1) {

			return true;

		} else {

			return false;
			
		}
	}

	// 주문내역조회페이지 불러오기
	@Override
	public List<OrderDTO> orderListPage(String userId) throws Exception {

		logger.info("주문내역조회페이지 불러오기 orderListPage - OrderDAO");
		return sqlSession.selectList(NAME_SPACE + ".getOrderListPage", userId);
	}

	// 주문상세내역조회페이지 불러오기
	@Override
	public List<OrderDTO> orderDetailPage(String orderNum) throws Exception {

		logger.info("주문상세내역조회페이지 불러오기 orderDetailPage - OrderDAO");
		return sqlSession.selectList(NAME_SPACE + ".getOrderDetailPage", orderNum);
	}

	// 결제완료페이지 불러오기
	@Override
	public OrderDTO orderFinishPage(String orderNum) throws Exception {

		logger.info("결제완료페이지 불러오기 orderFinishPage - OrderDAO");
		return sqlSession.selectOne(NAME_SPACE + ".getOrderFinishPage", orderNum);
	}

	// 결제완료 후 장바구니 전체 삭제
	@Override
	public int cartAllDelete(OrderDTO orderDTO) throws Exception {
		return sqlSession.delete(NAME_SPACE + ".deleteAll", orderDTO);
	}

}
