<%@page import="org.apache.taglibs.standard.lang.jstl.DivideOperator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ezen.project.IGSJ.utils.pagination.PageIngredient"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(seller)상품목록보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!--
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
-->
<style>
/*상품목록*/
span {
	padding: 10px;
}

.container {
	padding: 40px;
}

.adminTitle {
	font-size: 30px;
	margin-bottom: 10px;
	text-align: left;
}

.listWrap {
	width: 90%;
	margin: 0 auto;
}

.listWrap table {
	border-top: 3px solid black;
	border-spacing: 0;
	width: 100%;
}

.listWrap a {
	color: black;
	cursor: pointer;
	text-decoration: underline;
	font-weight: bold;
}

.listWrap thead {
	height: 52px;
	font-size: 14px;
	vertical-align: middle;
	text-align: center;
	padding: 0;
	background-color: #f1f1f1;
}

.listWrap th {
	border-bottom: 1px solid black;
}

.listWrap td {
	height: 100%;
	border-bottom: 1px solid #ccc;
	font-size: 13px;
	vertical-align: middle;
	font-weight: normal;
	text-align: center;
}

.paging {
	margin-top: 20px;
}
.paging span{
	padding: 10px;
}

.paging b {
	font-size: 22px;
	border: 1px solid black;
	border-radius: 10px;
	padding: 0 15px;
}

.paging a {
	text-decoration: none;
	color: black;
}

</style>
</head>
<body>
	<jsp:include page="../pageIngredient/header.jsp"></jsp:include>
	<!-- 상품 목록 -->
	<div class="container" align="center">
		<div class="listWrap">
			<div>
				<p class="adminTitle">상품 목록</p>
			</div>
			<table>
				<thead>
					<tr>
						<th style="width: fit-content;">주문번호</th>
						<th style="width: fit-content;">주문일자</th>
						<th style="width: fit-content;">상품번호</th>
						<th style="width: fit-content;">수량</th>
						<th style="width: fit-content;">금액</th>
						<th style="width: fit-content;">배송상태</th>
					</tr>
				</thead>
				<tbody>
					<!-- 
					private String pno; 					// 제품번호
					private String storedFileRootName; 		// 상품이미지 경로 - 조인용
					private int product_price; 				// 제품가격
					private int product_stock; 				// 제품재고
					private String product_description; 	// 제품설명
					private Date product_regDate; 			// 제품등록일자
					private int cno; 						// 카테고리 번호
					private String product_name; 			// 제품이름
					private String userId; 					// 제품등록자
					
					adminProductList
				-->
					<%-- <c:forEach> --%>
						<tr>
							<td align="right"></td>
							<td align="right">
								
							</td>
							<td align="right"></td>
							<td align="right"></td>
							<td align="right"></td>
							<td align="right"></td>
							
						</tr>
					<%-- </c:forEach> --%>
				</tbody>
			</table>

			<!-- 페이징 시작 -->
			<%-- <div>
				<div class="paging" align="center">
					<%
					PageIngredient pageIngredient = (PageIngredient) request.getAttribute("page");

					int selectedPageNum = (int) request.getAttribute("selectedPageNum");

					/* 이전페이지 버튼만들기 */
					if (pageIngredient.isPrevPage() == true) {
					%>
					<span> <a
						href="/seller/productlist?pageNum=<%=pageIngredient.getStartPage() - 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">&lt;</a>
					</span>
					<%
					}

					/* 페이지 쫙(1,2,3,4...) 출력하기 */
					for (int i = pageIngredient.getStartPage(); i <= pageIngredient.getEndPage(); i++) {

					if (selectedPageNum != i) {
					%>
					<span> <a id="notSelectedPage"
						href="/seller/productlist?pageNum=<%=i%><%=pageIngredient.getSearchTypeAndKeyword()%>"><%=i%></a>
					</span>
					<%
					} else if (selectedPageNum == i) {
					%>
					<span> <b style=""><%=i%></b>
					</span>
					<%
					}
					}

					/* 다음버튼 만들기 */
					if (pageIngredient.isNextPage() == true) {
					%>
					<span> <a
						href="/seller/productlist?pageNum=<%=pageIngredient.getEndPage() + 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">&gt;</a>
					</span>
					<%
					}
					%>
				</div>
				<!-- 페이징 끝 -->
				
			</div> --%>
		</div>
	
	</div>
	<script src="/resources/seller/sellerProductList.js"></script>
</body>
</html>