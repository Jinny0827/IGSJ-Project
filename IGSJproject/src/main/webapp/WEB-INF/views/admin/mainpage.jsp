<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">

.admin-menu{
	min-height: 601px;
}
</style>
</head>
<body>
	<jsp:include page="../pageIngredient/header.jsp"></jsp:include>

	<div class="admin-menu">
		<ul>
			<li>
				<a href="/admin/admindetail">
					<img src="/resources/pageImg/adminLogo.png" alt="관리자페이지로고" width="500" height="500">
				</a>
			</li>
		</ul>
	</div>
	
	<jsp:include page="../pageIngredient/footer.jsp"></jsp:include>
</body>
</html>