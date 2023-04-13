<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="ezen.project.IGSJ.utils.pagination.PageIngredient"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>(Admin)회원 목록 보기</title>
<link rel="stylesheet" href="/resources/Outer/admin/css/adminMemberList.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<style>

</style>
<body>
	<jsp:include page="../pageIngredient/header.jsp"></jsp:include>

	<!-- 회원 목록 -->
	<div class="container" align="center">
		<div class="listWrap">
			<div>
				<p class="adminTitle">회원 목록</p>
			</div>
			<table>
				<thead>
					<tr>
						<th style="width: fit-content;">아이디</th>
						<th style="width: fit-content;">이름</th>
						<th style="width: fit-content;">생년월일</th>
						<th style="width: fit-content;">가입일자</th>
						<th style="width: fit-content;">회원유형</th>
						<th style="width: fit-content;">관리</th>
					</tr>
				</thead>
				<tbody style="text-align: center; vertical-align: middle;">
				
					<c:forEach var="adminMemberList" items="${adminMemberList }">
						<tr>
							<td align="right">
								<a href="${contextPath}/member/memberDetail?userId=${adminMemberList.userId}" title="상세정보조회">${adminMemberList.userId}</a>
							</td>
							<td align="right">${adminMemberList.userName}</td>
							<td align="right">${adminMemberList.userBirth}</td>
							<td align="right">
								<fmt:formatDate value="${adminMemberList.userJoinDate}" pattern="yyyy.MM.dd  hh:mm" />
							</td>
							<c:choose>
								<c:when test="${adminMemberList.userVerify == 128 }">
									<td>관리자</td>
								</c:when>
								<c:when test="${adminMemberList.userVerify == 5 }">
									<td>판매자</td>
								</c:when>
								<c:otherwise>
									<td>일반회원</td>
								</c:otherwise>
							</c:choose>
							<td>
								<a href="/admin/membermodifypage?userId=${adminMemberList.userId }">[수정]</a>
								<br>
								<a href="javascript:removeMember('${adminMemberList.userId }')">[삭제]</a>
							</td>
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
					<a href="/admin/memberlist?pageNum=<%=pageIngredient.getStartPage() - 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">&lt;</a>
				</span>
				<%
				}
	
				/* 페이지 쫙(1,2,3,4...) 출력하기 */
				for (int i = pageIngredient.getStartPage(); i <= pageIngredient.getEndPage(); i++) {
	
				if (selectedPageNum != i) {
				%>
				<span>
					<a id="notSelectedPage" href="/admin/memberlist?pageNum=<%=i%><%=pageIngredient.getSearchTypeAndKeyword()%>"><%=i%></a>
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
					<a href="/admin/memberlist?pageNum=<%=pageIngredient.getEndPage() + 1%><%=pageIngredient.getSearchTypeAndKeyword()%>">&gt;</a>
				</span>
				<%
				}
				%>
			</div>
			<!-- 페이징 끝 -->
		</div>

		<!-- 게시글 검색기능 -->
		<div class="search">
			<select class="searchType" name="searchType" onchange="changeInputTag()">
				<option value="userId" <%=pageIngredient.getSearchType().equals("userId") ? "selected" : ""%>>아이디</option>
				<option value="userName" <%=pageIngredient.getSearchType().equals("userName") ? "selected" : ""%>>이름</option>
				<option value="userVerify" <%=pageIngredient.getSearchType().equals("userVerify") ? "selected" : ""%>>회원유형</option>
			</select>
			<input type="text" id="keyword" class="keyword" name="keyword" value="<%=pageIngredient.getKeyword()%>" onkeyup="enterSearching();">
			<button id="searchingActivate" type="button" onclick="searchingActivate();">검색</button>
		</div>
		<!-- 게시글 검색기능 끝 -->

	</div>

	<script src="/resources/Outer/admin/js/adminMemberList.js"></script>
</body>
</html>