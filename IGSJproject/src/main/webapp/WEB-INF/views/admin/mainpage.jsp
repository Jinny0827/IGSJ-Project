<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
/*헤더*/
* {
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	flex-direction: column;
	height: 600px;
	width: 1516px;
}

.header {
	display: flex;
	width: 100%;
	height: 90px;
	background: black;
	padding-left: 20px;
	align-items: center;
	justify-content: space-between;
}

.logo span {
	color: white;
	font-size: 40px;
	text-decoration: none;
	padding-left: 10px;
}

.logo a {
	text-decoration: none;
	font-weight: bold;
	color: white;
	padding: 0 30px;
}

.logo a:hover {
	text-decoration: underline;
}

.login_info {
	height: 50px;
	border-bottom: 1px solid #e5e5e5;
	display: flex;
	align-items: center;
	padding: 10px 0;
}

.login_info p {
	font-size: 20px;
	margin-left: 20px;
	text-decoration: none;
	font-weight: bold;
}
/*메인*/
.main {
	height: 100%;
	width: 100%;
	display: flex;
	flex-direction: column;
}

.main p {
	padding: 50px;
}

.box_container {
	display: flex;
	flex-direction: row;
	align-items: center;
	padding-left: 50px;
	justify-content: center;
}

.box {
	height: 300px;
	min-width: 400px;
	margin-right: 50px;
	display: flex;
	flex-direction: column;
	align-items: center;
	border: 2px solid #e5e5e5;
	justify-content: center;
	transition: box-shadow .3s;
}

.box:hover {
	box-shadow: 0 0 11px rgba(33, 33, 33, .2);
}

.box img {
	width: 70px;
}

.box_container a {
	text-decoration: none;
	color: black;
}

.material-symbols-outlined {
	font-size: 100px;
	display: flex;
	justify-content: center;
}

.label {
	font-size: 30px;
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<div class="logo">
				<span>IGSJ</span>
			</div>
			<div class="logo" align="right">
				<a href="/manager/managerLogout">로그아웃</a>
			</div>
		</div>
		<div class="login_info">
			<p>관리자 ${member.userId } 님이 로그인중입니다.</p>
		</div>
		<div class="main">
			<p>
				안녕하세요, ${member.userId } 님
				<br>
				<br>
				관리자 페이지에서 회원 목록과 상품 목록을 조회/관리할 수 있습니다.
			</p>
			<div class="box_container">
				<a href="/admin/memberlist?pageNum=1">
					<div class="box">
						<span class="material-symbols-outlined">groups</span>
						</br>
						<span class="label">회원 관리 페이지</span>
					</div>
				</a>
				<a href="/admin/productlist?pageNum=1">
					<div class="box">
						<span class="material-symbols-outlined">storefront</span>
						</br>
						<span class="label">상품 관리 페이지</span>
					</div>
				</a>
			</div>
		</div>
	</div>

</body>
</html>