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
	margin: 20px 0;
}
.paging span{
	padding:  15px;
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

.registerbtn {
	margin-top: 20px;
	text-align: right;
}

.registerbtn a {
	border: 1px solid black;
	padding: 10px 30px;
	color: black;
	text-decoration: none;
	background: #f1f1f1;
	font-weight: bold;
}

/*게시글 검색기능*/
.search {
	display: flex;
	align-items: flex-end;
	justify-content: center;
}

.searchType {
	height: 30px;
	margin-right: 10px;
}

#searchType {
	height: 30px;
}

.search input {
	height: 25px;
	width: 300px;
}

.search button {
	height: 30px;
	width: 80px;
	margin-left: 10px;
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
					<c:forEach var="sellerProductList" items="${sellerProductList }">
						<tr>
							<td align="right">${sellerProductList.pno}</td>
							<td align="right"><img alt="상품 이미지 로딩 실패"
								src="${sellerProductList.storedFileRootName}" width="100"
								height="100"></td>
							<td align="right"><a
								href="${contextPath}/seller/productDetail?pno=${sellerProductList.pno}">${sellerProductList.product_name}</a>
							</td>
							<td align="right">${sellerProductList.product_price}</td>
							<td align="right">${sellerProductList.product_stock}</td>
							<td align="right"><fmt:formatDate
									value="${sellerProductList.product_regDate}"
									pattern="yyyy.MM.dd hh:mm" /></td>
							<td align="right">${sellerProductList.userId}</td>
							<td align="right">${sellerProductList.cno}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- 페이징 시작 -->
			<div>
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
			</div>
		</div>
		<!-- 
			private int cno; // 카테고리 번호
			private String product_name; // 제품이름
			private String userId; // 제품등록자
		 -->
		<!-- 게시글 검색기능 -->
		<div class="search">
			<select class="searchType" name="searchType"
				onchange="changeInputTag()">
				<option value="product_name"
					<%=pageIngredient.getSearchType().equals("product_name") ? "selected" : ""%>>제품이름</option>
				<option value="cno"
					<%=pageIngredient.getSearchType().equals("cno") ? "selected" : ""%>>카테고리</option>
			</select> <input type="text" id="keyword" class="keyword" name="keyword"
				value="<%=pageIngredient.getKeyword()%>" onkeyup="enterSearching();">
			<button id="searchingActivate" type="button"
				onclick="searchingActivate();">검색</button>
		</div>
		<!-- 게시글 검색기능 끝 -->

	</div>
	<script src="/resources/seller/sellerProductList.js"></script>
</body>
</html>