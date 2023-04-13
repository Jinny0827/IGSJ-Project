<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/36.0.0/classic/ckeditor.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>

<style>
/*상품등록*/
.container {
	padding: 40px;
}

.adminTitle {
	font-size: 30px;
	margin-bottom: 10px;
	text-align: left;
}

.title_container{
    border-bottom: 3px solid #000000;
}

.info_container{
	padding: 15px 0;
    display: flex;
    border-bottom: 1px solid #f1f1f1;
}

.info_container p{
	width: 100px;
}

.info_container input{
    width: 80%;
    height: 32px;
    padding: 5px 6px;
    border: 1px solid #e5e5e5;
    background-color: #ffffff;
    box-sizing: border-box;
    font-size: 14px;
    line-height: 20px;
    transition: border 0.2s ease-in-out;
}

.submit_btn{
	display: flex;
    justify-content: center;
    padding: 20px 0;
}

.submit_btn button{
	min-width: 100px;
    height: 40px;
    color: #ffffff;
    box-sizing: border-box;
    font-size: 15px;
    text-align: center;
    cursor: pointer;
    background-color: black;
    padding: 0 100px;
}
/**/
.listWrap {
	width: 90%;
	margin: 0 auto;
}

.ck-editor__editable {
	min-height: 600px;
}
</style>
</head>
<body>
	<jsp:include page="../pageIngredient/header.jsp"></jsp:include>

	<!-- 사이드 바 -->
	<div class="container">
		<form role="form" class="listWrap" method="post" autocomplete="off" enctype="multipart/form-data">
			<div class="title_container">
				<p class="adminTitle">상품 등록</p>
			</div>
			<div class="info_container">
				<p>1차 분류</p>
				<select class="category1">
					<option value="">전체</option>
				</select> <br> 
			</div>
			
			<div class="info_container">
				<p>2차 분류</p> 
				<select class="category2" name="cno">
					<option value="">전체</option>
				</select>
			</div>
			
			<div class="info_container">
				<p for="productName">상품명</p>
				<input type="text" id="product_name" name="product_name" />
			</div>

			<div class="info_container">
				<p for="productPrice">상품가격</p>
				<input type="text" id="product_price" name="product_price" />
			</div>

			<div style="padding-top: 15px">
				<p for="product_description" style="margin-bottom: 15px;">상품소개</p>
				<textarea id="product_description" name="product_description"></textarea>
			</div>

			<div class="info_container">
				<p for="productStock">상품수량</p>
				<input type="text" id="product_stock" name="product_stock" />
			</div>

			<div class="info_container">
				<p for="gdsImg">이미지</p>
				<input type="file" id="product_img" name="product_img" style="border: none;" />
				<div class="select_img">
					<img src="" />
				</div>
			</div>

			<div class="submit_btn">
				<button type="submit" id="register_Btn" class="btn btn-primary">등록</button>
			</div>

		</form>
	</div>
	<script src="/resources/Outer/seller/js/register.js"></script>
</body>
</html>