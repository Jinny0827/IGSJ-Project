<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ezen.project.IGSJ.utils.pagination.PageIngredient"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(Admin)상품목록보기</title>
<link rel="stylesheet" href="/resources/Outer/admin/css/adminProductList.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../pageIngredient/header.jsp"></jsp:include>

	<!-- 상품 목록 -->
	<div class="container" align="center">
		<div class="listWrap">
			<div>
				<h2 class="adminTitle">상품 목록</h2>
			</div>
			<table class="table table-striped table-hover align-middle table-bordered" style="border-spacing: 0;">
				<thead class="table-dark" style="text-align: center; vertical-align: middle;">
					<tr>
						<th style="width: fit-content;">제품번호</th>
						<th style="width: fit-content;">제품이미지</th>
						<th style="width: fit-content;">제품이름</th>
						<th style="width: fit-content;">제품가격</th>
						<th style="width: fit-content;">제품재고</th>
						<th style="width: fit-content;">제품등록일</th>
						<th style="width: fit-content;">제품등록자</th>
						<th style="width: fit-content;">카테고리</th>
					</tr>
				</thead>
				<tbody style="text-align: center; vertical-align: middle;">
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
					<c:forEach var="adminProductList" items="${adminProductList }">
						<tr>
							<td align="right"><font size="2">${adminProductList.pno}</font></td>
							<td align="right">
								<img alt="상품 이미지 로딩 실패" src="${adminProductList.storedFileRootName}" width="100" height="100">
							</td>
							<td align="right">
								<a href="${contextPath}/admin/productDetail?pno=${adminProductList.pno}">${adminProductList.product_name}</a>
							</td>
							<td align="right">
								<fmt:formatNumber value="${adminProductList.product_price}" pattern="###,###,###"></fmt:formatNumber>
							</td>
							<td align="right">${adminProductList.product_stock}</td>
							<td align="right">
								<fmt:formatDate value="${adminProductList.product_regDate}" pattern="yyyy.MM.dd hh:mm" />
							</td>
							<td align="right">${adminProductList.userId}</td>
							<td align="right">${adminProductList.cno}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 시작 -->
			<div class="paging">
				<%
				PageIngredient pageIngredient = (PageIngredient) request.getAttribute("page");
	
				int selectedPageNum = (int) request.getAttribute("selectedPageNum");
	
				/* 이전페이지 버튼만들기 */
				if (pageIngredient.isPrevPage() == true) {
				%>
				<span>
					<a href="/admin/productlist?pageNum=<%=pageIngredient.getStartPage() - 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">◀이전</a>
				</span>
				<%
				}
	
				/* 페이지 쫙(1,2,3,4...) 출력하기 */
				for (int i = pageIngredient.getStartPage(); i <= pageIngredient.getEndPage(); i++) {
	
				if (selectedPageNum != i) {
				%>
				<span>
					<a id="notSelectedPage" href="/admin/productlist?pageNum=<%=i%><%=pageIngredient.getSearchTypeAndKeyword()%>"><%=i%></a>
				</span>
				<%
				} else if (selectedPageNum == i) {
				%>
				<span>
					<b style="font-size: 22px"><%=i%></b>
				</span>
				<%
				}
				}
	
				/* 다음버튼 만들기 */
				if (pageIngredient.isNextPage() == true) {
				%>
				<span>
					<a href="/admin/productlist?pageNum=<%=pageIngredient.getEndPage() + 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">다음▶</a>
				</span>
				<%
				}
				%>
			</div>
			<!-- 페이징 끝 -->
		</div>
		<!-- 
			private int cno; // 카테고리 번호
			private String product_name; // 제품이름
			private String userId; // 제품등록자
		 -->
		<!-- 게시글 검색기능 -->
		<div class="search">
			<select class="searchType" name="searchType" onchange="changeInputTag()">
				<option value="userId" <%=pageIngredient.getSearchType().equals("userId") ? "selected" : ""%>>등록자</option>
				<option value="product_name" <%=pageIngredient.getSearchType().equals("product_name") ? "selected" : ""%>>제품이름</option>
				<option value="cno" <%=pageIngredient.getSearchType().equals("cno") ? "selected" : ""%>>카테고리</option>
			</select>
			<input type="text" id="keyword" class="keyword" name="keyword" value="<%=pageIngredient.getKeyword()%>" onkeyup="enterSearching();">
			<button id="searchingActivate" type="button" onclick="searchingActivate();">검색</button>
		</div>
		<!-- 게시글 검색기능 끝 -->

	</div>

	<script src="/resources/Outer/admin/js/adminProductList.js"></script>
</body>
</html>