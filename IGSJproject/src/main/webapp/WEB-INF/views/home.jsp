<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매니저 로그인 페이지</title>
<link rel="stylesheet" href="/resources/Outer/admin/css/managerLoginPage.css">
<style type="text/css">
span>input {
	outline: none;
}
</style>
</head>
<body>
	<div class="login">
		<h1>
			<a href="/" title="관리자메인페이지">
				<span class="login-logo">IGSJ Management</span>
			</a>
		</h1>

		<form action="/managerLogin" method="post" id="login-form" name="login-form">
			<div class="login-box">
				<ul>
					<li>
						<span class="id-icon">
							<!-- 장식이미지 -->
						</span>
						<span>
							<input type="text" id="userId" name="userId" placeholder="아이디">
						</span>
					</li>
					<li class="error id-error">아이디를 입력해주세요</li>
					<li>

						<span class="pwd-icon">
							<!-- 장식이미지 -->
						</span>
						<span>
							<input type="password" id="userPwd" name="userPwd" placeholder="비밀번호">
						</span>

					</li>
					<li class="error pwd-error">비밀번호를 입력해주세요</li>
					<c:choose>
						<c:when test="${managerLoginFalse == false }">
							<li style="color: red; border: none;">아이디나 비밀번호를 확인해주세요</li>
						</c:when>
						<c:when test="${blockNomalMember == false }">
							<li style="color: red; border: none;">관리자만 로그인이 가능한 페이지입니다.</li>
						</c:when>
					</c:choose>
				</ul>
				<button type="submit" id="login-btn" onclick="memberLogin()">로그인</button>
			</div>
		</form>
	</div>
</body>
</html>