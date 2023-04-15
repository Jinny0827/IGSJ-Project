// 검색타입을 결정하는 select 태그가 카테고리로 바뀌면 시작되는 onchange 로직이다.
function changeInputTag() {
	// select 태그의 class = 'searchType'을 가져와 options [select태그관련변수명.selectedIndex].value로 option 태그의 값을 가져온다.
	let searchType = document.querySelector(".searchType");
	// select에서 선택된 option의 val
	let opVal = searchType.options[searchType.selectedIndex].value;

	// 만약 option 태그의 값이 'category' (select 태그 = 카테고리)라면 검색창인 input태그를 select 태그로 바꿔버린다.
	if (opVal == 'userVerify') {

		let replacedType = $(".keyword").replaceWith
			(
				"<select id='searchType' name='searchType' onchange='changeCategoryValue();'>"
				+ "<option value='0' name='keyword'>일반회원</option>"
				+ "<option value='5' name='keyword'>판매자</option>"
				+ "<option value='128' name='keyword'>관리자</option>"
				+ "</select>"
			);
		
		
	} else {

		$("#searchType").replaceWith(
			"<input type='text' id='keyword' class='keyword' name='keyword' value='<%=pageIngredient.getKeyword()%>' onkeyup='enterSearching();'>"
		);

		document.getElementById("keyword").value = "";
	}

}

/* 위의 함수에서 "이미 실행이 돼고 나온 태그"에 적용되는 로직이다. */
function changeCategoryValue() {
	/* 새롭게 생성된 select 태그의 id를 받아 option의 value를 저장한다. */
	let categorySearchType = document.getElementById("searchType");
	let secondCategoryValue = categorySearchType.options[categorySearchType.selectedIndex].value;

	/* 그리고 검색에 써먹기 위해 return을 시켜 값이 확정되도록 만든다. */
	return secondCategoryValue;
}

function searchingActivate() {
	let searchType = document.getElementsByName("searchType")[0].value;
	let keyword = document.getElementsByName("keyword")[0].value;

	/* 만약 select 태그가 카테고리(option 태그 value = 'category' 라면 changeCategoryValue 함수의 결과값을 키워드에 넣어 검색한다. */
	if (searchType == 'userVerify') {
		keyword = changeCategoryValue();
	}

	/* select 태그의 value인 searchType에 관한 option태그의 값과
	input태그에 들어간 keyword 값을 쿼리스트링으로 보내 첫페이지를 출력한다. */
	location.href = "/admin/memberlist?pageNum=1" + "&searchType="
		+ searchType + "&keyword=" + keyword;
	

}

/* input 태그 안에서 enter키 (keyNumber == 13) 누르면 검색 시작. */
function enterSearching() {
	if (window.event.keyCode == 13) {
		searchingActivate();
	}
}


/* 회원탈퇴로직 ajax */
function removeMember(userId) {

	let removeYN = confirm("정말 선택한 회원을 삭제하시겠습니까?\n삭제된 정보는 복구되지 않습니다.");

	if (removeYN) {

		let tempAdminPwd = '';

		$.ajax({
			url: "/admin/removeMember",
			type: "get",
			data: {
				"tempAdminPwd": tempAdminPwd
			},
			async: false, /* ajax를 2개이상 사용하므로 인증번호를 받아내는 ajax는 비동기처리를 안함으로써 ajax가 꼬이지 않게 한다.*/
			dataType: "text",

			success: function(resultPwd) {
				tempAdminPwd = resultPwd;
			},
			error: function(error) {
				alert("관리자 인증번호 생성 중 오류가 발생하였습니다.");
				return false;
			}
		})

		let adminPwdPrompt = prompt("관리자 인증 비밀번호를 입력해주세요.");
		
		if (tempAdminPwd === adminPwdPrompt) {
			
			alert("비밀번호가 일치합니다. 회원삭제를 시작합니다.");
			
			$.ajax({
				url: "/admin/removeMember",
				type: "post",
				data: {
					"userId": userId
				},
				dataType: "json",

				success: function(result) {
					if (result == true) {

						alert("회원 삭제가 완료되었습니다.");
						location.reload(true);

					} else {
						alert("관리자 인증번호가 맞지 않습니다.");
						return false;
					}
				},
				error: function(error) {
					alert("알 수 없는 오류로 회원삭제에 실패하였습니다.\n로그를 확인해주세요.");
					return false;
				}
			})
		} else {
			alert("인증번호가 일치하지 않습니다.");
		}

	} else {
		alert("탈퇴가 취소되었습니다.");

		return false;
	}
}
